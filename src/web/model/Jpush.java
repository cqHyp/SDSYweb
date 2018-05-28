package web.model;

import java.sql.Timestamp;

/**
 * Jpush entity. @author MyEclipse Persistence Tools
 */

public class Jpush implements java.io.Serializable {

	// Fields

	private Integer JId;
	private Integer JSt;
	private String JMessage;
	private Timestamp createTime;
	private Integer JAcid;

	// Constructors

	/** default constructor */
	public Jpush() {
	}

	/** full constructor */
	public Jpush(Integer JSt, String JMessage, Timestamp createTime, Integer JAcid) {
		this.JSt = JSt;
		this.JMessage = JMessage;
		this.createTime = createTime;
		this.JAcid = JAcid;
	}

	// Property accessors

	public Integer getJId() {
		return this.JId;
	}

	public void setJId(Integer JId) {
		this.JId = JId;
	}

	public Integer getJSt() {
		return this.JSt;
	}

	public void setJSt(Integer JSt) {
		this.JSt = JSt;
	}

	public String getJMessage() {
		return this.JMessage;
	}

	public void setJMessage(String JMessage) {
		this.JMessage = JMessage;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getJAcid() {
		return this.JAcid;
	}

	public void setJAcid(Integer JAcid) {
		this.JAcid = JAcid;
	}

}