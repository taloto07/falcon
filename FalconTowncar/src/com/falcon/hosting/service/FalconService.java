package com.falcon.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.falcon.hosting.doa.User;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FalconService {
	@Inject
	private Provider<EntityManager> entityManager;

	//-------------------------------------------User------------------------------------------------------------------
	public List<User> getAllUser(){
		return entityManager.get().createNamedQuery("User.findAll", User.class).getResultList();
	}
	
	public User getUserByEmail(String email){
		try{
			return entityManager.get().createNamedQuery("User.findByEmail", User.class).setParameter("email", email)
					.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	public boolean addUser(User user){
		User u = getUserByEmail(user.getEmail());
		if (u != null) return false;
		
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(user);
		entityManager.get().getTransaction().commit();
		
		return true;
	}
	
	//-------------------------------------------User------------------------------------------------------------------
}
