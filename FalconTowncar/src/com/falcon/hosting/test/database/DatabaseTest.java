package com.falcon.hosting.test.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.City;
import com.falcon.hosting.doa.Country;
import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.Job;
import com.falcon.hosting.doa.License;
import com.falcon.hosting.doa.Make;
import com.falcon.hosting.doa.Model;
import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.Street;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.Vehicle;
import com.falcon.hosting.doa.Zipcode;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.falcon.hosting.test.validator.SHA256Conv;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DatabaseTest {
	private static FalconService service;
	private static Injector injector;
	
	public static void main(String[] args) throws ParseException{
		String pass = "hello";
		SHA256Conv sha256Conv = new SHA256Conv(pass);
		System.out.println(sha256Conv.toString());
	}
	
	public void addDriverForm(){
//		String addressN = "7748 134th ave ne";
//		int index = addressN.indexOf(' ');
//		
//		String houseN = addressN.substring(0, index-1);
//		String streetN = addressN.substring(index+1);
//		
//		System.out.println("House: " + houseN);
//		System.out.println("Street: " + streetN);
//
//		injector = Guice.createInjector(new MainModule());
//		injector.getInstance(FalconPersistenceInitializer.class);
//		service = injector.getInstance(FalconService.class);	
//		
//		User driver = service.getUserByEmail("test@driver");
//		
//		List<License> licenses = driver.getDriver().getLicenses();
//		for (License l: licenses){
//			System.out.println(l.getLicenseNumber());
//		}
//		
//		List<Vehicle> vehicles = driver.getDriver().getVehicles();
//		for (Vehicle v: vehicles){
//			System.out.println(v.getLicensePlateNumber());
//		}
		
//		User user = new User();
//		user.setFirstname("firstname");
//		user.setLastname("lastname");
//		user.setEmail("firstname@lastname.com");
//		user.setPassword("12345678");
//		user.setPhoneNumber("2068164969");
//		service.addUser(user);
////		
//		House house = service.getHouseByNumber("751");
//		if (house == null) {
//			house = new House();
//			house.setNumber("751");
//			service.addHouse(house);
//		}
//		
//		Street street = service.getStreetByName("134th ave ne");
//		if (street == null){
//			street = new Street();
//			street.setName("134th ave ne");
//			service.addStreet(street);
//		}
//		
//		City city = service.getCityByName("Redmond");
//		if (city == null){
//			city = new City();
//			city.setName("Redmond");
//			service.addCity(city);
//		}
//		
//		State state = service.getStateById(3);
//		
//		Zipcode zipcode = service.getZipcodeByZipcdoe(98052);
//		if (zipcode == null){
//			zipcode = new Zipcode();
//			zipcode.setZipcode(98052);
//			service.addZipcode(zipcode);
//		}
//		
//		Country country = service.getCountryByName("united states");
//		
//		Address address = new Address();
//		address.setHouse(house);
//		address.setStreet(street);
//		address.setState(state);
//		address.setCity(city);
//		address.setCountry(country);
//		address.setZipcode(zipcode);
//		service.addAddress(address);
//		
//		// add vehicle to database
//		// get vehicle make from database
//		Make make = service.getMakeById(4);
//		
//		// get vehicle model from database
//		Model model = service.getModelById(4);
//		
//		Vehicle vehicle = new Vehicle();
//		vehicle.setMake(make);
//		vehicle.setModel(model);
//		vehicle.setYear(1990);
//		vehicle.setLicensePlateNumber("license plate");
//		vehicle.setCapacity(5);
//		service.addVehicle(vehicle);
//
//		// add driver
//		Driver newDriver = new Driver();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//		Date dob = sdf.parse("05-12-1987");
//		System.out.println(dob);
//		newDriver.setDate_of_birth(dob);
//		newDriver.setBankAccountNumber("1234");
//		newDriver.setBankName("bank of america");
//		newDriver.setUser(user);
//		newDriver.setAddress(address);
//		newDriver.setCurrentVehicle(vehicle);
//		service.addDriver(newDriver);
//		
//		vehicle.setDriver2(newDriver);
//		service.addVehicle(vehicle);
	}
}

