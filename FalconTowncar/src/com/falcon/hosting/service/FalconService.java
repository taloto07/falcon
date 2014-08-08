package com.falcon.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.falcon.hosting.doa.Comment;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.User;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FalconService {
	@Inject
	private Provider<EntityManager> entityManager;

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
	
	//-------------------------------------------Comment-------------------------------------------------------------------------------------------------------
	// get all comments
	public List<Comment> getAllComment(){
		return entityManager.get().createNamedQuery("Comment.findAll", Comment.class).getResultList();
	}
	//-------------------------------------------End Comment---------------------------------------------------------------------------------------------------
}
