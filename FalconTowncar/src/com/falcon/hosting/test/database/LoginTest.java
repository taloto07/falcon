package com.falcon.hosting.test.database;

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
		
		User u = service.getUserByEmail("chamnaplim@yahoo.com");
		
		if (u == null)
			System.out.println("U is null");
		else
			System.out.println("U is NOT null");
		
		String firstName = u.getFirstname();
		String lastName = u.getLastname();
		
		System.out.println(firstName + " " + lastName);
	}
}
