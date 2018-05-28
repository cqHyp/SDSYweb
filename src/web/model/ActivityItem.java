package web.model;

/**
 * ActivityItem entity. @author MyEclipse Persistence Tools
 */

public class ActivityItem implements java.io.Serializable {

	// Fields

	private Integer AId;
	private Integer aaid;
	private String aname;
	private String arequire;

	// Constructors

	private ActivityType actType;
	
	public ActivityType getActType() {
		return actType;
	}

	public void setActType(ActivityType actType) {
		this.actType = actType;
	}

	/** default constructor */
	public ActivityItem() {
	}

	/** full constructor */
	public ActivityItem(Integer aaid, String aname, String arequire) {
		this.aaid = aaid;
		this.aname = aname;
		this.arequire = arequire;
	}

	// Property accessors

	public Integer getAId() {
		return this.AId;
	}

	public void setAId(Integer AId) {
		this.AId = AId;
	}

	public Integer getAaid() {
		return this.aaid;
	}

	public void setAaid(Integer aaid) {
		this.aaid = aaid;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getArequire() {
		return this.arequire;
	}

	public void setArequire(String arequire) {
		this.arequire = arequire;
	}

}