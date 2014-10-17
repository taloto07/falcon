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
//		User user = new User();
//		user.setFirstname("test");
//		user.setLastname("test");
//		user.setEmail("test@test.com");
//		user.setPassword("123446");
//		user.setPhoneNumber("2068164969");
//		
//		Group driverGroup = service.getGroupByName("driver");
//		List<Group> groups = new ArrayList<Group>();
//		groups.add(driverGroup);
//		
//		user.setGroups(groups);
//		service.addUser(user);
	}
	
	@Test
	public void deleteUser(){
		User user = service.getUserByEmail("dean.wendchester@gmail.com");
		assertNotNull(user);
		
		Driver driver = user.getDriver();
		driver.setCurrentVehicle(null);
		service.addDriver(driver);
		
//		assertNull(user);
	}
	
	@AfterClass
	public static void tearDownClass(){
		injector = null;
		service = null;
	}
}
