package web.model;

/**
 * ActivitingImg entity. @author MyEclipse Persistence Tools
 */

public class ActivitingImg implements java.io.Serializable {

	// Fields

	private Integer IId;
	private Integer IAId;
	private String AImgUrl;

	// Constructors

	/** default constructor */
	public ActivitingImg() {
	}

	/** full constructor */
	public ActivitingImg(Integer IAId, String AImgUrl) {
		this.IAId = IAId;
		this.AImgUrl = AImgUrl;
	}

	// Property accessors

	public Integer getIId() {
		return this.IId;
	}

	public void setIId(Integer IId) {
		this.IId = IId;
	}

	public Integer getIAId() {
		return this.IAId;
	}

	public void setIAId(Integer IAId) {
		this.IAId = IAId;
	}

	public String getAImgUrl() {
		return this.AImgUrl;
	}

	public void setAImgUrl(String AImgUrl) {
		this.AImgUrl = AImgUrl;
	}

}