package web.dao;

import java.util.List;

import web.model.Activity;

public interface ActivityDao {
	public double pageSum(int i);	
	public void addActivity(Activity activity);
	public void deleteActivity(Activity activity);
	public Activity findoneActivity(int id);
    public List<Activity> allActivity(int i, int j);
	public void updateRecsign(int id);
	public void updateRTu(int id);
	public void updateActTime(Activity detailactivity);
	public void addoldAct(Activity act);
	
	
}
