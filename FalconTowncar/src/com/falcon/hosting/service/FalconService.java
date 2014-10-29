package com.falcon.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.City;
import com.falcon.hosting.doa.Comment;
import com.falcon.hosting.doa.Coordination;
import com.falcon.hosting.doa.Country;
import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.Job;
import com.falcon.hosting.doa.License;
import com.falcon.hosting.doa.LicenseType;
import com.falcon.hosting.doa.Make;
import com.falcon.hosting.doa.Model;
import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.Street;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.Vehicle;
import com.falcon.hosting.doa.Zipcode;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FalconService {
	@Inject
	private Provider<EntityManager> entityManager;
	
	//-------------------------------------------Refresh-------------------------------------------------------------------------------------------------------
	// flushing updating information to database
	public void flush(){
		entityManager.get().getTransaction().begin();
		entityManager.get().getTransaction().commit();
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------

	//-------------------------------------------User----------------------------------------------------------------------------------------------------------
	// get all users
	public List<User> getAllUser(){
		return entityManager.get().createNamedQuery("User.findAll", User.class).getResultList();
	}
	
	// get specific user by email address
	public User getUserByEmail(String email){
		try{
			return entityManager.get().createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	
	// add one user to database
	public boolean addUser(User user){
		// return false if email address already existed in database
		User u = getUserByEmail(user.getEmail());
		if (u != null) return false;
		
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(user);
		entityManager.get().getTransaction().commit();
		
		return true;
	}
	
	// get a group of user by group name
	public List<User> getUsersByGroupName(String groupName){
		try{
			return entityManager.get().createNamedQuery("User.findByGroup", User.class).setParameter("groupName", groupName).getResultList();
		} catch(NoResultException e){
			return null;
		}
	}
	
	// remove user
	public boolean removeUser(User user){
		if (user == null) return false;
		entityManager.get().getTransaction().begin();
		entityManager.get().remove(user);
		entityManager.get().getTransaction().commit();
		return true;
	}
	
	//-------------------------------------------End User------------------------------------------------------------------------------------------------------
	
	
	//-------------------------------------------Group---------------------------------------------------------------------------------------------------------
	// get a list of group
	public List<Group> getAllGroup(){
		return entityManager.get().createNamedQuery("Group.findAll", Group.class).getResultList();
	}
	
	// get a group by group name
	public Group getGroupByName(String groupName){
		try{
			return entityManager.get().createNamedQuery("Group.findByName", Group.class).setParameter("groupName", groupName).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	//-------------------------------------------End Group-----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Driver--------------------------------------------------------------------------------------------------------
	// get all drivers
	public List<Driver> getAllDriver(){
		return entityManager.get().createNamedQuery("Driver.findAll", Driver.class).getResultList();
	}
	
	// get list of driver based on their current vehicle capacity >= capacity variable
	public List<Driver> getDriverCurrentVehicleCapacityGreaterThanOrEqual(int capacity){
		return entityManager.get().createNamedQuery("Driver.findVehicleCapacityGreaterThanOrEqual", Driver.class).setParameter("capacity", capacity)
				.getResultList();
	}
	
	// add driver
	public void addDriver(Driver driver){
		entityManager.get().getTransaction().begin();;
		entityManager.get().persist(driver);
		entityManager.get().getTransaction().commit();
	}
	//-------------------------------------------End Driver----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Customer------------------------------------------------------------------------------------------------------
	// get all customers
	public List<Customer> getAllCustomer(){
		return entityManager.get().createNamedQuery("Customer.findAll", Customer.class).getResultList();
	}
	
	// add customer
	public void addCustomer(Customer customer){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(customer);
		entityManager.get().getTransaction().commit();
	}
	//-------------------------------------------End Customer--------------------------------------------------------------------------------------------------
	
	//-------------------------------------------House---------------------------------------------------------------------------------------------------------
	// get all houses
	public List<House> getAllHouse(){
		return entityManager.get().createNamedQuery("House.findAll", House.class).getResultList();
	}
	
	// get house object by house number
	public House getHouseByNumber(String houseNumber){
		try{
			return entityManager.get().createNamedQuery("House.findByHouseNumber", House.class).setParameter("number", houseNumber).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	// add house number
	public void addHouse(House house){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(house);
		entityManager.get().getTransaction().commit();
	}
	//-------------------------------------------End House-----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Street--------------------------------------------------------------------------------------------------------
	// get all streets
	public List<Street> getAllStreet(){
		return entityManager.get().createNamedQuery("Street.findAll", Street.class).getResultList();
	}
	
	// get street object by street name
	public Street getStreetByName(String name){
		try{
			return entityManager.get().createNamedQuery("Street.findByName", Street.class).setParameter("name", name).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	// add street
	public void addStreet(Street street){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(street);
		entityManager.get().getTransaction().commit();
	}
	//-------------------------------------------End Street----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------City----------------------------------------------------------------------------------------------------------
	// get all cities
	public List<City> getAllCity(){
		return entityManager.get().createNamedQuery("City.findAll", City.class).getResultList();
	}
	
	// get city object by city name
	public City getCityByName(String name){
		try{
			return entityManager.get().createNamedQuery("City.findByName", City.class).setParameter("name", name).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	// add city
	public void addCity(City city){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(city);
		entityManager.get().getTransaction().commit();
	}	
	//-------------------------------------------End City------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------State---------------------------------------------------------------------------------------------------------
	// get all states
	public List<State> getAllState(){
		return entityManager.get().createNamedQuery("State.findAll", State.class).getResultList();
	}
	
	// get all states in ascending order
	public List<State> getAllStateASC(){
		return entityManager.get().createNamedQuery("State.findAllASC", State.class).getResultList();
	}
	
	// get state object by state id
	public State getStateById(int id){
		try{
			return entityManager.get().createNamedQuery("State.findById", State.class).setParameter("id", id).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	// get state object by state name
	public State getStateByName(String name){
		try{
			return entityManager.get().createNamedQuery("State.findByName", State.class).setParameter("name", name).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	//-------------------------------------------End State-----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------State---------------------------------------------------------------------------------------------------------
	// get all zipcodes
	public List<Zipcode> getAllZipcode(){
		return entityManager.get().createNamedQuery("Zipcode.findAll", Zipcode.class).getResultList();
	}
	
	// get zipcode object by zipcode name
	public Zipcode getZipcodeByZipcdoe(int zipcode){
		try{
			return entityManager.get().createNamedQuery("Zipcode.findByZipcode", Zipcode.class).setParameter("zipcode", zipcode).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	// add zipcode
	public void addZipcode(Zipcode zipcode){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(zipcode);
		entityManager.get().getTransaction().commit();
	}
	//-------------------------------------------End State-----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Country-------------------------------------------------------------------------------------------------------
	// get all countries
	public List<Country> getAllCountry(){
		return entityManager.get().createNamedQuery("Country.findAll", Country.class).getResultList();
	}
	
	// get state object by state name
	public Country getCountryByName(String name){
		try{
			return entityManager.get().createNamedQuery("Country.findByName", Country.class).setParameter("name", name).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	//-------------------------------------------End Country---------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Address-------------------------------------------------------------------------------------------------------
	// get all addresses
	public List<Address> getAllAddress(){
		return entityManager.get().createNamedQuery("Address.findAll", Address.class).getResultList();
	}
	
	// add address
	public void addAddress(Address address){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(address);
		entityManager.get().getTransaction().commit();
	}
	//-------------------------------------------End Address---------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Comment-------------------------------------------------------------------------------------------------------
	// get all comments
	public List<Comment> getAllComment(){
		return entityManager.get().createNamedQuery("Comment.findAll", Comment.class).getResultList();
	}
	//-------------------------------------------End Comment---------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Coordination--------------------------------------------------------------------------------------------------
	// get all coordinations
	public List<Coordination> getAllCordination(){
		return entityManager.get().createNamedQuery("Coordination.findAll", Coordination.class).getResultList();
	}
	
	// get coordination by latitude and longitude
	public Coordination getCoordinationByLtnLng(double latitude, double longitude){
		try{
			return entityManager.get().createNamedQuery("Coordination.findByLtnLng", Coordination.class).setParameter("latitude", latitude)
					.setParameter("longitude", longitude).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	// add coordination
	public boolean addCoordination(Coordination coordination){
		if (getCoordinationByLtnLng(coordination.getLatitude(), coordination.getLongitude()) != null)
			return false;
		
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(coordination);
		entityManager.get().getTransaction().commit();
		
		return true;
	}
	//-------------------------------------------End Coordination----------------------------------------------------------------------------------------------
	
	//--------------------------------------------------Make---------------------------------------------------------------------------------------------------
	// get all vehicle makes
	public List<Make> getAllMake(){
		return entityManager.get().createNamedQuery("Make.findAll", Make.class).getResultList();
	}
	
	// get all vehicle makes and sort
	public List<Make> getAllMakeASC(){
		return entityManager.get().createNamedQuery("Make.findAllASC", Make.class).getResultList();
	}
	
	// get make by id
	public Make getMakeById(int id){
		try{
			return entityManager.get().createNamedQuery("Make.findById", Make.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	//--------------------------------------------------End Make-----------------------------------------------------------------------------------------------
	
	//--------------------------------------------------Model--------------------------------------------------------------------------------------------------
	// get all vehicle makes
	public List<Model> getAllModel(){
		return entityManager.get().createNamedQuery("Model.findAll", Model.class).getResultList();
	}
	
	// get all vehicle makes and sort
	public List<Model> getAllModelASC(){
		return entityManager.get().createNamedQuery("Model.findAllASC", Model.class).getResultList();
	}
	
	// get model by id
	public Model getModelById(int id){
		try{
			return entityManager.get().createNamedQuery("Model.findById", Model.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	//--------------------------------------------------End Model----------------------------------------------------------------------------------------------
	
	//--------------------------------------------------Vehicle------------------------------------------------------------------------------------------------
	// get all vehicle
	public List<Vehicle> getAllVehicle(){
		return entityManager.get().createNamedQuery("Vehicle.findAll", Vehicle.class).getResultList();
	}
	
	// get vehicle by id
	public Vehicle getVehicleById(int id){
		try{
			return entityManager.get().createNamedQuery("Vehicle.findById", Vehicle.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	// add vehicle to database
	public void addVehicle(Vehicle vehicle){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(vehicle);
		entityManager.get().getTransaction().commit();
	}
	//--------------------------------------------------End Vehicle--------------------------------------------------------------------------------------------
	
	//--------------------------------------------------License Type-------------------------------------------------------------------------------------------
	// get all licensetype
	public List<LicenseType> getAllLicenseType(){
		return entityManager.get().createNamedQuery("LicenseType.findAll", LicenseType.class).getResultList();
	}
	
	// get licensetype by type
	public LicenseType getLicenseTypeByType(String type){
		try{
			return entityManager.get().createNamedQuery("LicenseType.findByType", LicenseType.class).setParameter("type", type).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		
	}
	//--------------------------------------------------End License Type---------------------------------------------------------------------------------------
	
	//--------------------------------------------------License------------------------------------------------------------------------------------------------
	//get all licenses
	public List<License> getAllLicense(){
		return entityManager.get().createNamedQuery("Lincense.findAll", License.class).getResultList();
	}
	
	// add license
	public void addLicense(License license){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(license);
		entityManager.get().getTransaction().commit();
	}
	//--------------------------------------------------End License--------------------------------------------------------------------------------------------
	
	//--------------------------------------------------Job----------------------------------------------------------------------------------------------------
	// get all jobs
	public List<Job> getAllJob(){
		return entityManager.get().createNamedQuery("Job.findAll", Job.class).getResultList();
	}
	//--------------------------------------------------End Job------------------------------------------------------------------------------------------------
}
