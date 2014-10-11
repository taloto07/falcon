package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the states database table.
 * 
 */
@Entity
@Table(name="states")
@NamedQueries({
	@NamedQuery(name="State.findAll", query="SELECT s FROM State s"),
	@NamedQuery(name="State.findByName", query="SELECT s FROM State s WHERE s.name = :name"),
	@NamedQuery(name="State.findAllASC", query="SELECT s FROM State s ORDER BY s.name ASC")
})

public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String abbreviation;

	private String name;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="state")
	private List<Address> addresses;

	public State() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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
		address.setState(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setState(null);

		return address;
	}

}