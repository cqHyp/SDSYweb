package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.ActImgDao;

import web.model.ActivityImg;

public class ActImgDaoImp extends HibernateDaoSupport implements ActImgDao {

	@Override
	public void addActImg(ActivityImg actImg) {
		this.getHibernateTemplate().save(actImg);
	}

}
