package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the houses database table.
 * 
 */
@Entity
@Table(name="houses")
@NamedQueries({
	@NamedQuery(name="House.findAll", query="SELECT h FROM House h"),
	@NamedQuery(name="House.findByHouseNumber", query="SELECT h FROM House h WHERE h.number = :number")
})

public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String number;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="house")
	private List<Address> addresses;

	public House() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setHouse(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setHouse(null);

		return address;
	}

}