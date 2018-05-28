package web.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Yuyuelaoshi entity. @author MyEclipse Persistence Tools
 */

public class Yuyuelaoshi implements java.io.Serializable {

	// Fields

	private Integer YId;
	private String YTId;
	private String YAddress;
	private Timestamp YCreateTime;
	private Timestamp YChangeTime;
	private String YEvaluation;
	private Time YStartTime;
	private Time YEndTime;
	private Date YDate;

	// Constructors

	/** default constructor */
	public Yuyuelaoshi() {
	}

	/** full constructor */
	public Yuyuelaoshi(String YTId, String YAddress, Timestamp YCreateTime, Timestamp YChangeTime, String YEvaluation,
			Time YStartTime, Time YEndTime, Date YDate) {
		this.YTId = YTId;
		this.YAddress = YAddress;
		this.YCreateTime = YCreateTime;
		this.YChangeTime = YChangeTime;
		this.YEvaluation = YEvaluation;
		this.YStartTime = YStartTime;
		this.YEndTime = YEndTime;
		this.YDate = YDate;
	}

	// Property accessors

	public Integer getYId() {
		return this.YId;
	}

	public void setYId(Integer YId) {
		this.YId = YId;
	}

	public String getYTId() {
		return this.YTId;
	}

	public void setYTId(String YTId) {
		this.YTId = YTId;
	}

	public String getYAddress() {
		return this.YAddress;
	}

	public void setYAddress(String YAddress) {
		this.YAddress = YAddress;
	}

	public Timestamp getYCreateTime() {
		return this.YCreateTime;
	}

	public void setYCreateTime(Timestamp YCreateTime) {
		this.YCreateTime = YCreateTime;
	}

	public Timestamp getYChangeTime() {
		return this.YChangeTime;
	}

	public void setYChangeTime(Timestamp YChangeTime) {
		this.YChangeTime = YChangeTime;
	}

	public String getYEvaluation() {
		return this.YEvaluation;
	}

	public void setYEvaluation(String YEvaluation) {
		this.YEvaluation = YEvaluation;
	}

	public Time getYStartTime() {
		return this.YStartTime;
	}

	public void setYStartTime(Time YStartTime) {
		this.YStartTime = YStartTime;
	}

	public Time getYEndTime() {
		return this.YEndTime;
	}

	public void setYEndTime(Time YEndTime) {
		this.YEndTime = YEndTime;
	}

	public Date getYDate() {
		return this.YDate;
	}

	public void setYDate(Date YDate) {
		this.YDate = YDate;
	}

}