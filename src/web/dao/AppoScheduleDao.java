package web.dao;

import web.model.Yuyuelaoshi;

public interface AppoScheduleDao {

	public Yuyuelaoshi isExist(Yuyuelaoshi appoSchedule);

	public void updateAppoSchedule(Yuyuelaoshi appoSchedule);

	public void addAppoSchedule(Yuyuelaoshi appoSchedule);

}
