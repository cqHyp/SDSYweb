package web.model;

/**
 * ActivityImg entity. @author MyEclipse Persistence Tools
 */

public class ActivityImg implements java.io.Serializable {

	// Fields

	private Integer aiId;
	private Integer aiAId;
	private String aiImgUrl;

	// Constructors

	/** default constructor */
	public ActivityImg() {
	}

	/** full constructor */
	public ActivityImg(Integer aiAId, String aiImgUrl) {
		this.aiAId = aiAId;
		this.aiImgUrl = aiImgUrl;
	}

	// Property accessors

	public Integer getAiId() {
		return this.aiId;
	}

	public void setAiId(Integer aiId) {
		this.aiId = aiId;
	}

	public Integer getAiAId() {
		return this.aiAId;
	}

	public void setAiAId(Integer aiAId) {
		this.aiAId = aiAId;
	}

	public String getAiImgUrl() {
		return this.aiImgUrl;
	}

	public void setAiImgUrl(String aiImgUrl) {
		this.aiImgUrl = aiImgUrl;
	}

}