package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the drivers database table.
 * 
 */
@Entity
@Table(name="drivers")
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="bank_account_number")
	private String bankAccountNumber;

	@Column(name="bank_name")
	private String bankName;

	@Temporal(TemporalType.DATE)
	@Column(name="`date of birth`")
	private Date date_of_birth;

	private byte driving;

	private byte shift;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="addresses_id")
	private Address address;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="users_id")
	private User user;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="driver")
	private List<Job> jobs;

	//bi-directional many-to-one association to Licens
	@OneToMany(mappedBy="driver")
	private List<Licens> licenses;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="driver")
	private List<Vehicle> vehicles;

	public Driver() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getDate_of_birth() {
		return this.date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public byte getDriving() {
		return this.driving;
	}

	public void setDriving(byte driving) {
		this.driving = driving;
	}

	public byte getShift() {
		return this.shift;
	}

	public void setShift(byte shift) {
		this.shift = shift;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setDriver(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setDriver(null);

		return job;
	}

	public List<Licens> getLicenses() {
		return this.licenses;
	}

	public void setLicenses(List<Licens> licenses) {
		this.licenses = licenses;
	}

	public Licens addLicens(Licens licens) {
		getLicenses().add(licens);
		licens.setDriver(this);

		return licens;
	}

	public Licens removeLicens(Licens licens) {
		getLicenses().remove(licens);
		licens.setDriver(null);

		return licens;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setDriver(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setDriver(null);

		return vehicle;
	}

}