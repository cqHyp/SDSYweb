package web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.ActItemDao;
import web.model.ActivityItem;
import web.model.ActivityType;

public class ActItemDaoImp extends HibernateDaoSupport implements ActItemDao {

	public double pageSum() {
		Session session = getSession();
		Query query = session.createQuery("select count(*) from ActivityItem");
		Long count = (Long) query.uniqueResult();
		double a = count;
		session.close();
		return a;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityItem> allItem(int i) {
		Session ssn = getSession();
		Query query = ssn.createQuery("from ActivityItem");
		query.setFirstResult(i);
		query.setMaxResults(7);
		List<ActivityItem> list = query.list();
		List<ActivityType> type=null;
		int len=list.size();
		Object[] values = new Object[len];
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j <len; j++){
			values[j]=list.get(j).getAaid();
			   if(j != len - 1)
			        sb.append("?,");
			    else
			        sb.append("?");
		}
	 type = getHibernateTemplate().find("FROM ActivityType t WHERE t.aid IN (" + sb.toString() + ")",values);
		for(ActivityItem a: list){
			for(ActivityType entity1 : type)
		        if(a.getAaid().equals(entity1.getAid())){
		            a.setActType(entity1);
		            break;
		        }
		}
		ssn.close();
		return list;
	}

	@Override
	public void addItem(ActivityItem actItem) {
		this.getHibernateTemplate().save(actItem);
	}
	@Override
	public void updateItem(ActivityItem actItem) {
		this.getHibernateTemplate().update(actItem);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActivityItem findItem(int itemId) {
		List<ActivityType> type=null;
		List<ActivityItem> list=getHibernateTemplate().find("from ActivityItem where AId=?",itemId);
		if(list.size()!=0){
				 type=getHibernateTemplate().find("from ActivityType where aid=?",list.get(0).getAaid());
				 list.get(0).setActType(type.get(0)); 
			return list.get(0);
		}else
		return null;
	}

}
