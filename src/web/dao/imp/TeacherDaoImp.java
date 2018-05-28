package web.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.TeacherDao;
import web.model.Students;
import web.model.Teacher;
import web.util.MD5Util;

public class TeacherDaoImp extends HibernateDaoSupport implements TeacherDao {

	@SuppressWarnings("unchecked")
	@Override
	public Teacher login(String name, String psd) {
		String[] parm = {name, psd};
		List<Teacher> l = getHibernateTemplate()
				.find("from Teacher t where t.id.TTeacherId = ? and t.TPassword = ?",
						parm);
		if (l.size() > 0) {
			return l.get(0);
		} else
			return  null;
	}

	@Override
	public double pageSum(int j) {
		Session session = getSession();
		System.out.println(j);
		Query	query;
		String b[]={"商学院","信息学院","人文学院","机械学院","外国语学院","建筑学院","设计学院","理学院","中旅学院"};
		if(j==0){
			query = session.createQuery("select count(*) from Teacher where t_power=1");

		}else{
			query= session.createQuery("select count(*) from Teacher where t_power=1 and t_college=?");
			query.setParameter(0, b[j-1]);
		}
		Long c = (Long) query.uniqueResult();
		double a=c;
		session.close();
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> allTeacher(int j) {
		Session ssn = getSession();
		Query query;
		String b[]={"商学院","信息学院","人文学院","机械学院","外国语学院","建筑学院","设计学院","理学院","中旅学院","之江学院"};
		if(j==0){
			query= ssn.createQuery("from Teacher where t_power=1");
		}else{
			query= ssn.createQuery("from Teacher where t_power=1 and t_college=?");
			query.setParameter(0, b[j-1]);
			// System.out.println(b[j-1]);
		}
		//query.setFirstResult(i);
		//query.setMaxResults(7);
		List<Teacher> l= query.list();
		Map<String,Object> map;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Teacher tea:l){
			map=new HashMap<String,Object>();
			map.put("id",tea.getId().getTId());
			map.put("tid",tea.getId().getTTeacherId());
			map.put("name",tea.getTName());
			map.put("college",tea.getTCollege());
			map.put("iphone",tea.getTPhone());
			map.put("in",tea.getTIntroduction());
			map.put("photo","<img src='"+tea.getTPicture()+"' width='80px' heigth='80px' >");
			list.add(map);
		}
		ssn.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateTeacherPsd(String id, String psd) {
		List<Teacher> list = getHibernateTemplate().find("from Teacher t where t.id.TTeacherId=?",id);
		list.get(0).setTPassword(MD5Util.MD5(psd));
		this.getHibernateTemplate().update(list.get(0));
	}

	@Override
	public Teacher isExist(String tId) {
		// TODO Auto-generated method stub
		List<Teacher> list = getHibernateTemplate().find(
				"from Teacher s where s.id.TTeacherId=?", tId);
		if (list.size()>0) {
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updatepsd(Teacher teacher) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(teacher);
	}

	@Override
	public void addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(teacher);

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findTeacher(String fkTeacherId, String changepsd) {
		String parm[]={fkTeacherId,MD5Util.MD5(changepsd)};
		List<Teacher> list = getHibernateTemplate().find("from Teacher t where t.id.TTeacherId=? and "
				+ "t.TPassword=?",parm);
		if(list.size()!=0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> allTeacher() {
		Session ssn = getSession();
		Query query = ssn.createQuery("from Teacher where TPower=1");
		List<Teacher> l = query.list();
		ssn.close();
		return l;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Teacher oneTeahcer(String tId) {
		List<Teacher> list = getHibernateTemplate().find(
				"from Teacher s where s.id.TTeacherId=?", tId);
		if (list.size()!=0) {
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteTeachers(int j) {
		// TODO Auto-generated method stub
		String b[]={"商学院","信息工程学院","人文学院","机械工程学院","外国语学院","建筑学院","设计学院","理学院","中旅学院"};
		List<Teacher> t=getHibernateTemplate().find("from Teacher s where s.TPower=1 and s.TCollege=?",b[j-1]);
		for(Teacher tea:t){
			this.getHibernateTemplate().delete(tea);
		}
	}
}
