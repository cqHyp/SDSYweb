package web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.AddressDao;
import web.model.Address;

public class AddressDaoImp extends HibernateDaoSupport implements AddressDao {

	@Override
	public void newAddress(Address add) {
		//System.out.println(add.getName()+add.getARange()+add.getLatitude()+add.getLongitude());
		this.getHibernateTemplate().save(add);
	}

	@Override
	public void deleteAddress(Address address) {
		this.getHibernateTemplate().delete(address);

	}

	@Override
	public double pageSum() {
		Session session = getSession();
		Query query = session.createQuery("select count(*) from Address");
		Long count = (Long) query.uniqueResult();
		double a = count;
		session.close();
		return a;
		
	}

	@Override
	public List<Address> allAddress(int i) {
		Session ssn = getSession();
		Query query = ssn.createQuery("from Address");
		query.setFirstResult(i);
		query.setMaxResults(7);
		List<Address> l = query.list();
		ssn.close();
		return l;
	}

	@Override
	public Address findAddress(int addressId) {
		List<Address> list = getHibernateTemplate().find("from Address where id=?", addressId);
		return list.get(0);
	}

	@Override
	public List<Address> allAddress() {
		Session ssn = getSession();
		Query query = ssn.createQuery("from Address");
		List<Address> l = query.list();
		ssn.close();
		return l;
	}

	@Override
	public void updateAddress(Address address) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(address);
	}

}
