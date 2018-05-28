package web.model;

import java.sql.Timestamp;

/**
 * Announce entity. @author MyEclipse Persistence Tools
 */

public class Announce implements java.io.Serializable {

	// Fields

	private Integer anId;
	private String anTitle;
	private Timestamp anTime;
	private String anContent;

	// Constructors

	/** default constructor */
	public Announce() {
	}

	/** full constructor */
	public Announce(String anTitle, Timestamp anTime, String anContent) {
		this.anTitle = anTitle;
		this.anTime = anTime;
		this.anContent = anContent;
	}

	// Property accessors

	public Integer getAnId() {
		return this.anId;
	}

	public void setAnId(Integer anId) {
		this.anId = anId;
	}

	public String getAnTitle() {
		return this.anTitle;
	}

	public void setAnTitle(String anTitle) {
		this.anTitle = anTitle;
	}

	public Timestamp getAnTime() {
		return this.anTime;
	}

	public void setAnTime(Timestamp anTime) {
		this.anTime = anTime;
	}

	public String getAnContent() {
		return this.anContent;
	}

	public void setAnContent(String anContent) {
		this.anContent = anContent;
	}

}