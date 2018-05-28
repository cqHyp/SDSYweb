package web.model;

import java.sql.Timestamp;

/**
 * Students entity. @author MyEclipse Persistence Tools
 */

public class Students implements java.io.Serializable {

	// Fields

	private StudentsId id;
	private String SName;
	private Boolean SSex;
	private String SSchool;
	private String SCollege;
	private String SProfession;
	private String SClass;
	private String SClasstype;
	private String SJobstatus;
	private String STutor;
	private String SPhone;
	private String SIdcard;
	private String SPassword;
	private Timestamp SCreateTime;
	private Timestamp SChangeTime;
	private String SPicture;
	private String SToken;
	private Timestamp STokenendtime;

	// Constructors

	/** default constructor */
	public Students() {
	}

	/** minimal constructor */
	public Students(StudentsId id) {
		this.id = id;
	}

	/** full constructor */
	public Students(StudentsId id, String SName, Boolean SSex, String SSchool, String SCollege, String SProfession,
			String SClass, String SClasstype, String SJobstatus, String STutor, String SPhone, String SIdcard,
			String SPassword, Timestamp SCreateTime, Timestamp SChangeTime, String SPicture, String SToken,
			Timestamp STokenendtime) {
		this.id = id;
		this.SName = SName;
		this.SSex = SSex;
		this.SSchool = SSchool;
		this.SCollege = SCollege;
		this.SProfession = SProfession;
		this.SClass = SClass;
		this.SClasstype = SClasstype;
		this.SJobstatus = SJobstatus;
		this.STutor = STutor;
		this.SPhone = SPhone;
		this.SIdcard = SIdcard;
		this.SPassword = SPassword;
		this.SCreateTime = SCreateTime;
		this.SChangeTime = SChangeTime;
		this.SPicture = SPicture;
		this.SToken = SToken;
		this.STokenendtime = STokenendtime;
	}
	

	// Property accessors

	public Students(StudentsId id, String sName, Boolean sSex, String sSchool, String sCollege, String sProfession,
			String sClass, String sClasstype, String sJobstatus, String sTutor, String sPhone, String sIdcard,
			String sPassword) {
		super();
		this.id = id;
		this.SName = sName;
		this.SSex = sSex;
		this.SSchool = sSchool;
		this.SCollege = sCollege;
		this.SProfession = sProfession;
		this.SClass = sClass;
		this.SClasstype = sClasstype;
		this.SJobstatus = sJobstatus;
		this.STutor = sTutor;
		this.SPhone = sPhone;
		this.SIdcard = sIdcard;
		this.SPassword = sPassword;
	}

	public StudentsId getId() {
		return this.id;
	}

	public void setId(StudentsId id) {
		this.id = id;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public Boolean getSSex() {
		return this.SSex;
	}

	public void setSSex(Boolean SSex) {
		this.SSex = SSex;
	}

	public String getSSchool() {
		return this.SSchool;
	}

	public void setSSchool(String SSchool) {
		this.SSchool = SSchool;
	}

	public String getSCollege() {
		return this.SCollege;
	}

	public void setSCollege(String SCollege) {
		this.SCollege = SCollege;
	}

	public String getSProfession() {
		return this.SProfession;
	}

	public void setSProfession(String SProfession) {
		this.SProfession = SProfession;
	}

	public String getSClass() {
		return this.SClass;
	}

	public void setSClass(String SClass) {
		this.SClass = SClass;
	}

	public String getSClasstype() {
		return this.SClasstype;
	}

	public void setSClasstype(String SClasstype) {
		this.SClasstype = SClasstype;
	}

	public String getSJobstatus() {
		return this.SJobstatus;
	}

	public void setSJobstatus(String SJobstatus) {
		this.SJobstatus = SJobstatus;
	}

	public String getSTutor() {
		return this.STutor;
	}

	public void setSTutor(String STutor) {
		this.STutor = STutor;
	}

	public String getSPhone() {
		return this.SPhone;
	}

	public void setSPhone(String SPhone) {
		this.SPhone = SPhone;
	}

	public String getSIdcard() {
		return this.SIdcard;
	}

	public void setSIdcard(String SIdcard) {
		this.SIdcard = SIdcard;
	}

	public String getSPassword() {
		return this.SPassword;
	}

	public void setSPassword(String SPassword) {
		this.SPassword = SPassword;
	}

	public Timestamp getSCreateTime() {
		return this.SCreateTime;
	}

	public void setSCreateTime(Timestamp SCreateTime) {
		this.SCreateTime = SCreateTime;
	}

	public Timestamp getSChangeTime() {
		return this.SChangeTime;
	}

	public void setSChangeTime(Timestamp SChangeTime) {
		this.SChangeTime = SChangeTime;
	}

	public String getSPicture() {
		return this.SPicture;
	}

	public void setSPicture(String SPicture) {
		this.SPicture = SPicture;
	}

	public String getSToken() {
		return this.SToken;
	}

	public void setSToken(String SToken) {
		this.SToken = SToken;
	}

	public Timestamp getSTokenendtime() {
		return this.STokenendtime;
	}

	public void setSTokenendtime(Timestamp STokenendtime) {
		this.STokenendtime = STokenendtime;
	}

}