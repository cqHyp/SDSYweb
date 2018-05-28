package web.model;

/**
 * AnnounceImg entity. @author MyEclipse Persistence Tools
 */

public class AnnounceImg implements java.io.Serializable {

	// Fields

	private Integer anIId;
	private Integer anId;
	private String anImg;

	// Constructors

	/** default constructor */
	public AnnounceImg() {
	}

	/** full constructor */
	public AnnounceImg(Integer anId, String anImg) {
		this.anId = anId;
		this.anImg = anImg;
	}

	// Property accessors

	public Integer getAnIId() {
		return this.anIId;
	}

	public void setAnIId(Integer anIId) {
		this.anIId = anIId;
	}

	public Integer getAnId() {
		return this.anId;
	}

	public void setAnId(Integer anId) {
		this.anId = anId;
	}

	public String getAnImg() {
		return this.anImg;
	}

	public void setAnImg(String anImg) {
		this.anImg = anImg;
	}

}