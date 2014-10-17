package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the licenses database table.
 * 
 */
@Entity
@Table(name="licenses")
@NamedQuery(name="License.findAll", query="SELECT l FROM License l")
public class License implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date expiration;

	@Column(name="license_number")
	private String licenseNumber;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="drivers_id")
	private Driver driver;

	//bi-directional many-to-one association to LicenseType
	@ManyToOne
	@JoinColumn(name="license_type_id")
	private LicenseType licenseType;

	public License() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExpiration() {
		return this.expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public LicenseType getLicenseType() {
		return this.licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

}