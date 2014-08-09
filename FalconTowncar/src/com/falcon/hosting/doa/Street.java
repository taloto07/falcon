package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the streets database table.
 * 
 */
@Entity
@Table(name="streets")
@NamedQueries({
	@NamedQuery(name="Street.findAll", query="SELECT s FROM Street s"),
	@NamedQuery(name="Street.findByName", query="SELECT s FROM Street s WHERE s.name = :name")
})

public class Street implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="street")
	private List<Address> addresses;

	public Street() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setStreet(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setStreet(null);

		return address;
	}

}