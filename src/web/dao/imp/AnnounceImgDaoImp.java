package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.AnnounceImgDao;
import web.model.AnnounceImg;

public class AnnounceImgDaoImp extends HibernateDaoSupport implements AnnounceImgDao {

	@Override
	public void addAnnounceImg(AnnounceImg announceImg) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(announceImg);
	}

}
