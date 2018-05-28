package web.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.AppoScheduleDao;
import web.model.Yuyuelaoshi;

public class AppoScheduleDaoImp extends HibernateDaoSupport implements AppoScheduleDao {

	@SuppressWarnings("unchecked")
	@Override
	public Yuyuelaoshi isExist(Yuyuelaoshi appoSchedule) {
		// TODO Auto-generated method stub 
		  Object [] parm={appoSchedule.getYStartTime(),appoSchedule.getYTId(),appoSchedule.getYDate()};
		List<Yuyuelaoshi> list = getHibernateTemplate().find(
				"from Yuyuelaoshi s where s.YStartTime=? and s.YTId=? and s.YDate=?",parm);
		if (list.size() > 0) {
			//System.out.println("´æÔÚ");
			return list.get(0);
		}else
		return null;
	}

	@Override
	public void updateAppoSchedule(Yuyuelaoshi appoSchedule) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(appoSchedule);
	}

	@Override
	public void addAppoSchedule(Yuyuelaoshi appoSchedule) {
		this.getHibernateTemplate().save(appoSchedule);

	}

}
