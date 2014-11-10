package com.falcon.hosting.test.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.City;
import com.falcon.hosting.doa.Comment;
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
//		String pass = "hello";
//		SHA256Conv sha256Conv = new SHA256Conv(pass);
//		System.out.println(sha256Conv.toString());
		
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);
		
		List<Comment> comments = service.getAllCommentSortByASC();
		
		for (Comment c: comments){
			System.out.println("Comment: " + c.getComment() + " datetime: " + c.getDate());
		}
	}
}

