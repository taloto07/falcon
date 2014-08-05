package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ccv_number")
	private int ccvNumber;

	@Column(name="credit_card_number")
	private String creditCardNumber;

	@Temporal(TemporalType.DATE)
	private Date expiration;

	//bi-directional many-to-one association to Zipcode
	@ManyToOne
	@JoinColumn(name="zipcodes_id")
	private Zipcode zipcode;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="customer")
	private List<Job> jobs;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="users_id")
	private User user;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCcvNumber() {
		return this.ccvNumber;
	}

	public void setCcvNumber(int ccvNumber) {
		this.ccvNumber = ccvNumber;
	}

	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Date getExpiration() {
		return this.expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setCustomer(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setCustomer(null);

		return job;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}