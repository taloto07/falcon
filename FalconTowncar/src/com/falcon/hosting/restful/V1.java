package com.falcon.hosting.restful;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.json.JSONException;
//import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.falcon.hosting.doa.User;
import com.falcon.hosting.guice.InjectorGuice;
import com.falcon.hosting.restful.helper.UploadImageHelper;
import com.falcon.hosting.restful.helper.UserFromJson;
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
	
	@Path("registerpost")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerpost(String data) throws IOException{
		
		UserFromJson userFromJson = new Gson().fromJson(data, UserFromJson.class);
		
		System.out.println(userFromJson);
		System.out.println(System.getProperty("user.dir"));
		
		byte[] bData = new BASE64Decoder().decodeBuffer(userFromJson.getImage());
		File myImage = new File("../eclipseApps/FalconTowncar/img/myImage.jpeg");
		FileOutputStream fos = new FileOutputStream(myImage);
		fos.write(bData);
		fos.flush();
		fos.close();
		
		if (userFromJson.isNull())
			return Response.status(406).entity("Invalidated").build();
		
		User user = new User();
		userFromJson.copyToUser(user);
		
		if(!service.addUser(user))
			return Response.status(400).entity("User existed.").build();
		
		return Response.status(200).entity("User created.").build();

	}
	
	@Path("uploadimage")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImage(String data) throws IOException{
		UploadImageHelper image = new Gson().fromJson(data, UploadImageHelper.class);
		System.out.println(image.getImage());
		
		byte[] bData = new BASE64Decoder().decodeBuffer(image.getImage());
		File myImage = new File("../eclipseApps/FalconTowncar/img/myImage.jpeg");
		FileOutputStream fos = new FileOutputStream(myImage);
		fos.write(bData);
		fos.flush();
		fos.close();
		
		return Response.status(200).entity("Successfully Uploaded.").build();
	}
	
	@Path("userLogin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String userLogin(String data){
		
//		JSONObject json_obj = null;
//		String str_userId;
//		String str_password;
//		try{
//			json_obj = new JSONObject(data);
//			str_userId = json_obj.getString("userid");
//			str_userId = json_obj.getString("password");
//			
//			//check in database see if match: match return OK, else return FAILED
//			
//		} catch (JSONException e){
//			
//		}
		return "OK";
	}
	
	@Path("userRegister")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String userRegister(String data){
		
//		JSONObject json_obj = null;
//		
//		String str_userId;
//		String str_password;
//		try{
//			json_obj = new JSONObject(data);
//			str_userId = json_obj.getString("userid");
//			str_userId = json_obj.getString("password");
//			
//			//check in database see if match: match return OK, else return FAILED
//			
//		} catch (JSONException e){
//			
//		}
		
		
		
		return "OK";
	}

}
