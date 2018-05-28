package web.model;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */

public class Address implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Double longitude;
	private Double latitude;
	private Integer ARange;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(String name, Double longitude, Double latitude,
			Integer ARange) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.ARange = ARange;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getARange() {
		return this.ARange;
	}

	public void setARange(Integer ARange) {
		this.ARange = ARange;
	}

}