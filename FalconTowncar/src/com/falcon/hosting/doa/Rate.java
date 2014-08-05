package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rates database table.
 * 
 */
@Entity
@Table(name="rates")
@NamedQuery(name="Rate.findAll", query="SELECT r FROM Rate r")
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="fee_per_mile")
	private double feePerMile;

	public Rate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getFeePerMile() {
		return this.feePerMile;
	}

	public void setFeePerMile(double feePerMile) {
		this.feePerMile = feePerMile;
	}

}