package web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.JpushDao;
import web.model.Jpush;

public class JpushDaoImp extends HibernateDaoSupport implements JpushDao {
	@Override
	public void addmessage(Jpush message) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jpush> allMessage() {
		Session ssn = getSession();
		Query query = ssn.createQuery("from Jpush order by JId desc");
		query.setMaxResults(100);
		List<Jpush> list=query.list();
		ssn.close();
		return list;
	}

	@Override
	public void deleteMessage(int id) {
		// TODO Auto-generated method stub
		Session ssn = getSession();
		Query query = ssn.createQuery("from Jpush where JId=:jid");
		query.setInteger("jid", id);
		List<Jpush> list=query.list();
		if(list.size()>0&&list!=null){
	        this.getHibernateTemplate().delete(list.get(0));
		}
		ssn.close();
	}

}
