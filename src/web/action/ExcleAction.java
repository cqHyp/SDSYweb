package web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import web.dao.ActRecordDao;
import web.dao.ActivityDao;
import web.dao.AppoScheduleDao;
import web.dao.StudentDao;
import web.dao.TeacherDao;
import web.model.Activiting;
import web.model.ActivitingId;
import web.model.Activity;
import web.model.Students;
import web.model.StudentsId;
import web.model.Teacher;
import web.model.TeacherId;
import web.model.Yuyuelaoshi;
import web.util.JSONTools;
import web.util.MD5Util;

public class ExcleAction extends ActionSupport implements SessionAware {
	// 上传学生
	private Timestamp createTime;
	private File importStudent;
	private String importStudentFileName;
	private JSONObject s;
	private StudentDao studentDao;
	// 老师
	private File importTeacher;
	private String importTeacherFileName;
	private TeacherDao teacherDao;
	// 老师预约时间
	private AppoScheduleDao appoScheduleDao;
	private File importTeaTime;
	private String importTeaTimeFileName;
	private int num = 0;
	// 活动
	private int id; // 活动id
	private ActivityDao activityDao;
	private ActRecordDao actRecordDao;
	private int exportType;

	//
	private List<Map<String, Object>> activitingslist = new ArrayList<Map<String, Object>>();

	/// 导入，更新学生数据
	public String importExcleStudent() {
		createTime = getTime();
		String path = "/excleFile";
		String realpath = ServletActionContext.getServletContext().getRealPath(path);
		File target = new File(realpath, importStudentFileName);
		if (target.exists()) {
			target.delete();
		}
		try {
			FileUtils.copyFile(importStudent, target);
			System.out.println("学生信息文件上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fi = new FileInputStream(target);
			Workbook rwb = Workbook.getWorkbook(fi);
			Sheet rs = rwb.getSheet(0);
			int rows = rs.getRows();// 所有行
			int clos = rs.getColumns();// 所有列
			// System.out.println(rows+","+clos);
			Boolean sex = false;
			String stuId, stuName = null, stuSex = null, stuSchool = null, stuCollege = null, stuProfession = null,
					stuClass = null, stuClassType = null, stuJobstatus = null, stuTutor = null, stuPhone = null,
					stuIdcard = null;
			StudentsId stuI = null;
			Students student = null;
			Students stu = null;
			for (int i = 1; i < rows; i++) {
				// System.out.println(i+",,"+rows);
				for (int j = 0; j < clos; j += 250) {
					// System.out.println(i+",,"+rows);
					stuId = rs.getCell(j++, i).getContents();
					stuName = rs.getCell(j++, i).getContents();
					stuSex = rs.getCell(j++, i).getContents();
					stuSchool = rs.getCell(j++, i).getContents();
					stuCollege = rs.getCell(j++, i).getContents();
					stuProfession = rs.getCell(j++, i).getContents();
					stuClass = rs.getCell(j++, i).getContents();
					stuClassType = rs.getCell(j++, i).getContents();
					stuJobstatus = rs.getCell(j++, i).getContents();
					stuTutor = rs.getCell(j++, i).getContents();
					stuPhone = rs.getCell(j++, i).getContents();
					stuIdcard = rs.getCell(j++, i).getContents();
					if (stuSex.equals("男")) {
						sex = false;
					} else if (stuSex.equals("女")) {
						sex = true;
					}
					stuId = stuId.trim();
					stuI = new StudentsId();
					stuI.setSStudentId(stuId);
					student = null;
					student = studentDao.isexist(stuId);
					// if(i==12){
					// System.out.println("fo"+stuId);
					// }
					if (student != null) {// 表中存在该学生，则更新
						stuI.setSId(student.getId().getSId());
						stu = new Students(stuI, stuName, sex, stuSchool, stuCollege, stuProfession, stuClass,
								stuClassType, stuJobstatus, stuTutor, stuPhone, stuIdcard,
								MD5Util.MD5(stuId.substring(stuId.length() - 6)));
						System.out.println(stuId + "--" + stuId.length() + ">>" + stuId.substring(stuId.length() - 6));
						// stu.setSPicture("https://sdsy.zzjc.edu.cn/SDSYw/image/1.jpg");
						stu.setSCreateTime(student.getSCreateTime());
						stu.setSChangeTime(getTime());
						studentDao.updatestudent(stu);
					} else {// 表中不存在该学生，则添加
						System.out.println(stuId + "--" + stuId.length() + ">>" + stuId.substring(stuId.length() - 6));
						stu = new Students(stuI, stuName, sex, stuSchool, stuCollege, stuProfession, stuClass,
								stuClassType, stuJobstatus, stuTutor, stuPhone, stuIdcard,
								MD5Util.MD5(stuId.substring(stuId.length() - 6)));
						stu.setSCreateTime(createTime);
						stu.setSChangeTime(createTime);
						stu.setSPicture("https://sdsy.zzjc.edu.cn/SDSYw/image/1.jpg");
						studentDao.addstudent(stu);
					}
				}
			}
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("a");
			e.printStackTrace();
		} catch (BiffException e) {
			System.out.println("b");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("c");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s = JSONTools.createJsonObject("s", "OK");
		return "success";
	}

	// 导入教师
	public String importExcleTeacher() {
		createTime = getTime();
		System.out.println("导入教师");
		String path = "/excleFile";
		String realpath = ServletActionContext.getServletContext().getRealPath(path);

		File target = new File(realpath, importTeacherFileName);
		if (target.exists()) {
			target.delete();
		}
		try {
			FileUtils.copyFile(importTeacher, target);
			System.out.println("教师信息文件上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fi = new FileInputStream(target);
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet rs = wb.getSheet(0);
			int rows = rs.getRows();// 行
			int clos = rs.getColumns();// 列
			System.out.println(rows + "and" + clos);
			String tName = null, tId = null, tAddress = null, tTelePhone = null, tEmail = null, tCollege = null,
					tPhone = null, tType = null, tdesc = null, tp = null, themeall;
			TeacherId teaId = null;
			Teacher tea = null;
			Teacher teacher = null;
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j += 255) {
					tName = rs.getCell(j++, i).getContents();
					tId = rs.getCell(j++, i).getContents();
					tAddress = rs.getCell(j++, i).getContents();
					tTelePhone = rs.getCell(j++, i).getContents();
					tEmail = rs.getCell(j++, i).getContents();
					tCollege = rs.getCell(j++, i).getContents();
					tPhone = rs.getCell(j++, i).getContents();
					tType = rs.getCell(j++, i).getContents();
					tdesc = rs.getCell(j++, i).getContents();
					tp = rs.getCell(j++, i).getContents();
					String[] t = tType.split(",|，");
					// System.out.println("新增"+tId );
					// System.out.println("新增"+tName);
					// System.out.println("新增"+tPhone );
					// System.out.println("新增"+Integer.parseInt(tTelePhone) );
					themeall = "";
					tId = tId.trim();
					for (int k = 0; k < t.length; k++) {
						switch (t[k]) {
							case "学习":
								themeall += "1,";
								break;
							case "就业":
								themeall += "2,";
								break;
							case "创业":
								themeall += "3,";
								break;
							case "情感":
								themeall += "4,";
								break;
							case "心理":
								themeall += "5,";
								break;
							case "生活":
								themeall += "6,";
								break;
							case "其他":
								themeall += "7,";
								break;
							default:
								break;
						}
						teaId = new TeacherId();
						teaId.setTTeacherId(tId);
						tea = null;// 可以不添加，只是为保证tea判断正确
						tea = teacherDao.isExist(tId);

						if (tea != null) {
							// System.out.println("修改");
							// System.out.println(tea.getId().getTTeacherId());
							teaId.setTId(tea.getId().getTId());
							teacher = new Teacher(teaId, MD5Util.MD5(tId), tName, tPhone, Integer.parseInt(tTelePhone),
									tEmail, tCollege, tAddress, themeall, tp, tdesc);
							teacher.setTFrequency(tea.getTFrequency());
							teacher.setTSearchnum(tea.getTSearchnum());
							teacher.setTPower(tea.getTPower());
							teacher.setTCreateTime(tea.getTCreateTime());
							teacher.setTType(tea.getTType());
							teacher.setTChangeTime(getTime());
							teacherDao.updatepsd(teacher);
						} else {

							teacher = new Teacher(teaId, MD5Util.MD5(tId), tName, tPhone, Integer.parseInt(tTelePhone),
									tEmail, tCollege, tAddress, themeall, tp, tdesc);
							teacher.setTFrequency(0);
							teacher.setTSearchnum(0);
							// teacher.setTPicture("https://sdsy.zzjc.edu.cn/SDSYw/image/1.jpg");
							teacher.setTPower(1);
							teacher.setTType(2);
							teacher.setTCreateTime(createTime);
							teacher.setTChangeTime(createTime);
							teacherDao.addTeacher(teacher);

						}
					}
				}
			}
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = JSONTools.createJsonObject("s", "OK");
		return "success";

	}

	// 导入教师时间
	@SuppressWarnings("unused")
	public String importExcleTeaTime() {
		createTime = getTime();
		String path = "/excleFile";
		String realpath = ServletActionContext.getServletContext().getRealPath(path);
		// System.out.println(realpath);
		File target = new File(realpath, importTeaTimeFileName);
		if (target.exists()) {
			target.delete();
		}
		try {
			FileUtils.copyFile(importTeaTime, target);
			System.out.println("老师预约时间表文件上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fi = new FileInputStream(target);
			Workbook rwb = Workbook.getWorkbook(fi);
			Sheet rs = rwb.getSheet(0);
			int rows = rs.getRows();// 所有行
			int clos = rs.getColumns();// 所有列
			System.out.println(rows + "," + clos);
			String tId = null, tName = null, tAddress = null, tDate = null;
			// te , tf , tg , th , ti, tj , tk , tl, tm, tn, to, tp, tq, tr, ts,
			// tt, tu ;
			Yuyuelaoshi appoSchedule = null;
			Yuyuelaoshi app = null;
			String t[] = new String[17];
			String time[] = { "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00",
					"13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "18:00:00",
					"18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00" };
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					tId = rs.getCell(j++, i).getContents();
					tName = rs.getCell(j++, i).getContents();
					tAddress = rs.getCell(j++, i).getContents();
					tDate = rs.getCell(j++, i).getContents();
					t[0] = rs.getCell(j++, i).getContents();
					t[1] = rs.getCell(j++, i).getContents();
					t[2] = rs.getCell(j++, i).getContents();
					t[3] = rs.getCell(j++, i).getContents();
					t[4] = rs.getCell(j++, i).getContents();
					t[5] = rs.getCell(j++, i).getContents();
					t[6] = rs.getCell(j++, i).getContents();
					t[7] = rs.getCell(j++, i).getContents();
					t[8] = rs.getCell(j++, i).getContents();
					t[9] = rs.getCell(j++, i).getContents();
					t[10] = rs.getCell(j++, i).getContents();
					t[11] = rs.getCell(j++, i).getContents();
					t[12] = rs.getCell(j++, i).getContents();
					t[13] = rs.getCell(j++, i).getContents();
					t[14] = rs.getCell(j++, i).getContents();
					t[15] = rs.getCell(j++, i).getContents();
					t[16] = rs.getCell(j++, i).getContents();
					if (tId == null)
						break;
					appoSchedule = new Yuyuelaoshi();
					appoSchedule.setYTId(tId);
					appoSchedule.setYAddress(tAddress);
					// System.out.println(tDate);
					appoSchedule.setYDate(parseTimestamp(tDate));
					System.out.println(t.length);
					for (int k = 0, len = t.length; k < len; k++) {
						if (k <= 2) {
							num = k;
						} else if (k <= 4 && k >= 3) {
							num = k + 1;
						} else if (k <= 10 && k >= 5) {
							num = k + 2;
						} else {
							num = k + 3;
						}
						if ("T".equals(t[k])) {
							appoSchedule.setYStartTime(Time.valueOf(time[num]));
							appoSchedule.setYEndTime(Time.valueOf(time[num + 1]));
							app = null;
							app = appoScheduleDao.isExist(appoSchedule);
							if (app != null) {
								appoSchedule.setYId(app.getYId());
								appoSchedule.setYCreateTime(app.getYCreateTime());
								appoSchedule.setYChangeTime(getTime());
								appoScheduleDao.updateAppoSchedule(appoSchedule);
							} else {
								appoSchedule.setYCreateTime(createTime);
								appoSchedule.setYChangeTime(createTime);
								appoScheduleDao.addAppoSchedule(appoSchedule);

							}
						}

					}
				}
			}

			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s = JSONTools.createJsonObject("s", "OK");
		return "success";
	}

	private Timestamp parseTimestamp(String tDate) {
		String d = tDate;
		if (tDate.indexOf("/") > 0) {
			d = tDate.replace("/", "-");
		}

		String temp = "20" + d + " 00:00:00";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = df.parse(temp);
			return new Timestamp(time.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("可能没有读到 ");
			return null;
		}
	}

	private Timestamp parseTimestamp2(String tDate) {
		String d = tDate;
		if (tDate.indexOf("/") > 0) {
			d = tDate.replace("/", "-");
		}
		String temp = d;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = df.parse(temp);
			return new Timestamp(time.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" 可能没有读到");
			return null;
		}
	}

	// 导出预约活动学生数据
	public String exportActStudent() {
		try {
			System.out.println("名单导出");
			WritableWorkbook wwb = null;
			// 默认保存在d盘
			String path = ServletActionContext.getServletContext().getRealPath("/excleFile");
			File file = new File(path + "/mingdan.xls");
			// System.out.println(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("sheet0", 0);
			List<Activiting> list = actRecordDao.JionActStudent(id);
			Label labelSId = new Label(0, 0, "学号");
			Label laelName = new Label(1, 0, "姓名");
			Label labelSchool = new Label(2, 0, "学院");
			Label labelClass = new Label(3, 0, "班级");
			Label labelPhone = new Label(4, 0, "联系方式");

			ws.addCell(labelSId);
			ws.addCell(laelName);
			ws.addCell(labelSchool);
			ws.addCell(labelClass);
			ws.addCell(labelPhone);
			int i = 1;
			for (Activiting actR : list) {
				ws.addCell(new Label(0, i, actR.getId().getASId()));
				ws.addCell(new Label(1, i, actR.getStudent().getSName()));
				ws.addCell(new Label(2, i, actR.getStudent().getSCollege()));
				ws.addCell(new Label(3, i, actR.getStudent().getSClass()));
				ws.addCell(new Label(4, i++, actR.getStudent().getSPhone()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s = JSONTools.createJsonObject("data", "OK");
		return "success";
	}

	// 一个活动成绩导出
	public String exportStuActRecord() {
		try {
			System.out.println("成绩名单导出");
			WritableWorkbook wwb = null;
			// 默认保存在d盘
			String path = ServletActionContext.getServletContext().getRealPath("/excleFile");
			File file = new File(path + "/grade.xls");
			// System.out.println(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("sheet0", 0);
			List<Map<String, Object>> list = actRecordDao.allStuRecord(id);
			Label labelSId = new Label(0, 0, "学号");
			Label laelName = new Label(1, 0, "姓名");
			Label labelSchool = new Label(2, 0, "学院");
			Label labelClass = new Label(3, 0, "班级");
			Label labelPhone = new Label(4, 0, "联系方式");
			Label lablegrade = new Label(5, 0, "成绩");
			ws.addCell(labelSId);
			ws.addCell(laelName);
			ws.addCell(labelSchool);
			ws.addCell(labelClass);
			ws.addCell(labelPhone);
			int i = 1;
			for (Map<String, Object> actR : list) {
				ws.addCell(new Label(0, i, (String) actR.get("id")));
				ws.addCell(new Label(1, i, (String) actR.get("name")));
				ws.addCell(new Label(2, i, (String) actR.get("college")));
				ws.addCell(new Label(3, i, (String) actR.get("clasz")));
				ws.addCell(new Label(4, i, (String) actR.get("iphone")));
				ws.addCell(new Label(5, i++, (String) actR.get("grade")));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s = JSONTools.createJsonObject("data", "OK");
		return "success";
	}

	//下载跳转
	public String toDownloadgrade(){
		listcollege=studentDao.getAllcollege();
		listpro=studentDao.getAllprofession();
		listcla=studentDao.getAllclass();

		return "success";
	}


	// 导出学生参加活动成绩
	public String exportRecord() {
		if(students.length()>2){
			students=students.substring(0,students.length()-1);
		}
		// System.out.println("下载"+exportType);
		System.out.println("people:"+students);
		System.out.println(",exportType="+exportType+",="+twocollege+",nianji="+nianji+",profession="+profession+",classz="+classz);
		int y = 0;
		try {
			WritableWorkbook wwb = null;
			// 默认保存在d盘
			String path = ServletActionContext.getServletContext().getRealPath("/excleFile");
			// System.out.println(path);
			File file = new File(path + "/cj.xls");
			if (!file.exists()) {
				file.createNewFile();
			}
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("sheet0", 0);
			List<Map<String, Object>> list =actRecordDao.ActStudentRecordNew(exportType,students,twocollege,nianji,profession,classz);
			//List<Map<String, Object>> list =actRecordDao.ActStudentRecord(exportType, y);
			Label labelSId = new Label(0, 0, "学号");
			Label laelName = new Label(1, 0, "姓名");
			Label labelSchool = new Label(2, 0, "学院");
			Label labelProfession = new Label(3, 0, "专业");
			Label labelClass = new Label(4, 0, "班级");
			ws.addCell(labelSId);
			ws.addCell(laelName);
			ws.addCell(labelSchool);
			ws.addCell(labelProfession);
			ws.addCell(labelClass);
			switch (exportType - 1) {
				case 1: // 导出学生(博雅读书)
					ws.addCell(new Label(5, 0, "书评报告会"));
					ws.addCell(new Label(6, 0, "读书沙龙"));
					ws.addCell(new Label(7, 0, "书评撰写"));
					break;
				case 2:// 导出学生(博雅心情)
					ws.addCell(new Label(5, 0, "理论教学"));
					ws.addCell(new Label(6, 0, "随堂练习"));
					ws.addCell(new Label(7, 0, "实践锻炼"));
					break;
				case 3:// 导出学生(博雅实践)
					ws.addCell(new Label(5, 0, "社会实践"));
					ws.addCell(new Label(6, 0, "志愿服务"));
					break;
				case 4:// 导出学生(博雅讲坛)
					ws.addCell(new Label(5, 0, "之江人文大讲堂"));
					ws.addCell(new Label(6, 0, "之江大讲堂"));
					ws.addCell(new Label(7, 0, "师友讲堂"));
					ws.addCell(new Label(8, 0, "院长下午茶"));
					ws.addCell(new Label(9, 0, "主题沙龙"));
					break;
				case 5:// 导出学生(博雅修身)
					ws.addCell(new Label(5, 0, "兴趣社团"));
					ws.addCell(new Label(6, 0, "阳光长跑"));
					ws.addCell(new Label(7, 0, "文明寝室建设"));
					ws.addCell(new Label(8, 0, "体育文化活动"));
					ws.addCell(new Label(9, 0, "出国深造"));
					break;
				case 6:// 导出学生(博雅视野)
					ws.addCell(new Label(5, 0, "交换学习"));
					ws.addCell(new Label(6, 0, "游学访学"));
					break;
				default:
					System.out.println("default");
					break;
			}
			int num[] = { 8, 8, 7, 10, 9, 7 };
			Map<String, Object> map = null;
			int j;
			for (int i = 0, len = list.size(); i < len; i++) {
				map = list.get(i);
				j = 0;
				for (String k : map.keySet()) {
					// System.out.println(map.get("id")+","+map.get("college"));
					if (j < num[exportType - 2])
						ws.addCell(new Label(j, i + 1, String.valueOf(map.get(k))));
					j++;
				}
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("下载成功");
		s = JSONTools.createJsonObject("data", "OK");
		return "success";
	}

	// 导出学生参加情况
	public String exportTypeRecord() {
		// System.out.println("下载"+exportType);
		System.out.println(",exportType="+exportType+",="+twocollege+",nianji="+nianji+",profession="+profession+",classz="+classz);
		int y = 0;
		try {
			WritableWorkbook wwb = null;
			// 默认保存在d盘
			String path = ServletActionContext.getServletContext().getRealPath("/excleFile");
			// System.out.println(path);
			File file = new File(path + "/typeRecord.xls");
			if (!file.exists()) {
				file.createNewFile();
			}
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("sheet0", 0);
			List<Map<String, Object>> list =actRecordDao.allActingNew(twocollege,nianji,profession,classz,exportType);
			//List<Map<String, Object>> list =actRecordDao.ActStudentRecord(exportType, y);
			ws.addCell(new Label(0, 0, "类别"));
			ws.addCell(new Label(1, 0, "总人数"));
			ws.addCell(new Label(2, 0, "0分人数"));
			ws.addCell(new Label(3, 0, "百分比"));
			ws.addCell(new Label(4, 0, "1分人数"));
			ws.addCell(new Label(5, 0, "百分比"));
			ws.addCell(new Label(6, 0, "2分及以上人数"));
			ws.addCell(new Label(7, 0, "百分比"));
			Map<String, Object> map = null;
			for (int i = 0, len = list.size(); i < len; i++) {
				map=list.get(i);
				ws.addCell(new Label(0, i + 1, String.valueOf(map.get("name"))));
				ws.addCell(new Label(1, i + 1, String.valueOf(map.get("number"))));
				ws.addCell(new Label(2, i + 1, String.valueOf(map.get("number0"))));
				ws.addCell(new Label(3, i + 1, String.valueOf(map.get("bnumber0"))));
				ws.addCell(new Label(4, i + 1, String.valueOf(map.get("number1"))));
				ws.addCell(new Label(5, i + 1, String.valueOf(map.get("bnumber1"))));
				ws.addCell(new Label(6, i + 1, String.valueOf(map.get("number2"))));
				ws.addCell(new Label(7, i + 1, String.valueOf(map.get("bnumber2"))));


			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("下载成功");
		s = JSONTools.createJsonObject("data", "OK");
		return "success";
	}
	//导入往期活动
	public String importOldActivity()  {
		String path = "/excleFile";
		String realpath = ServletActionContext.getServletContext().getRealPath(path);
		File target = new File(realpath, importStudentFileName);
		if (target.exists()) {
			target.delete();
		}
		try {

			FileUtils.copyFile(importStudent, target);
			System.out.println("往期活动文件上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			FileInputStream fi = new FileInputStream(target);
			Workbook rwb = Workbook.getWorkbook(fi);
			Sheet rs = rwb.getSheet(0);
			int rows = rs.getRows();// 所有行
			int clos = rs.getColumns();// 所有列
			// System.out.println(rows+","+clos);
			String acId, actName = null, actTea = null, actPic = null, actIn = null, actType = null, actCollege = null,
					actAdd = null, actStart1 = null, actEnd1 = null, actStart2 = null, actEnd2 = null,
					actCreatTime = null, actNum = null, actCredit = null, actItem = null, actZdcollege = null;

			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j += 250) {
					if(rs.getCell(0, i).getContents().trim()==null){
						System.out.println("空");
						break;
					}
					acId = rs.getCell(j++, i).getContents().trim();
					actName = rs.getCell(j++, i).getContents();
					actTea = rs.getCell(j++, i).getContents();
					actPic = rs.getCell(j++, i).getContents();
					actIn = rs.getCell(j++, i).getContents();
					actType = rs.getCell(j++, i).getContents().trim();
					actCollege = rs.getCell(j++, i).getContents().trim();
					actAdd = rs.getCell(j++, i).getContents().trim();
					actStart1 = rs.getCell(j++, i).getContents();
					actEnd1 = rs.getCell(j++, i).getContents();
					actStart2 = rs.getCell(j++, i).getContents();
					actEnd2 = rs.getCell(j++, i).getContents();
					actCreatTime = rs.getCell(j++, i).getContents();
					actNum = rs.getCell(j++, i).getContents().trim();
					actCredit = rs.getCell(j++, i).getContents().trim();
					actItem = rs.getCell(j++, i).getContents().trim();
					actZdcollege = rs.getCell(j++, i).getContents().trim();
					Activity act = activityDao.findoneActivity(Integer.valueOf(acId));
					if (act == null) {
						act=new Activity(Integer.valueOf(acId));
						act.setRName(actName);
						if(teacherDao.isExist(actTea)!=null)
							act.setRTId(actTea);
						else
							act.setRTId("000001");
						act.setRPicture(actPic);
						act.setRIntroduction(actIn);
						act.setRType(Integer.valueOf(actType));
						act.setRStatus(2);
						act.setRCollege(actCollege);
						act.setRAddress(Integer.valueOf(actAdd));
						act.setRStartTime(Timestamp.valueOf(actStart1));
						act.setREndTime(Timestamp.valueOf(actEnd1));
						act.setRStartTimeTwo(Timestamp.valueOf(actStart2));
						act.setREndTimeTwo(Timestamp.valueOf(actEnd2));
						act.setRCreateTime(Timestamp.valueOf(actCreatTime));
						act.setRMaximum(Integer.valueOf(actNum));
						act.setRCredit(Double.valueOf(actCredit));
						act.setRItem(Integer.valueOf(actItem));
						act.setRTu(5);
						act.setRRecsign(0);
						act.setRZdcollege(Integer.valueOf(actZdcollege));
						System.out.println(act.getRId());
						activityDao.addoldAct(act);
						activityDao.updateActTime(act);
					} else {
						System.out.println("有活动");
					}
				}
			}
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("a");
			e.printStackTrace();
		} catch (BiffException e) {
			System.out.println("b");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("c");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = JSONTools.createJsonObject("s", "OK");
		return "success";
	}
	//导入往期活动成绩
	public String importOldActRecord() {
		String path = "/excleFile";
		String realpath = ServletActionContext.getServletContext().getRealPath(path);
		File target = new File(realpath, importStudentFileName);
		if (target.exists()) {
			target.delete();
		}
		try {
			FileUtils.copyFile(importStudent, target);
			System.out.println("往期活动成绩文件上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fi = new FileInputStream(target);
			Workbook rwb = Workbook.getWorkbook(fi);
			Sheet rs = rwb.getSheet(0);
			int rows = rs.getRows();// 所有行
			int clos = rs.getColumns();// 所有列
			String acRId, actSId = null, actSta = null, actGrade = null, actde1 = null, actTime1 = null, actde2 = null,
					actTime2 = null, actde3 = null, actTime3 = null, actde4 = null, actTime4 = null;

			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j += 250) {
					acRId = rs.getCell(j++, i).getContents().trim();
					actSId = rs.getCell(j++, i).getContents().trim();
					actSta = rs.getCell(j++, i).getContents().trim();
					actGrade = rs.getCell(j++, i).getContents().trim();
					actde1 = rs.getCell(j++, i).getContents();
					actTime1 = rs.getCell(j++, i).getContents();
					actde2 = rs.getCell(j++, i).getContents().trim();
					actTime2 = rs.getCell(j++, i).getContents().trim();
					actde3 = rs.getCell(j++, i).getContents();
					actTime3 = rs.getCell(j++, i).getContents();
					actde4 = rs.getCell(j++, i).getContents();
					actTime4 = rs.getCell(j++, i).getContents();
					//
					Activiting acting=actRecordDao.findactRecordStu(Integer.valueOf(acRId),actSId);
					if (acting == null) {
						acting=new Activiting();
						ActivitingId actingId=new ActivitingId(Integer.valueOf(acRId),actSId);
						acting.setId(actingId);
						acting.setAStatus(Integer.valueOf(actSta));
						acting.setAGrade(Integer.valueOf(actGrade));
						acting.setADevicecode(actde1);
						acting.setAFirsttime(Timestamp.valueOf(actTime1));
						acting.setASecdevicecode(actde2);
						acting.setASecondtime(Timestamp.valueOf(actTime2));
						acting.setAThirddevicecode(actde3);
						acting.setAThirdtime(Timestamp.valueOf(actTime3));
						acting.setAFourdevicecode(actde4);
						acting.setAFourtime(Timestamp.valueOf(actTime4));
						actRecordDao.addActiviting(acting);
					} else {


					}
				}
			}
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("a");
			e.printStackTrace();
		} catch (BiffException e) {
			System.out.println("b");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("c");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s = JSONTools.createJsonObject("s", "OK");
		return "success";
	}

	//汇总成绩表格打印
	public String exportAll(){
		try {
			System.out.println("名单导出");
			WritableWorkbook wwb = null;
			// 默认保存在d盘
			String path = ServletActionContext.getServletContext().getRealPath("/excleFile");
			File file = new File(path + "/huizong.xls");
			// System.out.println(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			Label label1,label2,type1,type2,type3,type4,type5,type6,labelnumber,labelname;
			java.text.NumberFormat format = java.text.NumberFormat.getPercentInstance();
			int i=2;
			String a[] = { "博雅读书", "博雅心情", "博雅实践", "博雅讲坛", "博雅修身", "博雅视野" };
			String b[] = { "商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院" };
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("分学院", 0);
			//表头格式
			labelname = new Label(0, 0, "学院名称");
			labelnumber = new Label(1, 0, "总人数");
			type1 = new Label(2, 0, "博雅读书");
			type2 = new Label(4, 0, "博雅心情");
			type3 = new Label(6, 0, "博雅实践");
			type4 = new Label(8, 0, "博雅讲坛");
			type5 = new Label(10, 0, "博雅修身");
			type6 = new Label(12, 0, "博雅视野");
			while(i<=13){
				label1 = new Label(i++, 1, "完成人数");
				label2 = new Label(i++, 1, "完成率");
				ws.addCell(label1);
				ws.addCell(label2);
			}
			ws.mergeCells(0, 0, 0, 1);
			ws.mergeCells(1, 0, 1, 1);
			ws.mergeCells(2, 0, 3, 0);
			ws.mergeCells(4, 0, 5, 0);
			ws.mergeCells(6, 0, 7, 0);
			ws.mergeCells(8, 0, 9, 0);
			ws.mergeCells(10, 0, 11, 0);
			ws.mergeCells(12, 0, 13, 0);
			ws.addCell(labelname);

			ws.addCell(labelnumber);
			ws.addCell(type1);
			ws.addCell(type2);
			ws.addCell(type3);
			ws.addCell(type4);
			ws.addCell(type5);
			ws.addCell(type6);
			//数据输入
			int k;
			for(int j=1;j<=b.length;j++){
				activitingslist=actRecordDao.allActing(0,j);
				ws.addCell(new Label(0, j+1, b[j-1]));
				ws.addCell(new Label(1, j+1, activitingslist.get(0).get("number").toString()));
				k=2;
				for(Map m:activitingslist){
					//String k= String.valueOf((int)m.get("number1")+(int)m.get("number2"));
					ws.addCell(new Label(k++,j+1,String.valueOf((int)m.get("number1")+(int)m.get("number2"))));
					ws.addCell(new Label(k++,j+1,format.format(Double.valueOf(((int)m.get("number1")+(int)m.get("number2")+0.0)/(int)m.get("number")))));
				}
			}
			//分专业
			WritableSheet ws2 = wwb.createSheet("分专业", 1);
			i = 3;
			labelname = new Label(0, 0, "学院名称");
			labelnumber = new Label(2, 0, "总人数");
			ws2.addCell(new Label(2 + 1, 0, "博雅读书"));
			ws2.addCell(new Label(4 + 1, 0, "博雅心情"));
			ws2.addCell(new Label(6 + 1, 0, "博雅实践"));
			ws2.addCell(new Label(8 + 1, 0, "博雅讲坛"));
			ws2.addCell(new Label(10 + 1, 0, "博雅修身"));
			ws2.addCell(new Label(12 + 1, 0, "博雅视野"));

			while(i<=14){
				label1 = new Label(i++, 1, "完成人数");
				label2 = new Label(i++, 1, "完成率");
				ws2.addCell(label1);
				ws2.addCell(label2);
			}
			ws2.mergeCells(0, 0, 1, 1);
			ws2.mergeCells(2, 0, 2, 1);
			ws2.mergeCells(3, 0, 4, 0);
			ws2.mergeCells(5, 0, 6, 0);
			ws2.mergeCells(7, 0, 8, 0);
			ws2.mergeCells(9, 0, 10, 0);
			ws2.mergeCells(11, 0, 10, 0);
			ws2.mergeCells(13, 0, 14, 0);
			ws2.addCell(labelname);
			ws2.addCell(labelnumber);
			int l=2,y;
			for(int k1=0;k1<b.length;k1++){
				List<Students> s=studentDao.findPro(k1);
				ws2.mergeCells(0, l,0, l+s.size());
				for(Students stu:s){
					ws2.addCell(new Label(1, l++, stu.getSProfession()));
					activitingslist=actRecordDao.allProActing(0, stu.getSProfession());
					//数据输入
					ws2.addCell(new Label(2, l-1, activitingslist.get(0).get("number").toString()));
					y=3;
					for(Map m:activitingslist){
						ws2.addCell(new Label(y++,l-1,String.valueOf((int)m.get("number1")+(int)m.get("number2"))));
						ws2.addCell(new Label(y++,l-1,format.format(Double.valueOf(((int)m.get("number1")+(int)m.get("number2")+0.0)/(int)m.get("number")))));
					}
				}
				ws2.addCell(new Label(1, l++, "合计"));
				ws2.addCell(new Label(0, l-s.size(), b[k1]));

			}
			//分模块
			WritableSheet ws3 = wwb.createSheet("分模块", 2);

			System.out.println("写入完成");
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}
	private static Timestamp getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date time = df.parse(df.format(new Date()));
			return new Timestamp(time.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setSession(Map session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	@SuppressWarnings("rawtypes")
	Map session;
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

	public String findStudents(){
		System.out.println(",="+twocollege+",nianji="+nianji+",profession="+profession+",classz="+classz);
		activitingslist = studentDao.findStudents(twocollege,nianji,profession,classz);
		s = JSONTools.createJsonObject("data", activitingslist);
		return "success";
	}
	//选项
	private String twocollege;
	private String nianji;
	private String profession;
	private String classz;
	private String students;

	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExportType() {
		return exportType;
	}

	public void setExportType(int exportType) {
		this.exportType = exportType;
	}

	public void setAppoScheduleDao(AppoScheduleDao appoScheduleDao) {
		this.appoScheduleDao = appoScheduleDao;
	}

	public void setActRecordDao(ActRecordDao actRecordDao) {
		this.actRecordDao = actRecordDao;
	}

	public JSONObject getS() {
		return s;
	}

	public void setS(JSONObject s) {
		this.s = s;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public File getImportStudent() {
		return importStudent;
	}

	public void setImportStudent(File importStudent) {
		this.importStudent = importStudent;
	}

	public String getImportStudentFileName() {
		return importStudentFileName;
	}

	public void setImportStudentFileName(String importStudentFileName) {
		this.importStudentFileName = importStudentFileName;
	}

	public File getImportTeacher() {
		return importTeacher;
	}

	public void setImportTeacher(File importTeacher) {
		this.importTeacher = importTeacher;
	}

	public String getImportTeacherFileName() {
		return importTeacherFileName;
	}

	public void setImportTeacherFileName(String importTeacherFileName) {
		this.importTeacherFileName = importTeacherFileName;
	}

	public File getImportTeaTime() {
		return importTeaTime;
	}

	public void setImportTeaTime(File importTeaTime) {
		this.importTeaTime = importTeaTime;
	}

	public String getImportTeaTimeFileName() {
		return importTeaTimeFileName;
	}

	public void setImportTeaTimeFileName(String importTeaTimeFileName) {
		this.importTeaTimeFileName = importTeaTimeFileName;
	}

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

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

}
