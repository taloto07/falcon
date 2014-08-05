package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the jobs database table.
 * 
 */
@Entity
@Table(name="jobs")
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cancel;

	private double cost;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double distance;

	private double tip;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="job")
	private List<Comment> comments;

	//bi-directional many-to-one association to Coordination
	@ManyToOne
	@JoinColumn(name="source_coordinations_id")
	private Coordination coordination1;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customers_id")
	private Customer customer;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="destination_addresses_id")
	private Address address1;

	//bi-directional many-to-one association to Coordination
	@ManyToOne
	@JoinColumn(name="`destination-coordinations_id`")
	private Coordination coordination2;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="drivers_id")
	private Driver driver;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="source_addresses_id")
	private Address address2;

	public Job() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCancel() {
		return this.cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTip() {
		return this.tip;
	}

	public void setTip(double tip) {
		this.tip = tip;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setJob(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setJob(null);

		return comment;
	}

	public Coordination getCoordination1() {
		return this.coordination1;
	}

	public void setCoordination1(Coordination coordination1) {
		this.coordination1 = coordination1;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress1() {
		return this.address1;
	}

	public void setAddress1(Address address1) {
		this.address1 = address1;
	}

	public Coordination getCoordination2() {
		return this.coordination2;
	}

	public void setCoordination2(Coordination coordination2) {
		this.coordination2 = coordination2;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Address getAddress2() {
		return this.address2;
	}

	public void setAddress2(Address address2) {
		this.address2 = address2;
	}

}