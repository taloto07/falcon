package com.falcon.hosting.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.falcon.hosting.doa.User;
import com.falcon.hosting.guice.InjectorGuice;
import com.falcon.hosting.service.FalconService;
import com.google.gson.Gson;

@Path("v1")
public class V1 {
	FalconService service;
	
	public V1(){
		service = InjectorGuice.injector.getInstance(FalconService.class);
	}
	
	@Path("version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStatus(){
		return "Restful service version 1.0";
	}
	
	@Path("getalluser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUser(){
		List<User> users = service.getAllUser();
		
		List<User> myUsers = new ArrayList<User>();
		User user;
		for (User u: users){
			user = new User();
			user.setEmail(u.getEmail());
			user.setFirstname(u.getFirstname());
			user.setLastname(u.getLastname());
			myUsers.add(user);
		}
		
		Map<String, List> userMap = new HashMap<String, List>();
		userMap.put("users", myUsers);
		
		Gson gson = new Gson();
		
		return gson.toJson(userMap);
	}
}
