package web.model;

/**
 * StudentsId entity. @author MyEclipse Persistence Tools
 */

public class StudentsId implements java.io.Serializable {

	// Fields

	private Integer SId;
	private String SStudentId;

	// Constructors

	/** default constructor */
	public StudentsId() {
	}

	/** full constructor */
	public StudentsId(Integer SId, String SStudentId) {
		this.SId = SId;
		this.SStudentId = SStudentId;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public String getSStudentId() {
		return this.SStudentId;
	}

	public void setSStudentId(String SStudentId) {
		this.SStudentId = SStudentId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StudentsId))
			return false;
		StudentsId castOther = (StudentsId) other;

		return ((this.getSId() == castOther.getSId())
				|| (this.getSId() != null && castOther.getSId() != null && this.getSId().equals(castOther.getSId())))
				&& ((this.getSStudentId() == castOther.getSStudentId())
						|| (this.getSStudentId() != null && castOther.getSStudentId() != null
								&& this.getSStudentId().equals(castOther.getSStudentId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSId() == null ? 0 : this.getSId().hashCode());
		result = 37 * result + (getSStudentId() == null ? 0 : this.getSStudentId().hashCode());
		return result;
	}

}