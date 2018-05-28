package web.model;

import java.sql.Timestamp;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */

public class Activity implements java.io.Serializable {

	// Fields

	private Integer RId;
	private String RName;
	private String RTId;
	private String RPicture;
	private String RIntroduction;
	private Integer RType;
	private Integer RStatus;
	private String RCollege;
	private Integer RAddress;
	private Timestamp RStartTime;
	private Timestamp REndTime;
	private Timestamp RStartTimeTwo;
	private Timestamp REndTimeTwo;
	private Timestamp RCreateTime;
	private Timestamp RChangeTime;
	private Integer RPopular;
	private String RZixun;
	private Integer RSearchnum;
	private Integer RClicknum;
	private Integer RMaximum;
	private Integer RNowmum;
	private Double RCredit;
	private Integer RItem;
	private Integer RRecsign;
	private Integer RTu;
	private Integer RZdcollege;

	// Constructors
	// Constructors
	private Activiting activiting;
	private ActivityType acttype;
	private Address address;
	private Teacher teacher;
	/** default constructor */
	public Activity() {
	}

	public Activity(Integer id) {
		this.RId=id;
	}
	/** full constructor */
	public Activity(String RName, String RTId, String RPicture, String RIntroduction, Integer RType, Integer RStatus,
			String RCollege, Integer RAddress, Timestamp RStartTime, Timestamp REndTime, Timestamp RStartTimeTwo,
			Timestamp REndTimeTwo, Timestamp RCreateTime, Timestamp RChangeTime, Integer RPopular, String RZixun,
			Integer RSearchnum, Integer RClicknum, Integer RMaximum, Integer RNowmum, Double RCredit, Integer RItem,
			Integer RRecsign, Integer RTu, Integer RZdcollege) {
		this.RName = RName;
		this.RTId = RTId;
		this.RPicture = RPicture;
		this.RIntroduction = RIntroduction;
		this.RType = RType;
		this.RStatus = RStatus;
		this.RCollege = RCollege;
		this.RAddress = RAddress;
		this.RStartTime = RStartTime;
		this.REndTime = REndTime;
		this.RStartTimeTwo = RStartTimeTwo;
		this.REndTimeTwo = REndTimeTwo;
		this.RCreateTime = RCreateTime;
		this.RChangeTime = RChangeTime;
		this.RPopular = RPopular;
		this.RZixun = RZixun;
		this.RSearchnum = RSearchnum;
		this.RClicknum = RClicknum;
		this.RMaximum = RMaximum;
		this.RNowmum = RNowmum;
		this.RCredit = RCredit;
		this.RItem = RItem;
		this.RRecsign = RRecsign;
		this.RTu = RTu;
		this.RZdcollege = RZdcollege;
	}

	// Property accessors

	public Integer getRId() {
		return this.RId;
	}

	public Activiting getActiviting() {
		return activiting;
	}

	public void setActiviting(Activiting activiting) {
		this.activiting = activiting;
	}

	public ActivityType getActtype() {
		return acttype;
	}

	public void setActtype(ActivityType acttype) {
		this.acttype = acttype;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setRId(Integer RId) {
		this.RId = RId;
	}

	public String getRName() {
		return this.RName;
	}

	public void setRName(String RName) {
		this.RName = RName;
	}

	public String getRTId() {
		return this.RTId;
	}

	public void setRTId(String RTId) {
		this.RTId = RTId;
	}

	public String getRPicture() {
		return this.RPicture;
	}

	public void setRPicture(String RPicture) {
		this.RPicture = RPicture;
	}

	public String getRIntroduction() {
		return this.RIntroduction;
	}

	public void setRIntroduction(String RIntroduction) {
		this.RIntroduction = RIntroduction;
	}

	public Integer getRType() {
		return this.RType;
	}

	public void setRType(Integer RType) {
		this.RType = RType;
	}

	public Integer getRStatus() {
		return this.RStatus;
	}

	public void setRStatus(Integer RStatus) {
		this.RStatus = RStatus;
	}

	public String getRCollege() {
		return this.RCollege;
	}

	public void setRCollege(String RCollege) {
		this.RCollege = RCollege;
	}

	public Integer getRAddress() {
		return this.RAddress;
	}

	public void setRAddress(Integer RAddress) {
		this.RAddress = RAddress;
	}

	public Timestamp getRStartTime() {
		return this.RStartTime;
	}

	public void setRStartTime(Timestamp RStartTime) {
		this.RStartTime = RStartTime;
	}

	public Timestamp getREndTime() {
		return this.REndTime;
	}

	public void setREndTime(Timestamp REndTime) {
		this.REndTime = REndTime;
	}

	public Timestamp getRStartTimeTwo() {
		return this.RStartTimeTwo;
	}

	public void setRStartTimeTwo(Timestamp RStartTimeTwo) {
		this.RStartTimeTwo = RStartTimeTwo;
	}

	public Timestamp getREndTimeTwo() {
		return this.REndTimeTwo;
	}

	public void setREndTimeTwo(Timestamp REndTimeTwo) {
		this.REndTimeTwo = REndTimeTwo;
	}

	public Timestamp getRCreateTime() {
		return this.RCreateTime;
	}

	public void setRCreateTime(Timestamp RCreateTime) {
		this.RCreateTime = RCreateTime;
	}

	public Timestamp getRChangeTime() {
		return this.RChangeTime;
	}

	public void setRChangeTime(Timestamp RChangeTime) {
		this.RChangeTime = RChangeTime;
	}

	public Integer getRPopular() {
		return this.RPopular;
	}

	public void setRPopular(Integer RPopular) {
		this.RPopular = RPopular;
	}

	public String getRZixun() {
		return this.RZixun;
	}

	public void setRZixun(String RZixun) {
		this.RZixun = RZixun;
	}

	public Integer getRSearchnum() {
		return this.RSearchnum;
	}

	public void setRSearchnum(Integer RSearchnum) {
		this.RSearchnum = RSearchnum;
	}

	public Integer getRClicknum() {
		return this.RClicknum;
	}

	public void setRClicknum(Integer RClicknum) {
		this.RClicknum = RClicknum;
	}

	public Integer getRMaximum() {
		return this.RMaximum;
	}

	public void setRMaximum(Integer RMaximum) {
		this.RMaximum = RMaximum;
	}

	public Integer getRNowmum() {
		return this.RNowmum;
	}

	public void setRNowmum(Integer RNowmum) {
		this.RNowmum = RNowmum;
	}

	public Double getRCredit() {
		return this.RCredit;
	}

	public void setRCredit(Double RCredit) {
		this.RCredit = RCredit;
	}

	public Integer getRItem() {
		return this.RItem;
	}

	public void setRItem(Integer RItem) {
		this.RItem = RItem;
	}

	public Integer getRRecsign() {
		return this.RRecsign;
	}

	public void setRRecsign(Integer RRecsign) {
		this.RRecsign = RRecsign;
	}

	public Integer getRTu() {
		return this.RTu;
	}

	public void setRTu(Integer RTu) {
		this.RTu = RTu;
	}

	public Integer getRZdcollege() {
		return this.RZdcollege;
	}

	public void setRZdcollege(Integer RZdcollege) {
		this.RZdcollege = RZdcollege;
	}

}