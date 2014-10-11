package com.falcon.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.City;
import com.falcon.hosting.doa.Comment;
import com.falcon.hosting.doa.Coordination;
import com.falcon.hosting.doa.Country;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.Street;
import com.falcon.hosting.doa.User;
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
	//-------------------------------------------End Driver----------------------------------------------------------------------------------------------------
	
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
	//-------------------------------------------End House-----------------------------------------------------------------------------------------------------
	
	//-------------------------------------------Street--------------------------------------------------------------------------------------------------------
	// get all streets
	public List<Street> getAllStreet(){
		return entityManager.get().createNamedQuery("Street.findAll", Street.class).getResultList();
	}
	
	// get street object by street name
	public House getStreetByName(String name){
		try{
			return entityManager.get().createNamedQuery("Street.findByName", House.class).setParameter("name", name).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
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
}
