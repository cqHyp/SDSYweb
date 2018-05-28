package web.dao;

import java.util.List;

import web.model.ActivityItem;

public interface ActItemDao {
	public double pageSum();
	public List<ActivityItem> allItem(int i);
	public void addItem(ActivityItem actItem);
	public void updateItem(ActivityItem actItem);
	public ActivityItem findItem(int itemId);

}
