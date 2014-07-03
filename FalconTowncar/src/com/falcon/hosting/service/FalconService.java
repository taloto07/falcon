package com.falcon.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.falcon.hosting.doa.User;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FalconService {
	@Inject
	private Provider<EntityManager> entityManager;
	
	public List<User> getAllUser(){
		return entityManager.get().createNamedQuery("User.findAll", User.class).getResultList();
	}
}
