package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vehicles database table.
 * 
 */
@Entity
@Table(name="vehicles")
@NamedQueries({
	@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v"),
	@NamedQuery(name="Vehicle.findById", query="SELECT v FROM Vehicle v WHERE v.id = :id")
})
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int capacity;

	@Column(name="license_plate_number")
	private String licensePlateNumber;

	private int year;

	//bi-directional one-to-one association to Driver
	@OneToOne(mappedBy="currentVehicle")
	private Driver driver1;

	//bi-directional many-to-one association to Make
	@ManyToOne
	@JoinColumn(name="makes_id")
	private Make make;

	//bi-directional many-to-one association to Model
	@ManyToOne
	@JoinColumn(name="models_id")
	private Model model;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="drivers_id")
	private Driver driver2;

	public Vehicle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLicensePlateNumber() {
		return this.licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Driver getDriver1() {
		return this.driver1;
	}

	public void setDriver1(Driver driver1) {
		this.driver1 = driver1;
	}

	public Make getMake() {
		return this.make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Driver getDriver2() {
		return this.driver2;
	}

	public void setDriver2(Driver driver2) {
		this.driver2 = driver2;
	}

}