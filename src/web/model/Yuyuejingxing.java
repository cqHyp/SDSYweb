package web.model;

import java.sql.Timestamp;

/**
 * Yuyuejingxing entity. @author MyEclipse Persistence Tools
 */

public class Yuyuejingxing implements java.io.Serializable {

	// Fields

	private YuyuejingxingId id;
	private String YScript;
	private Integer YStatus;
	private Timestamp YCreateTime;
	private Timestamp YChangeTime;
	private Double YStar;
	private String YEvalution;
	private String YType;
	private String YReson;

	// Constructors

	/** default constructor */
	public Yuyuejingxing() {
	}

	/** minimal constructor */
	public Yuyuejingxing(YuyuejingxingId id) {
		this.id = id;
	}

	/** full constructor */
	public Yuyuejingxing(YuyuejingxingId id, String YScript, Integer YStatus, Timestamp YCreateTime,
			Timestamp YChangeTime, Double YStar, String YEvalution, String YType, String YReson) {
		this.id = id;
		this.YScript = YScript;
		this.YStatus = YStatus;
		this.YCreateTime = YCreateTime;
		this.YChangeTime = YChangeTime;
		this.YStar = YStar;
		this.YEvalution = YEvalution;
		this.YType = YType;
		this.YReson = YReson;
	}

	// Property accessors

	public YuyuejingxingId getId() {
		return this.id;
	}

	public void setId(YuyuejingxingId id) {
		this.id = id;
	}

	public String getYScript() {
		return this.YScript;
	}

	public void setYScript(String YScript) {
		this.YScript = YScript;
	}

	public Integer getYStatus() {
		return this.YStatus;
	}

	public void setYStatus(Integer YStatus) {
		this.YStatus = YStatus;
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

	public Double getYStar() {
		return this.YStar;
	}

	public void setYStar(Double YStar) {
		this.YStar = YStar;
	}

	public String getYEvalution() {
		return this.YEvalution;
	}

	public void setYEvalution(String YEvalution) {
		this.YEvalution = YEvalution;
	}

	public String getYType() {
		return this.YType;
	}

	public void setYType(String YType) {
		this.YType = YType;
	}

	public String getYReson() {
		return this.YReson;
	}

	public void setYReson(String YReson) {
		this.YReson = YReson;
	}

}