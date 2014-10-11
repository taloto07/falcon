package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the makes database table.
 * 
 */
@Entity
@Table(name="makes")
@NamedQueries({
	@NamedQuery(name="Make.findAll", query="SELECT m FROM Make m"),
	@NamedQuery(name="Make.findAllASC", query="SELECT m FROM Make m ORDER BY m.name")
})
public class Make implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="make")
	private List<Vehicle> vehicles;

	public Make() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setMake(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setMake(null);

		return vehicle;
	}

}