package web.model;

/**
 * YuyuejingxingId entity. @author MyEclipse Persistence Tools
 */

public class YuyuejingxingId implements java.io.Serializable {

	// Fields

	private Integer YYId;
	private String YSId;

	// Constructors

	/** default constructor */
	public YuyuejingxingId() {
	}

	/** full constructor */
	public YuyuejingxingId(Integer YYId, String YSId) {
		this.YYId = YYId;
		this.YSId = YSId;
	}

	// Property accessors

	public Integer getYYId() {
		return this.YYId;
	}

	public void setYYId(Integer YYId) {
		this.YYId = YYId;
	}

	public String getYSId() {
		return this.YSId;
	}

	public void setYSId(String YSId) {
		this.YSId = YSId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof YuyuejingxingId))
			return false;
		YuyuejingxingId castOther = (YuyuejingxingId) other;

		return ((this.getYYId() == castOther.getYYId()) || (this.getYYId() != null && castOther.getYYId() != null
				&& this.getYYId().equals(castOther.getYYId())))
				&& ((this.getYSId() == castOther.getYSId()) || (this.getYSId() != null && castOther.getYSId() != null
						&& this.getYSId().equals(castOther.getYSId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getYYId() == null ? 0 : this.getYYId().hashCode());
		result = 37 * result + (getYSId() == null ? 0 : this.getYSId().hashCode());
		return result;
	}

}