package com.falcon.hosting.test.database;

import java.util.List;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.Comment;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.Job;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class LoginTest {
	private static FalconService service;
	private static Injector injector;
	
	public static void main(String[] args){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);
		
		List<User> users = service.getUsersByGroupName("customer");
		
		for (User u: users){
			System.out.println(u.getFirstname());
		}
		
		User cham = service.getUserByEmail("chamnaplim@yahoo.com");
		Driver driverCham = cham.getDriver();
		
		List<Job> chamJobs = driverCham.getJobs();
		for (Job j: chamJobs){
			System.out.println(j.getDate());
		}
		
		List<Driver> drivers = service.getDriverCurrentVehicleCapacityGreaterThanOrEqual(3);
		for (Driver d: drivers){
			System.out.println(d.getBankName());
		}
		
		List<House> houses = service.getAllHouse();
		for (House h: houses){
			System.out.println(h.getNumber());
		}
		
		House house750 = service.getHouseByNumber("750");
		System.out.println("house 751 id: " + house750.getId());
		
		List<Comment> comments = null;
		comments = service.getAllComment();
		if (comments.isEmpty()){
			System.out.println("No comment!");
		}else{
			System.out.println("comments is NOT null");
		}
		
		System.out.println();
		
		List<Address> addresses = service.getAllAddress();
		for (Address a: addresses){
			String houseNumber = a.getHouse().getNumber();
			String streetName = a.getStreet().getName();
			String cityName = a.getCity().getName();
			String stateAbb = a.getState().getAbbreviation();
			int zipcode = a.getZipcode().getZipcode();
			
			System.out.println(houseNumber + " " + streetName + " " + cityName + ", " + stateAbb + " " + zipcode);
		}
	}
}
