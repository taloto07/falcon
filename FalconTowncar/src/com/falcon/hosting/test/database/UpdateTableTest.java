package com.falcon.hosting.test.database;

import java.util.ArrayList;
import java.util.List;

import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UpdateTableTest {
	private static FalconService service;
	private static Injector injector;
	
	public static void main(String[] args){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);
		// update user
//		User chamnap = service.getUserByEmail("chamnaplim@yahoo.com");
//		
//		if (chamnap != null){
//			System.out.println("First Name: " + chamnap.getFirstname());
//			System.out.println("Last Name: " + chamnap.getLastname());
//			System.out.println("Phone Number: " + chamnap.getPhoneNumber());
//			chamnap.setPhoneNumber("4255558888");
//			service.flush(); // call this function to updating information to database
//		}
		
		// add new user
//		User newUser = new User();
//		newUser.setFirstname("sideth");
//		newUser.setLastname("bun");
//		newUser.setEmail("sideth.bun@gmail.com");
//		newUser.setPassword("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824");
//		newUser.setPhoneNumber("5555555555");
//		
//		List<Group> groups = new ArrayList<Group>();
//		groups.add(service.getGroupByName("owner"));
//		newUser.setGroups(groups);
//		
//		service.addUser(newUser);
	
	}
}
