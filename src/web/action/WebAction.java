package web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import net.sf.json.JSONObject;
import web.dao.ActImgDao;
import web.dao.ActItemDao;
import web.dao.ActRecordDao;
import web.dao.ActivityDao;
import web.dao.AddressDao;
import web.dao.AnnounceDao;
import web.dao.AnnounceImgDao;
import web.dao.JpushDao;
import web.dao.StudentDao;
import web.dao.TeacherDao;
import web.model.Activiting;
import web.model.Activity;
import web.model.ActivityImg;
import web.model.ActivityItem;
import web.model.ActivityType;
import web.model.Jpush;
import web.model.Address;
import web.model.Announce;
import web.model.AnnounceImg;
import web.model.Students;
import web.model.Teacher;
import web.util.JSONTools;
import web.util.MD5Util;
import web.util.PhotoYsImp;

@SuppressWarnings("serial")
public class WebAction extends ActionSupport implements SessionAware {

	// 登陆
	@SuppressWarnings("unchecked")
	public String login() throws Exception {
		// System.out.println("开始登陆" );
		Teacher t = teacherDao.login(name, MD5Util.MD5(psdMd5));
		if (t == null) {
			// System.out.println("空");
			result = 1;
		} else {
			result = 0;
			// System.out.println("登陆" + name + ":" + psdMd5);
			Integer p = 1;
			session.put("user", t);
			session.put("power", t.getTPower());
			session.put("loginname", t.getTName());
			session.put("loginid", t.getId().getTTeacherId());
			session.put("actpag", p);// 用于活动分页
			session.put("itempag", p);// 用于活动类别分页1
			// session.put("recordpag", p);// 学生成绩分页
			session.put("addresspag", p);// 地址分页2
			session.put("announcepag", p);// 公告分页3
		}
		return "success";
	}

	// 退出
	@SuppressWarnings("unchecked")
	public String unLogin() {
		// session.clear();
		((org.apache.struts2.dispatcher.SessionMap<String, Object>) this.session)
				.invalidate();
		System.out.println("退出");
		return "success";
	}

	// 后台管理主页
	@SuppressWarnings("unchecked")
	public String allActivity() throws Exception {
		String loginid = (String) session.get("loginid");
		int i = 0;
		switch (loginid) {
			case "000001":
				i = 1;
				break;
			case "000002":
				i = 2;
				break;
			case "000003":
				i = 3;
				break;
			case "000004":
				i = 4;
				break;
			case "000005":
				i = 5;
				break;
			case "000006":
				i = 6;
				break;
		}
		// System.out.println(loginid+"ss"+i);
		double x = activityDao.pageSum(i);
		if (x != 0) {
			Integer pa = (Integer) session.get("actpag");
			pageSum = (int) (activityDao.pageSum(i) / 7 + 0.99);
			if (pageSum == 0)
				pageSum = 1;
			if (n == 0)
				pa = 1;
			else if (n == 1 && pa > 0)
				pa = pa - 1;
			else if (n == 2 && pa < pageSum)
				pa = pa + 1;
			else if (n == 3 && pa < pageSum)
				pa = pageSum;
			n = 4;// 保持原位置
			page = pa;
			activitylist = activityDao.allActivity((pa - 1) * 7, i);
			session.put("actpag", pa);
		} else {
			activitylist = null;
		}
		// System.out.println("OK ");
		return "success";

	}

	// 活动进行情况
	public String ontActivityStu() {
		return "success";
	}

	// 学生活动成绩列表+
	@SuppressWarnings("unchecked")
	public String allActRecord() {
		activitingslist = actRecordDao.allStuRecord(actid);
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}

	// 活动成绩
	public String oneActRecord() {
		// System.out.println(actid+"活动参与人");
		activitingslist = actRecordDao.findActRecord(actid);
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}

	public String toActqiandao() {
		activitingslist = actRecordDao.findActqiandao(actid);
		s = JSONTools.createJsonObject("data", activitingslist);

		return "success";
	}
	public String updateOneActQiandao() {
		if (stuid.contains(",")) {
			String[] qiandao = null;
			qiandao = stuid.split(",");
			for (int i = 0; i < qiandao.length; i++) {
				activityrecord = actRecordDao
						.findactRecordStu(actid, qiandao[i]);
				activityrecord.setAFourdevicecode(""+Math.random()*12222);
				actRecordDao.updateactRecordStu(activityrecord);
			}
		}
		return "success";
	}
	// 修改学生成绩
	public String updateOneRecord() {
		// System.out.println("活动id:" + actid);
		// System.out.println("通过学生id=" + stuid);
		// System.out.println("未通过学生id=" + stuokid);
		// System.out.println("优秀学生id=" + stugreatid);
		// 通过的学生
		if (stuid.contains(",")) {
			String[] passid = null;
			passid = stuid.split(",");
			// System.out.println("pass长度:" + passid.length);
			for (int i = 0; i < passid.length; i++) {
				// System.out.println("id:" + passid[i] + ", ,deleteid:" +
				// deleteid);
				activityrecord = actRecordDao
						.findactRecordStu(actid, passid[i]);
				activityrecord.setAGrade(2);
				activityrecord.setAStatus(3);
				actRecordDao.updateactRecordStu(activityrecord);
			}
		} else {
			// System.out.println("没有学生通过");
		}
		// 未通过的学生
		if (stuokid.contains(",")) {
			String[] unpassid = null;
			unpassid = stuokid.split(",");
			for (int i = 0; i < unpassid.length; i++) {
				// System.out.println("id:" + unpassid[i] + " ,deleteid:" +
				// deleteid);
				activityrecord = actRecordDao.findactRecordStu(actid,
						unpassid[i]);
				//	activityrecord.setAStatus(3);
				activityrecord.setAGrade(3);
				actRecordDao.updateactRecordStu(activityrecord);
			}
		} else {
			// System.out.println("没有有学生都通过");
		}
		// 优秀的学生id
		if (stugreatid.contains(",")) {
			String[] greatsid = null;
			greatsid = stugreatid.split(",");
			for (int i = 0; i < greatsid.length; i++) {
				activityrecord = actRecordDao.findactRecordStu(actid,
						greatsid[i]);
				activityrecord.setAGrade(1);
				activityrecord.setAStatus(3);
				actRecordDao.updateactRecordStu(activityrecord);
			}
		} else {
			// System.out.println("么有学生获得优秀");
		}
		return "success";
	}

	// 活动详情
	public String oneActivity() throws Exception {
		detailactivity = activityDao.findoneActivity(id);
		if (detailactivity != null) {
			return "success";
		} else
			return "faile";
	}

	// 活动详情链接
	public String toOneActivity() throws Exception {
		//	System.out.println("xiu");
		detailactivity = activityDao.findoneActivity(id);
		if (detailactivity != null) {
			return "success";
		} else
			return "faile";
	}
	//修改活动
	public String updateActivity() throws Exception {
		detailactivity = activityDao.findoneActivity(id);
		detailactivity.setRStartTime(startTime1);
		detailactivity.setREndTime(endTime1);
		detailactivity.setRStartTimeTwo(startTime2);
		detailactivity.setREndTimeTwo(endTime2);
		activityDao.updateActTime(detailactivity);
		return "success";
	}

	// 删除活动 将活动状态改变
	public String deleteActivity() throws Exception {
		Activity activity = activityDao.findoneActivity(id);
		if (activity != null) {
			activity.setRStatus(-1);
			activityDao.deleteActivity(activity);
			return "success";
		} else
			return "faile";

	}

	// 设置该活动为推荐活动
	public String torecommend() {
		activityDao.updateRecsign(id);
		return "success";
	}

	// 设置轮播图
	public String Lunbo() {
		activityDao.updateRTu(id);
		return "success";
	}

	// /////////////////////////////////////
	// 活动管理功能///////////////////////////////////////////
	// ///////////////////////////////////// // 查找活动类

	// 添加活动
	@SuppressWarnings("deprecation")
	public String addActivity() throws IOException {
		Activity activity = new Activity();
		activity.setRName(this.getName());
		activity.setRZdcollege(this.getZdcollege());
		activity.setRType(this.getType());
		activity.setRItem(this.getItem());
		activity.setRCredit(this.getCredit());
		activity.setRMaximum(this.getPeopleNum());
		activity.setRStartTime(startTime1);
		activity.setREndTime(endTime1);
		activity.setRStartTimeTwo(startTime2);
		activity.setREndTimeTwo(endTime2);
		//System.out.println("newActivity"+this.getFkTeacherId());
		if (teacherDao.isExist(this.getFkTeacherId()) != null)
			activity.setRTId(this.getFkTeacherId());
		else
			activity.setRTId("770066");
		activity.setRAddress(this.getAddressId());
		activity.setRIntroduction(this.getDesc());
		activity.setRNowmum(0);
		activity.setRPopular(0);
		activity.setRSearchnum(0);
		activity.setRClicknum(0);
		activity.setRTu(5);
		activity.setRRecsign(0);// 初始时为不推荐活动
		switch (this.getCollege()) {
			case "1":
				activity.setRCollege("全部学院");
				break;
			case "2":
				activity.setRCollege("之江学院");
				break;
			case "3":
				activity.setRCollege("信息学院");
				break;
			case "4":
				activity.setRCollege("人文学院");
				break;
			case "5":
				activity.setRCollege("商学院");
				break;
			case "6":
				activity.setRCollege("设计学院");
				break;
			case "7":
				activity.setRCollege("外国语学院");
				break;
			case "8":
				activity.setRCollege("机械学院");
				break;
			case "9":
				activity.setRCollege("中旅学院");
				break;
			case "10":
				activity.setRCollege("建筑学院");
				break;
			case "11":
				activity.setRCollege("理学院");
				break;
			case "12":
				activity.setRCollege("创新创业学院");
				break;
			default:
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(new Date());
		activity.setRCreateTime(Timestamp.valueOf(dateNowStr));
		activity.setRChangeTime(Timestamp.valueOf(dateNowStr));
		activity.setRStatus(2);
		// 上传图片
		if (file1 != null) {
			String path = ServletActionContext.getRequest().getRealPath(
					"/image");
			// 获取后缀名字
			String hz = file1FileName.substring(file1FileName.lastIndexOf("."));
			String newfilename = UUID.randomUUID().toString() + hz;// uuid不重复;
			OutputStream os = new FileOutputStream(new File(path, newfilename));
			String h = "https://sdsy.zzjc.edu.cn/SDSYw/image/" + newfilename;
			activity.setRPicture(h);
			InputStream is = new FileInputStream(file1);
			byte[] buf = new byte[1024];
			int length = 0;
			while (-1 != (length = is.read(buf))) {
				os.write(buf, 0, length);
			}
			PhotoYsImp p = new PhotoYsImp();
			p.photoYs(path + "/" + newfilename, 400, 400);// 对图片进行压缩。
			is.close();
			os.close();
		} else {
			activity.setRPicture("https://sdsy.zzjc.edu.cn/SDSYw/image/1.jpg");
		}
		activityDao.addActivity(activity);
		return "success";
	}

	// 活动发布跳转
	public String publishActivity() {
		teacherlist = teacherDao.allTeacher();
		// System.out.println(teacherlist.get(0).getTName());
		addresslist = addressDao.allAddress();
		return "success";
	}

	// 添加活动图片
	@SuppressWarnings("deprecation")
	public String addActImg() throws IOException {
		if (file2 != null) {
			String path = ServletActionContext.getRequest().getRealPath(
					"/image");
			// 输出流
			// 获取后缀名字
			String hz = file2FileName.substring(file2FileName.lastIndexOf("."));
			String newfilename = UUID.randomUUID().toString() + hz;
			System.out.println(newfilename);
			OutputStream os = new FileOutputStream(new File(path, newfilename));
			String h = "https://sdsy.zzjc.edu.cn/SDSYw/image/" + newfilename;
			// 输入流
			ActivityImg actImg = new ActivityImg();
			actImg.setAiImgUrl(h);
			// System.out.println(activity.getRId());
			actImg.setAiAId(id);
			actImgDao.addActImg(actImg);

			InputStream is = new FileInputStream(file2);
			byte[] buf = new byte[1024];
			int length = 0;
			while (-1 != (length = is.read(buf))) {
				os.write(buf, 0, length);
			}
			PhotoYsImp p = new PhotoYsImp();
			p.photoYs(path + "/" + newfilename, 400, 400);// 对图片进行压缩。
			is.close();
			os.close();
		}

		return "success";
	}

	// 所有活动类别
	@SuppressWarnings("unchecked")
	public String allActItem() {
		Integer pa = (Integer) session.get("itempag");
		pageSum1 = (int) (actItemDao.pageSum() / 7 + 0.99);
		if (pageSum1 == 0)
			pageSum1 = 1;
		if (n == 0)
			pa = 1;
		else if (n == 1 && pa > 0)
			pa = pa - 1;
		else if (n == 2 && pa < pageSum1)
			pa = pa + 1;
		else if (n == 3 && pa < pageSum1)
			pa = pageSum1;

		n = 4;// 保持原位置
		page1 = pa;
		actItemlist = actItemDao.allItem((pa - 1) * 7);
		session.put("itempag", pa);
		return "success";
	}

	//
	public String oneActItem() {
		// System.out.println(itemId);
		acItem = actItemDao.findItem(itemId);
		// System.out.println(acItem.getAname());
		return "success";
	}

	// 修改活动类别
	public String updateActItem() {
		ActivityItem actItem = actItemDao.findItem(itemId);
		actItem.setAname(itemDesc);
		actItem.setArequire(require);
		actItemDao.updateItem(actItem);
		return "success";
	}

	// 预约管理应用主页方法

	// 地址管理
	@SuppressWarnings("unchecked")
	public String allAddress() {
		Integer pa = (Integer) session.get("addresspag");
		pageSum2 = (int) (addressDao.pageSum() / 7 + 0.99);
		if (pageSum2 == 0)
			pageSum2 = 1;
		if (n == 0)
			pa = 1;
		else if (n == 1 && pa > 0)
			pa = pa - 1;
		else if (n == 2 && pa < pageSum2)
			pa = pa + 1;
		else if (n == 3 && pa < pageSum2)
			pa = pageSum2;

		n = 4;// 保持原位置
		page2 = pa;
		addresslist = addressDao.allAddress((pa - 1) * 7);
		session.put("addresspag", pa);
		return "success";
	}


	public String oneAddress() {
		// System.out.println(itemId);
		addre = addressDao.findAddress(addressId);
		// System.out.println(acItem.getAname());
		return "success";
	}
	// 修改地址
	public String updateAddress() {
		System.out.println("修改");
		Address address = addressDao.findAddress(addressId);
		address.setName(name);
		address.setLatitude(this.getLatitude());
		address.setLongitude(this.getLongitude());
		address.setARange(this.getRange());
		addressDao.updateAddress(address);
		return "success";
	}

	// 删除地址
	public String deleteAddress() {
		addressDao.deleteAddress(addressDao.findAddress(addressId));
		return "success";
	}

	// 公告信息
	@SuppressWarnings("unchecked")
	public String allAnnounce() {
		Integer pa = (Integer) session.get("announcepag");
		pageSum3 = (int) (announceDao.PageSum() / 7 + 0.99);
		if (pageSum3 == 0)
			pageSum3 = 1;
		if (n == 0)
			pa = 1;
		else if (n == 1 && pa > 0)
			pa = pa - 1;
		else if (n == 2 && pa < pageSum3)
			pa = pa + 1;
		else if (n == 3 && pa < pageSum3)
			pa = pageSum3;

		n = 4;// 保持原位置
		page3 = pa;
		announcelist = announceDao.allAnnounceDao((pa - 1) * 7);
		session.put("announcepag", pa);
		return "success";
	}

	// 删除公告
	public String deleteAnnounce() {
		announceDao.deleteAnnounce(announceDao.findAnnounce(id));
		return "success";
	}


	//统计跳转
	public String toTongji(){
		listcollege=studentDao.getAllcollege();
		listpro=studentDao.getAllprofession();
		listcla=studentDao.getAllclass();
		return "success";
	}

	// 活动总统计
	public String allActing() throws UnsupportedEncodingException {
		System.out.println("统计");
//		System.out.println(twocollege);
//		System.out.println(new String(twocollege.getBytes("gbk"),"UTF-8"));
//		twocollege=new String(twocollege.getBytes("iso-8859-1"),"UTF-8");
		System.out.println(twocollege);
//		profession=new String(profession.getBytes("iso-8859-1"),"UTF-8");
//		classz=new String(classz.getBytes("iso-8859-1"),"UTF-8");
		activitingslist = actRecordDao.allActingNew(twocollege,nianji,profession,classz,type);
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}

	// 管理员 学生各个模块成绩统计
	public String getStudentsGrade() throws UnsupportedEncodingException{
		System.out.println("学院" + twocollege);
		System.out.println("年级" + nianji);
		System.out.println("专业" + profession);
		System.out.println("班级" + classz);
		activitingslist = actRecordDao.getStudentsGrade(twocollege,nianji,profession,classz);
//		Map<String, Object> map = new HashMap<>();
//		map.put("data", activitingslist);
//		map.put("total", activitingslist.size());
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}

	// //////////////////////////////////////
	// 个人中心功///////////////////////////////////////////
	// 教师列表
	@SuppressWarnings("unchecked")
	public String allTeacher() {
		String loginid = (String) session.get("loginid");
		// System.out.println(loginid);
		int j = 0;
		switch (loginid) {
			case "000007":
				j = 1;
				break;
			case "000008":
				j = 2;
				break;
			case "000009":
				j = 3;
				break;
			case "000010":
				j = 4;
				break;
			case "000011":
				j = 5;
				break;
			case "000012":
				j = 6;
				break;
			case "000013":
				j = 7;
				break;
			case "000014":
				j = 8;
				break;
			case "000015":
				j = 9;
				break;
			case "000016":
				j = 10;
				break;
			default:
				break;
		}
		activitingslist = teacherDao.allTeacher(j);
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}

	// 个人信息
	public String oneTeacher() {
		System.out.println("tId" + td);
		teacher = teacherDao.oneTeahcer(td);
		return "success";

	}

	// 老师权限查看预约信息
	// 修改教师密码+
	public String updateTeacherPsd() {
		teacherDao.updateTeacherPsd(updateId, psdMd5);
		return "success";
	}

	// 修改自己的密码
	public String updatePsd() {

		if (teacherDao.findTeacher(updateId, changepsd)) {
			teacherDao.updateTeacherPsd(updateId, psdMd5);
			s = JSONTools.createJsonObject("s", "success");
			return "success";
		} else {
			s = JSONTools.createJsonObject("s", "faile");
			return "faile";
		}
	}

	// //////////////////////////////////////
	// 学生管理功能///////////////////////////////////////////
	// 学生列表
	@SuppressWarnings("unchecked")
	public String allStudent() {
		String loginid = (String) session.get("loginid");
		int j = 0;
		switch (loginid) {
			case "000007":
				j = 1;
				break;
			case "000008":
				j = 2;
				break;
			case "000009":
				j = 3;
				break;
			case "000010":
				j = 4;
				break;
			case "000011":
				j = 5;
				break;
			case "000012":
				j = 6;
				break;
			case "000013":
				j = 7;
				break;
			case "000014":
				j = 8;
				break;
			case "000015":
				j = 9;
				break;
			case "000016":
				j = 10;
				break;
		}
		activitingslist = studentDao.allStudent(j);
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}


	// 修改学生密码
	public String updateStudentPsd() {
		studentDao.updateStudentPsd(updateId, psdMd5);
		return "success";
	}

	// //////////////////////////////////////
	// 添加功能///////////////////////////////////////////
	// 添加公告
	public String addAnnounce() throws IOException {
		System.out.println("开始添加公告");
		Announce announce = new Announce();
		announce.setAnTitle(this.getTitle());
		announce.setAnContent(this.getDesc());
		announce.setAnTime(getTime());
		// 上传图片
		announceDao.addAnnounce(announce);
		System.out.println("添加完成");
		return "success";

	}

	// 添加公告图片
	@SuppressWarnings("deprecation")
	public String addAnnounceImg() throws IOException {
		AnnounceImg announceImg = new AnnounceImg();
		if (file1 != null) {
			String path = ServletActionContext.getRequest().getRealPath(
					"/image");
			// 获取后缀名字
			String hz = file1FileName.substring(file1FileName.lastIndexOf("."));
			String newfilename = UUID.randomUUID().toString() + hz;// uuid不重复;
			OutputStream os = new FileOutputStream(new File(path, newfilename));
			String h = "http://10.248.6.15:8080/SDSY/image/" + newfilename;
			announceImg.setAnImg(h);
			System.out.println(file1.getAbsolutePath());

			InputStream is = new FileInputStream(file1);
			byte[] buf = new byte[1024];
			int length = 0;
			while (-1 != (length = is.read(buf))) {
				os.write(buf, 0, length);
			}
			PhotoYsImp p = new PhotoYsImp();
			p.photoYs(path + "/" + newfilename, 400, 400);// 对图片进行压缩。
			is.close();
			os.close();
		} else {
			announceImg.setAnImg("https://10.248.6.15:8080/SDSY/image/1.jpg");
		}

		announceImg.setAnId(id);
		announceImgDao.addAnnounceImg(announceImg);
		return "success";
	}

	// 添加地址
	public String addAddress() {

		Address add = new Address();
		add.setName(this.getAddress());
		add.setLatitude(this.getLatitude());
		add.setLongitude(this.getLongitude());
		add.setARange(this.getRange());
		if (add.getARange() == null) {
			add.setARange(10);
		}
		// System.out.println(add.getName()+add.getARange()+add.getLatitude()+add.getLongitude());
		addressDao.newAddress(add);
		return "success";
	}

	// 添加项目类型
	public String addItem() {
		ActivityItem actItem = new ActivityItem();
		actItem.setAaid(type);
		actItem.setAname(itemDesc);
		actItem.setArequire(require);
		actItemDao.addItem(actItem);
		return "success";
	}

	// 清楚本学院老师学生
	public String clearStudents() {
		String loginid = (String) session.get("loginid");
		int j = 0;
		switch (loginid){
			case "000007":
				j = 1;
				break;
			case "000008":
				j = 2;
				break;
			case "000009":
				j = 3;
				break;
			case "000010":
				j = 4;
				break;
			case "000011":
				j = 5;
				break;
			case "000012":
				j = 6;
				break;
			case "000013":
				j = 7;
				break;
			case "000014":
				j = 8;
				break;
			case "000015":
				j = 9;
				break;
		}
		studentDao.deleteStudents(j);
		return "success";
	}

	public String clearTeachers() {
		String loginid = (String) session.get("loginid");
		int j = 0;
		switch (loginid) {
			case "000007":
				j = 1;
				break;
			case "000008":
				j = 2;
				break;
			case "000009":
				j = 3;
				break;
			case "000010":
				j = 4;
				break;
			case "000011":
				j = 5;
				break;
			case "000012":
				j = 6;
				break;
			case "000013":
				j = 7;
				break;
			case "000014":
				j = 8;
				break;
			case "000015":
				j = 9;
				break;
		}
		teacherDao.deleteTeachers(j);
		return "success";
	}
	//模块饼图
	public String bingTu(){
		System.out.println("bingTuid="+id);
		activitingslist = actRecordDao.allActing(0,id);
		Map<String, Object> map2 = new HashMap<String, Object>();
		bingTulist.clear();;
		for(int i=0;i<6;i++){
			map2=activitingslist.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", new String[] { "2分以上", "1-2分", "0-1分" });
			map.put("value", new int[] { (int) map2.get("number2"),
					(int)map2.get("number1"), (int) map2.get("number0") });
			//	System.out.println(map2.get("number0")+","+map2.get("number1")+","+map2.get("number2"));
			bingTulist.add(map);
		}
		System.out.print(bingTulist);
		s = JSONTools.createJsonObject("data", bingTulist);
		return "success";
	}
	public String bingTu2(){
		System.out.println("bingTuid2="+id);
		activitingslist = actRecordDao.allActing(id,0);
		Map<String, Object> map2 = new HashMap<String, Object>();
		bingTulist.clear();;
		for(int i=0;i<9;i++){
			map2=activitingslist.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", new String[] { "2分以上", "1-2分", "0-1分" });
			map.put("value", new int[] { (int) map2.get("number2"),
					(int)map2.get("number1"), (int) map2.get("number0") });
			//	System.out.println(map2.get("number0")+","+map2.get("number1")+","+map2.get("number2"));
			bingTulist.add(map);
		}
		s = JSONTools.createJsonObject("data", bingTulist);
		return "success";
	}

	// 极光推送学生
	@SuppressWarnings("unused")
	public String addJpush() {
		JPushClient SjpushClient = new JPushClient("cef4f0c2fba7cc72e102cc5b",
				"8763eabdfefe40b262a8ffbc");
		// JPushClient jpushClient = new JPushClient(masterSecret,
		// appKey);//第一个参数是masterSecret 第二个是appKey
		PushPayload payloadS = buileMessage(message);
		// System.out.println("添加"+this.getMessage());
		Jpush jpush = new Jpush();
		jpush.setJSt(1);
		jpush.setJMessage(this.getMessage());
		jpush.setCreateTime(getTime());
		jpush.setJAcid(0);
		//
		jpushDao.addmessage(jpush);
		// System.out.println("添加成功");
		try {
			PushResult resultS = SjpushClient.sendPush(payloadS);
			SjpushClient.close();
		} catch (APIConnectionException e) {
			System.out.println(e);
		} catch (APIRequestException e) {
		}
		return "success";
	}

	// 极光推送老师
	@SuppressWarnings("unused")
	public String addJpush1() {
		JPushClient TjpushClient = new JPushClient("f34af0ccf67faf0c1a07e4fe",
				"a3726db9adf50c4e872803b5");
		// JPushClient jpushClient = new JPushClient(masterSecret,
		// appKey);//第一个参数是masterSecret 第二个是appKey
		PushPayload payloadT = buileMessage(message);
		// System.out.println("添加"+message);
		Jpush jpush = new Jpush();
		jpush.setJSt(0);
		jpush.setJMessage(this.getMessage());
		jpush.setCreateTime(getTime());
		jpush.setJAcid(0);
		//

		jpushDao.addmessage(jpush);
		// System.out.println("添加成功");
		try {
			PushResult resultT = TjpushClient.sendPush(payloadT);
			TjpushClient.close();
		} catch (APIConnectionException e) {
			System.out.println(e);
		} catch (APIRequestException e) {
		}
		return "success";
	}

	// 查看所有最近推送
	public String allJpush() {
		//System.out.println("查看推送");
		jpushlist = jpushDao.allMessage();
		return "success";
	}

	public String deleteJpush() {
		//System.out.println("查看推送");
		jpushDao.deleteMessage(id);
		return "success";
	}
	//活动提醒
	public String activityTixing(){
		System.out.println(actid+message);

		List<Activiting> list=actRecordDao.findActStudents(actid);
		Collection<String> alias=new ArrayList<String>();
		Collection<String> aliat=new ArrayList<String>();
		if(list.size()>0){
			for(Activiting acing:list){
				alias.add(acing.getId().getASId());
			}
		}
		Activity activity=activityDao.findoneActivity(list.get(0).getId().getARId());
		if(activity!=null){
			aliat.add(activity.getRTId());
		}
		JPushClient SjpushClient = new JPushClient("cef4f0c2fba7cc72e102cc5b",
				"8763eabdfefe40b262a8ffbc");
		JPushClient TjpushClient = new JPushClient("f34af0ccf67faf0c1a07e4fe",
				"a3726db9adf50c4e872803b5");
		PushPayload payloadS = buileMessage(message,alias);
		PushPayload payloadT = buileMessage(message,aliat);
		Jpush jpush = new Jpush();
		jpush.setJSt(1);
		jpush.setJMessage(this.getMessage());
		jpush.setCreateTime(getTime());
		jpush.setJAcid(list.get(0).getId().getARId());
		jpushDao.addmessage(jpush);
		try {
			PushResult resultS = SjpushClient.sendPush(payloadS);
			SjpushClient.close();
			PushResult resultT = TjpushClient.sendPush(payloadT);
			TjpushClient.close();
		} catch (APIConnectionException e) {
			System.out.println(e);
		} catch (APIRequestException e) {
		}
		System.out.println("添加成功");
		return "success";
	}
	//  "alias" : [ "4314", "892", "4531" ]
	// 发送推送
	private static PushPayload buileMessage(String message) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				// 设置平台
				.setAudience(Audience.all())
				// 按什么发送 tag alia
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(message)
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.build())
								.addPlatformNotification(
										IosNotification.newBuilder().build())
								.build())
				// 发送消息
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
		// 设置ios平台环境 True 表示推送生产环境，False 表示要推送开发环境 默认是开发
	}
	//Collection<String> list=new ArrayList<String>();
	private static PushPayload buileMessage(String message,Collection<String> alia) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				// 设置平台
				.setAudience(Audience.alias(alia))
				// 按什么发送 tag alia
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(message)
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.build())
								.addPlatformNotification(
										IosNotification.newBuilder().build())
								.build())
				// 发送消息
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
		// 设置ios平台环境 True 表示推送生产环境，False 表示要推送开发环境 默认是开发
	}
	// 返回当前时间
	private static Timestamp getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date time = df.parse(df.format(new Date()));
			return new Timestamp(time.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// //////////////////////////////////////
	// 属性///////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	@SuppressWarnings("rawtypes")
	Map session;
	// Map map;
	//
	ActivityItem acItem;
	Address addre;
	Teacher teacher;
	Activity detailactivity;
	Activiting activityrecord;
	// 接口
	AnnounceImgDao announceImgDao;
	StudentDao studentDao;
	TeacherDao teacherDao;
	AddressDao addressDao;
	ActivityDao activityDao;
	ActRecordDao actRecordDao;
	ActItemDao actItemDao;
	ActImgDao actImgDao;
	AnnounceDao announceDao;
	JpushDao jpushDao;
	//

	private int typeId;
	private int actid;
	private int itemId;
	private String td; // 教师ID
	private String updateId;// 修改信息ID
	private int addressId;// 地址ID
	private String stuid;// 寻找指定活动学生成绩
	private int grade;// 活动学生成绩
	private int pageSum, page;// 活动页码
	private int pageSum1, page1;// 活动内别页码
	private int pageSum2, page2;// 地址页码
	private int pageSum3, page3;// 公告页码
	private int pageSum6, page6;// 学生cj列表页码
	private int n;
	private int result;// 登陆状态
	private int range;
	private int id;
	private String twocollege,nianji,profession,classz;
	// list
	List<Map<String, Object>> bingTulist=new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> activitingslist = new ArrayList<Map<String, Object>>();
	private List<Students> studentlist = new ArrayList<Students>();
	private List<Teacher> teacherlist = new ArrayList<Teacher>();
	private List<Address> addresslist = new ArrayList<Address>();
	private List<Activiting> actrecordlist = new ArrayList<Activiting>();
	private List<Activity> activitylist = new ArrayList<Activity>();
	private List<ActivityType> acttypelist = new ArrayList<ActivityType>();
	private List<ActivityItem> actItemlist = new ArrayList<ActivityItem>();
	private List<Announce> announcelist = new ArrayList<Announce>();
	private List<Jpush> jpushlist = new ArrayList<Jpush>();
	List<Students> listcollege=new ArrayList();
	List<Students> listpro=new ArrayList();
	List<Students> listcla=new ArrayList();

	public List<Students> getListcollege() {
		return listcollege;
	}

	public void setListcollege(List<Students> listcollege) {
		this.listcollege = listcollege;
	}

	public List<Students> getListpro() {
		return listpro;
	}

	public void setListpro(List<Students> listpro) {
		this.listpro = listpro;
	}

	public List<Students> getListcla() {
		return listcla;
	}

	public void setListcla(List<Students> listcla) {
		this.listcla = listcla;
	}
	// 文件属性
	private File file1;
	private File file2;
	private String announceFileName;

	private String file1FileName;
	private String file2FileName;
	// 成绩
	private String stuokid;
	private String stugreatid;
	// 属性
	private String actId;
	private String itemDesc;
	private String require;
	private Double latitude;// 地址经纬度
	private Double longitude;
	private String title;// 公告标题
	private String psdMd5;
	private String changepsd;
	private String name;
	private double credit;
	private String fkTeacherId;
	private int item;
	private String picture;
	private int type;
	private int status;
	private String desc;
	private String college;
	private int zdcollege;
	private String address;
	private Timestamp createTime;
	private Timestamp startTime1;
	private Timestamp endTime1;
	private Timestamp startTime2;
	private Timestamp endTime2;
	private Integer peopleNum;
	private short recsign;
	private JSONObject s;
	// JPUSH推送
	private String message;
	private String Ttitle;

	public String getTwocollege() {
		return twocollege;
	}

	public void setTwocollege(String twocollege) {
		this.twocollege = twocollege;
	}

	public String getNianji() {
		return nianji;
	}

	public void setNianji(String nianji) {
		this.nianji = nianji;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getClassz() {
		return classz;
	}

	public void setClassz(String classz) {
		this.classz = classz;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public int getZdcollege() {
		return zdcollege;
	}

	public void setZdcollege(int zdcollege) {
		this.zdcollege = zdcollege;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public ActivityItem getAcItem() {
		return acItem;
	}

	public void setAcItem(ActivityItem acItem) {
		this.acItem = acItem;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getTd() {
		return td;
	}

	public void setTd(String td) {
		this.td = td;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public List<Map<String, Object>> getActivitingslist() {
		return activitingslist;
	}

	public void setActivitingslist(List<Map<String, Object>> activitingslist) {
		this.activitingslist = activitingslist;
	}

	public String getStuokid() {
		return stuokid;
	}

	public void setStuokid(String stuokid) {
		this.stuokid = stuokid;
	}

	public String getStugreatid() {
		return stugreatid;
	}

	public void setStugreatid(String stugreatid) {
		this.stugreatid = stugreatid;
	}

	public String getChangepsd() {
		return changepsd;
	}

	public void setChangepsd(String changepsd) {
		this.changepsd = changepsd;
	}

	public List<Jpush> getJpushlist() {
		return jpushlist;
	}

	public Activiting getActivityrecord() {
		return activityrecord;
	}

	public void setJpushlist(List<Jpush> jpushlist) {
		this.jpushlist = jpushlist;
	}

	public void setActivityrecord(Activiting activityrecord) {
		this.activityrecord = activityrecord;
	}

	public int getActid() {
		return actid;
	}

	public void setActid(int actid) {
		this.actid = actid;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTtitle() {
		return Ttitle;
	}

	public void setTtitle(String ttitle) {
		Ttitle = ttitle;
	}

	public int getPageSum6() {
		return pageSum6;
	}

	public void setPageSum6(int pageSum6) {
		this.pageSum6 = pageSum6;
	}

	public int getPage6() {
		return page6;
	}

	public void setPage6(int page6) {
		this.page6 = page6;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<Activity> getActivitylist() {
		return activitylist;
	}

	public void setActivitylist(List<Activity> activitylist) {
		this.activitylist = activitylist;
	}

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public String getPsdMd5() {
		return psdMd5;
	}

	public void setPsdMd5(String psdMd5) {
		this.psdMd5 = psdMd5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFkTeacherId() {
		return fkTeacherId;
	}

	public void setFkTeacherId(String fkTeacherId) {
		this.fkTeacherId = fkTeacherId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(Timestamp startTime1) {
		this.startTime1 = startTime1;
	}

	public Timestamp getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(Timestamp endTime1) {
		this.endTime1 = endTime1;
	}

	public Address getAddre() {
		return addre;
	}

	public void setAddre(Address addre) {
		this.addre = addre;
	}

	public List<Map<String, Object>> getBingTulist() {
		return bingTulist;
	}

	public void setBingTulist(List<Map<String, Object>> bingTulist) {
		this.bingTulist = bingTulist;
	}

	public Timestamp getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(Timestamp startTime2) {
		this.startTime2 = startTime2;
	}

	public Timestamp getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Timestamp endTime2) {
		this.endTime2 = endTime2;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public short getRecsign() {
		return recsign;
	}

	public void setRecsign(short recsign) {
		this.recsign = recsign;
	}

	public JSONObject getS() {
		return s;
	}

	public void setS(JSONObject s) {
		this.s = s;
	}

	public Activity getDetailactivity() {
		return detailactivity;
	}

	public void setDetailactivity(Activity detailactivity) {
		this.detailactivity = detailactivity;
	}

	public void setAnnounceImgDao(AnnounceImgDao announceImgDao) {
		this.announceImgDao = announceImgDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public void setActRecordDao(ActRecordDao actRecordDao) {
		this.actRecordDao = actRecordDao;
	}

	public void setActItemDao(ActItemDao actItemDao) {
		this.actItemDao = actItemDao;
	}

	public void setActImgDao(ActImgDao actImgDao) {
		this.actImgDao = actImgDao;
	}

	public void setAnnounceDao(AnnounceDao announceDao) {
		this.announceDao = announceDao;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSum1() {
		return pageSum1;
	}

	public void setPageSum1(int pageSum1) {
		this.pageSum1 = pageSum1;
	}

	public int getPage1() {
		return page1;
	}

	public void setPage1(int page1) {
		this.page1 = page1;
	}

	public int getPageSum2() {
		return pageSum2;
	}

	public void setPageSum2(int pageSum2) {
		this.pageSum2 = pageSum2;
	}

	public int getPage2() {
		return page2;
	}

	public void setPage2(int page2) {
		this.page2 = page2;
	}

	public int getPageSum3() {
		return pageSum3;
	}

	public void setPageSum3(int pageSum3) {
		this.pageSum3 = pageSum3;
	}

	public int getPage3() {
		return page3;
	}

	public void setPage3(int page3) {
		this.page3 = page3;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public List<Announce> getAnnouncelist() {
		return announcelist;
	}

	public void setAnnouncelist(List<Announce> announcelist) {
		this.announcelist = announcelist;
	}

	public String getAnnounceFileName() {
		return announceFileName;
	}

	public void setAnnounceFileName(String announceFileName) {
		this.announceFileName = announceFileName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public List<Students> getStudentlist() {
		return studentlist;
	}

	public void setStudentlist(List<Students> studentlist) {
		this.studentlist = studentlist;
	}

	public List<Teacher> getTeacherlist() {
		return teacherlist;
	}

	public void setTeacherlist(List<Teacher> teacherlist) {
		this.teacherlist = teacherlist;
	}

	public List<Address> getAddresslist() {
		return addresslist;
	}

	public void setAddresslist(List<Address> addresslist) {
		this.addresslist = addresslist;
	}

	public List<Activiting> getActrecordlist() {
		return actrecordlist;
	}

	public void setActrecordlist(List<Activiting> actrecordlist) {
		this.actrecordlist = actrecordlist;
	}

	public List<ActivityType> getActtypelist() {
		return acttypelist;
	}

	public void setActtypelist(List<ActivityType> acttypelist) {
		this.acttypelist = acttypelist;
	}

	public List<ActivityItem> getActItemlist() {
		return actItemlist;
	}

	public void setActItemlist(List<ActivityItem> actItemlist) {
		this.actItemlist = actItemlist;
	}

	public String getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}

	public String getFile2FileName() {
		return file2FileName;
	}

	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	public void setJpushDao(JpushDao jpushDao) {
		this.jpushDao = jpushDao;
	}

}
