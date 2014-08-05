package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vehicles database table.
 * 
 */
@Entity
@Table(name="vehicles")
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int capacity;

	@Column(name="license_plate_number")
	private String licensePlateNumber;

	private int year;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="drivers_id")
	private Driver driver;

	//bi-directional many-to-one association to Make
	@ManyToOne
	@JoinColumn(name="makes_id")
	private Make make;

	//bi-directional many-to-one association to Model
	@ManyToOne
	@JoinColumn(name="models_id")
	private Model model;

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

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
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

}