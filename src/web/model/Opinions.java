package web.model;

/**
 * Opinions entity. @author MyEclipse Persistence Tools
 */

public class Opinions implements java.io.Serializable {

	// Fields

	private Integer id;
	private String opinions;

	// Constructors

	/** default constructor */
	public Opinions() {
	}

	/** full constructor */
	public Opinions(String opinions) {
		this.opinions = opinions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpinions() {
		return this.opinions;
	}

	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}

}