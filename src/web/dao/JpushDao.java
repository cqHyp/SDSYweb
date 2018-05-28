package web.dao;

import java.util.List;

import web.model.Jpush;

public interface JpushDao {
	public void addmessage(Jpush message);
	public List<Jpush> allMessage();
	public void deleteMessage(int id);

}
