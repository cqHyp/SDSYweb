package web.dao.imp;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.ActivityDao;
import web.model.Activity;
import web.model.ActivityType;
import web.model.Address;
import web.model.Teacher;

public class ActivityDaoImp extends HibernateDaoSupport implements ActivityDao {
	// 页数
	@Override
	public double pageSum(int i) {
		Session session = getSession();
		Query query;
		if (i == 0) {
			query = session
					.createQuery("select count(*) from Activity where r_status!=-1");
		} else {
			query = session
					.createQuery("select count(*) from Activity where r_status!=-1 and r_type=?");
			query.setParameter(0, ++i);
		}
		Long b = (Long) query.uniqueResult();
		double a = b;
		session.close();
		return a;
	}

	// 查询所有活动
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> allActivity(int i, int k) {
		List<Teacher> list = null;
		List<ActivityType> list2 = null;
		List<Address> list3 = null;
		Session ssn = getSession();
		Query query;
		if (k == 0) {
			query = ssn
					.createQuery("from Activity where RStatus!=-1 order by RId desc");
		} else {
			query = ssn
					.createQuery("from Activity where RStatus!=-1 and RType=? order by RId desc");
			query.setParameter(0, ++k);
		}
		// System.out.println(i);
		query.setFirstResult(i);
		query.setMaxResults(7);
		List<Activity> l = query.list();
		int len = l.size();
		if (len != 0) {
			String[] values1 = new String[len];
			Object[] values2 = new Object[len];
			Object[] values3 = new Object[len];
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
			// System.out.println(len+","+values1.length);
			for (int j = 0; j < len; j++) {
				values1[j] = l.get(j).getRTId();
				values2[j] = l.get(j).getRType();
				values3[j] = l.get(j).getRAddress();
				if (j != len - 1) {
					sb.append("?,");
					sb2.append("?,");
					sb3.append("?,");
				} else {
					sb.append("?");
					sb2.append("?");
					sb3.append("?");
				}
			}
			// System.out.println(values3[1]+","+values3[3]);
			// System.out.println(sb);

			// 这里的list查询出来的应该包含TeacherId吧
			list = getHibernateTemplate().find(
					"FROM Teacher t WHERE t.id.TTeacherId IN (" + sb.toString()
							+ ")", values1);
			list2 = getHibernateTemplate().find(
					"FROM ActivityType t WHERE t.aid IN (" + sb2.toString()
							+ ")", values2);
			list3 = getHibernateTemplate().find(
					"FROM Address t WHERE t.id IN (" + sb3.toString() + ")",
					values3);

			// System.out.println(list3.size());
			ssn.close();
			// System.out.println(list3.get(0).getName());
			for (Activity a : l) {
				for (Teacher entity : list)
					if (a.getRTId().equals(entity.getId().getTTeacherId())) {
						a.setTeacher(entity);
						break;
					}
				for (ActivityType entity1 : list2)
					if (a.getRType() == entity1.getAid()) {
						a.setActtype(entity1);
						break;
					}
				for (Address entity2 : list3)
					// System.out.println(a.getRAddress()+"lose"+entity2.getId());
					if (a.getRAddress().equals(entity2.getId())) {
						a.setAddress(entity2);
						break;
					}
			}

		}
		// System.out.println(l.get(0).getAddress().getName());
		// System.out.println("a");
		// for (Activity a : l) {
		// //System.out.print("a1");
		// list =
		// getHibernateTemplate().find("from Teacher t where t.id.TTeacherId=?",
		// a.getRTId());
		// // System.out.print("a2");
		// list2 =
		// getHibernateTemplate().find("from ActivityType aid=?",a.getRType());
		// // System.out.print("a3");
		// list3= getHibernateTemplate().find("from Address id=?",
		// a.getRAddress());
		// // System.out.println("a4");
		// a.setTeacher(list.get(0));
		// a.setActtype(list2.get(0));
		// a.setAddress(list3.get(0));
		// }
		// System.out.println("b");
		return l;

	}

	// 查询一个活动详情
	@SuppressWarnings("unchecked")
	@Override
	public Activity findoneActivity(int id) {
		List<Activity> list = getHibernateTemplate().find(
				"from Activity a where a.RId=?", id);
		if (list.size() > 0) {
			List<Teacher> t = getHibernateTemplate().find(
					"from Teacher t where id.TTeacherId=?",
					list.get(0).getRTId());
			if(t.size()>0)
			list.get(0).setTeacher(t.get(0));
			return list.get(0);
		}
		return null;
	}

	// 删除活动
	@Override
	public void deleteActivity(Activity activity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(activity);
	}

	// 修改推荐状态 推荐活动
	@SuppressWarnings("unchecked")
	@Override
	public void updateRecsign(int id) {
		List<Activity> list = getHibernateTemplate().find(
				"from Activity where RId=?", id);
		Activity ac = list.get(0);
		if (ac.getRRecsign() != 1)
			ac.setRRecsign(1);
		else if (ac.getRRecsign() != 0)
			ac.setRRecsign(0);
		this.getHibernateTemplate().update(ac);
	}

	// 添加活动
	@Override
	public void addActivity(Activity activity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(activity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateRTu(int id) {
		List<Activity> list = getHibernateTemplate().find(
				"from Activity where RId=?", id);
		Activity ac = list.get(0);
		int status = ac.getRStatus();
		ac.setRStatus(ac.getRTu());
		ac.setRTu(status);
		this.getHibernateTemplate().update(ac);
	}

	@Override
	public void updateActTime(Activity detailactivity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(detailactivity);
	}

	@Override
	public void addoldAct(Activity act) {
		Session session = getSession();
		//session.save(act);
		Query query=session.createSQLQuery("insert into activity(r_id,r_name) values(?,?)");
		query.setParameter(0, act.getRId());
		query.setParameter(1, act.getRName());
		query.executeUpdate();
		session.close();
		
	}


}
