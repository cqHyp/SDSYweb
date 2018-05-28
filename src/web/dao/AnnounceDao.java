package web.dao;

import java.util.List;

import web.model.Announce;

public interface AnnounceDao {
	public double PageSum() ;
	public List<Announce> allAnnounceDao(int i);
	public void addAnnounce(Announce announce);
	public void deleteAnnounce(Announce announce);
	public Announce findAnnounce(int id);
}
