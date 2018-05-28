package web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.AnnounceDao;
import web.model.Announce;

public class AnnounceDaoImp extends HibernateDaoSupport implements AnnounceDao {

	@Override
	public double PageSum() {
		Session session = getSession();
		Query query = session.createQuery("select count(*) from Announce");
		Long count = (Long) query.uniqueResult();
		double a = count;
		session.close();
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announce> allAnnounceDao(int i) {
		Session ssn = getSession();
		Query query = ssn.createQuery("from Announce order by anId desc");
		query.setFirstResult(i);
		query.setMaxResults(7);
		List<Announce> l = query.list();
		ssn.close();
		return l;
	}

	@Override
	public void addAnnounce(Announce announce) {
		this.getHibernateTemplate().save(announce);
		
	}



	@Override
	public void deleteAnnounce(Announce announce) {
		this.getHibernateTemplate().delete(announce);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Announce findAnnounce(int id) {
		// TODO Auto-generated method stub
		List<Announce> list = getHibernateTemplate().find("from Announce a where a.anId=?", id);
		return list.get(0);
	}

}
