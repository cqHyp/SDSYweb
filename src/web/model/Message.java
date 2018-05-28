package web.model;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer MId;
	private String SId;
	private Integer YId;
	private String message;
	private Integer status;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(String SId, Integer YId, String message, Integer status, Timestamp createtime) {
		this.SId = SId;
		this.YId = YId;
		this.message = message;
		this.status = status;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getMId() {
		return this.MId;
	}

	public void setMId(Integer MId) {
		this.MId = MId;
	}

	public String getSId() {
		return this.SId;
	}

	public void setSId(String SId) {
		this.SId = SId;
	}

	public Integer getYId() {
		return this.YId;
	}

	public void setYId(Integer YId) {
		this.YId = YId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}