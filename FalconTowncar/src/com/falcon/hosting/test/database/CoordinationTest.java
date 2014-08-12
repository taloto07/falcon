package com.falcon.hosting.test.database;

import com.falcon.hosting.doa.Coordination;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CoordinationTest {
	private static FalconService service;
	private static Injector injector;
	
	public static void main(String[] args){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);
		
		Coordination cordt = new Coordination();
		cordt.setLatitude(47.8668975);
		cordt.setLongitude(-122.2541731);
		
		if (service.addCoordination(cordt) == false){
			System.out.println("This coordination is already existed!");
		}else{
			System.out.println("Coordination is added.");
		}
	}
}
