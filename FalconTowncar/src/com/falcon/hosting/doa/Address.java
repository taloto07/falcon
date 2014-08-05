package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the addresses database table.
 * 
 */
@Entity
@Table(name="addresses")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="extra_info")
	private String extraInfo;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cities_id")
	private City city;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="countries_id")
	private Country country;

	//bi-directional many-to-one association to House
	@ManyToOne
	@JoinColumn(name="houses_id")
	private House house;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="states_id")
	private State state;

	//bi-directional many-to-one association to Street
	@ManyToOne
	@JoinColumn(name="streets_id")
	private Street street;

	//bi-directional many-to-one association to Zipcode
	@ManyToOne
	@JoinColumn(name="zipcodes_id")
	private Zipcode zipcode;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="address")
	private List<Driver> drivers;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="address1")
	private List<Job> jobs1;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="address2")
	private List<Job> jobs2;

	public Address() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExtraInfo() {
		return this.extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Street getStreet() {
		return this.street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setAddress(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setAddress(null);

		return driver;
	}

	public List<Job> getJobs1() {
		return this.jobs1;
	}

	public void setJobs1(List<Job> jobs1) {
		this.jobs1 = jobs1;
	}

	public Job addJobs1(Job jobs1) {
		getJobs1().add(jobs1);
		jobs1.setAddress1(this);

		return jobs1;
	}

	public Job removeJobs1(Job jobs1) {
		getJobs1().remove(jobs1);
		jobs1.setAddress1(null);

		return jobs1;
	}

	public List<Job> getJobs2() {
		return this.jobs2;
	}

	public void setJobs2(List<Job> jobs2) {
		this.jobs2 = jobs2;
	}

	public Job addJobs2(Job jobs2) {
		getJobs2().add(jobs2);
		jobs2.setAddress2(this);

		return jobs2;
	}

	public Job removeJobs2(Job jobs2) {
		getJobs2().remove(jobs2);
		jobs2.setAddress2(null);

		return jobs2;
	}

}