package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the license_types database table.
 * 
 */
@Entity
@Table(name="license_types")
@NamedQuery(name="LicenseType.findAll", query="SELECT l FROM LicenseType l")
public class LicenseType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String type;

	//bi-directional many-to-one association to Licens
	@OneToMany(mappedBy="licenseType")
	private List<Licens> licenses;

	public LicenseType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Licens> getLicenses() {
		return this.licenses;
	}

	public void setLicenses(List<Licens> licenses) {
		this.licenses = licenses;
	}

	public Licens addLicens(Licens licens) {
		getLicenses().add(licens);
		licens.setLicenseType(this);

		return licens;
	}

	public Licens removeLicens(Licens licens) {
		getLicenses().remove(licens);
		licens.setLicenseType(null);

		return licens;
	}

}