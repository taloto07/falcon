package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the coordinations database table.
 * 
 */
@Entity
@Table(name="coordinations")
@NamedQuery(name="Coordination.findAll", query="SELECT c FROM Coordination c")
public class Coordination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double latitude;

	private double longitude;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="sourceCoordination")
	private List<Job> jobs1;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="destinationCoordination")
	private List<Job> jobs2;

	public Coordination() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<Job> getJobs1() {
		return this.jobs1;
	}

	public void setJobs1(List<Job> jobs1) {
		this.jobs1 = jobs1;
	}

	public Job addJobs1(Job jobs1) {
		getJobs1().add(jobs1);
		jobs1.setSourceCoordination(this);

		return jobs1;
	}

	public Job removeJobs1(Job jobs1) {
		getJobs1().remove(jobs1);
		jobs1.setSourceCoordination(null);

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
		jobs2.setDestinationCoordination(this);

		return jobs2;
	}

	public Job removeJobs2(Job jobs2) {
		getJobs2().remove(jobs2);
		jobs2.setDestinationCoordination(null);

		return jobs2;
	}

}