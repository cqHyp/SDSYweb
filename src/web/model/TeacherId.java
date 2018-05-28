package web.model;

/**
 * TeacherId entity. @author MyEclipse Persistence Tools
 */

public class TeacherId implements java.io.Serializable {

	// Fields

	private Integer TId;
	private String TTeacherId;

	// Constructors

	/** default constructor */
	public TeacherId() {
	}

	/** full constructor */
	public TeacherId(Integer TId, String TTeacherId) {
		this.TId = TId;
		this.TTeacherId = TTeacherId;
	}

	// Property accessors

	public Integer getTId() {
		return this.TId;
	}

	public void setTId(Integer TId) {
		this.TId = TId;
	}

	public String getTTeacherId() {
		return this.TTeacherId;
	}

	public void setTTeacherId(String TTeacherId) {
		this.TTeacherId = TTeacherId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TeacherId))
			return false;
		TeacherId castOther = (TeacherId) other;

		return ((this.getTId() == castOther.getTId())
				|| (this.getTId() != null && castOther.getTId() != null && this.getTId().equals(castOther.getTId())))
				&& ((this.getTTeacherId() == castOther.getTTeacherId())
						|| (this.getTTeacherId() != null && castOther.getTTeacherId() != null
								&& this.getTTeacherId().equals(castOther.getTTeacherId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTId() == null ? 0 : this.getTId().hashCode());
		result = 37 * result + (getTTeacherId() == null ? 0 : this.getTTeacherId().hashCode());
		return result;
	}

}