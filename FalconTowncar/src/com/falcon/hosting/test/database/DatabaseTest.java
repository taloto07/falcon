package com.falcon.hosting.test.database;

import java.util.List;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Job;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DatabaseTest {
	private static FalconService service;
	private static Injector injector;
	
	public static void main(String[] args){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);	
		
		User chamnap = service.getUserByEmail("chamnaplim@yahoo.com");
		
		Driver custCham = chamnap.getDriver();
		
		List<Job> jobs = custCham.getJobs();
		
		for (Job job: jobs){
			
			System.out.print("Job ID: " + job.getId() );
			System.out.print(" Date: " + job.getDate() );
			System.out.print(" Cancel: " + job.getCancel() );
			System.out.print(" Tip: " + job.getTip() + "\n");
			
			Address sAddr = job.getSourceAddress();
			Address dAddr = job.getDestinationAddress();
			System.out.println("Source Location: " + sAddr.getHouse().getNumber() + " " + sAddr.getStreet().getName() + " " + sAddr.getCity().getName() + ", " + 
					sAddr.getState().getAbbreviation() + " " + sAddr.getZipcode().getZipcode());
			System.out.println("Destination Location: " + dAddr.getHouse().getNumber() + " " + dAddr.getStreet().getName() + " " + dAddr.getCity().getName() + ", " + 
					dAddr.getState().getAbbreviation() + " " + dAddr.getZipcode().getZipcode());
			
			System.out.println("Distance: " + job.getDistance() + " miles");
			
			Driver driver = job.getDriver();
			User uDriver = driver.getUser();
			System.out.println("Driver: " + uDriver.getFirstname() + " " + uDriver.getLastname());
			
			Customer customer = job.getCustomer();
			User uCustomer = customer.getUser();
			System.out.println("Customer: " + uCustomer.getFirstname() + " " + uCustomer.getLastname());
		}
	}
}
