package com.falcon.hosting.test.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UserTest {
	private static FalconService service;
	private static Injector injector;
	
	@BeforeClass
	public static void init(){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);
	}
	
	@Test
	public void addUser(){
		User user = new User();
		user.setFirstname("test");
		user.setLastname("test");
		user.setEmail("test@test.com");
		user.setPassword("123446");
		
		Group driverGroup = service.getGroupByName("driver");
		List<Group> groups = new ArrayList<Group>();
		groups.add(driverGroup);
		
		user.setGroups(groups);
		service.addUser(user);
		
		Driver driver = new Driver();
		driver.setUser(user);
		service.addDriver(driver);
	}
	
	@Test
	public void deleteUser(){
		
	}
	
	@AfterClass
	public static void tearDownClass(){
		User user = service.getUserByEmail("test@test.com");
		service.removeUser(user);
		
		injector = null;
		service = null;
	}
}
