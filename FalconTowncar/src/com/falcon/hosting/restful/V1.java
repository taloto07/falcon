package com.falcon.hosting.restful;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.doa.Job;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.Vehicle;
import com.falcon.hosting.guice.InjectorGuice;
import com.falcon.hosting.restful.helper.UploadImageHelper;
import com.falcon.hosting.restful.helper.UserFromJson;
import com.falcon.hosting.service.FalconService;
import com.google.gson.Gson;


@Path("v1")
public class V1 {
	FalconService service;
	final String projectId = "AIzaSyAb9uvsLAhq80R-gYBBStg8H-xTqopvxwk";
	
	public V1(){
		service = InjectorGuice.injector.getInstance(FalconService.class);
	}
	
	@Path("version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStatus(){
		return "Restful service version 1.0";
	}
	
	@Path("user")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserDetailInfo(String data) throws JSONException{
		JSONObject json_object = new JSONObject(data);
		String email = json_object.getString("email");
		
		User user = service.getUserByEmail(email);
		
		Map<String, Object> responsedUser = new HashMap<String, Object>();
		responsedUser.put("firstname", user.getFirstname());
		responsedUser.put("lastname", user.getLastname());
		responsedUser.put("email", user.getEmail());
		responsedUser.put("phone", user.getPhoneNumber());
		
		if (user.getDriver() != null){	// driver User
			Date dob = user.getDriver().getDate_of_birth();
			String strDob = "";
			if (dob != null) strDob = dob.toString();
			
			// get information 
			responsedUser.put("userType", "driver");
			responsedUser.put("dob", strDob);
			responsedUser.put("bankAccountNumber", user.getDriver().getBankAccountNumber());
			responsedUser.put("bankName", user.getDriver().getBankName());
			responsedUser.put("userImage", user.getDriver().getImage());
			
			// get address for driver user
			Address address = user.getDriver().getAddress();
			String house = "";
			String street = "";
			String city = "";
			String state = "";
			String zipcode = "";
			if (address != null){
				house = address.getHouse().getNumber();
				street = address.getStreet().getName();
				city = address.getCity().getName();
				state = address.getState().getAbbreviation();
				zipcode = "" + address.getZipcode().getZipcode();
			}
			
			responsedUser.put("house", house);
			responsedUser.put("street", street);
			responsedUser.put("city", city);
			responsedUser.put("state", state);
			responsedUser.put("zipcode", zipcode);
			
			// get jobs related to user
			List<Job> jobs = user.getDriver().getJobs();
			List<Map> jobsList = getJobList(jobs); // get all jobs from this user
			responsedUser.put("jobs", jobsList);
			
			// get current vehicle
			Vehicle currentVehicle = user.getDriver().getCurrentVehicle();
			if (currentVehicle != null){
				Map<String, String> currentVehicleMap = new HashMap<String, String>();
				currentVehicleMap.put("make", currentVehicle.getMake().getName());
				currentVehicleMap.put("model", currentVehicle.getModel().getName());
				currentVehicleMap.put("year", "" + currentVehicle.getYear());
				currentVehicleMap.put("licensePlate", currentVehicle.getLicensePlateNumber());
				currentVehicleMap.put("capacity", "" + currentVehicle.getCapacity());
				responsedUser.put("currentVehicle", currentVehicleMap);
			}
			
			// get all user's vehicles except current vehicle
			List<Vehicle> vehicles = user.getDriver().getVehicles();
			vehicles.remove(currentVehicle);
			List<Map> vehiclesList = new ArrayList<Map>();
			if (vehicles != null){
				for (Vehicle vehicle: vehicles){
					Map<String, String> vehicleMap = new HashMap<String, String>();
					vehicleMap.put("make", vehicle.getMake().getName());
					vehicleMap.put("model", vehicle.getModel().getName());
					vehicleMap.put("year", "" + vehicle.getYear());
					vehicleMap.put("licensePlate", vehicle.getLicensePlateNumber());
					vehicleMap.put("capacity", "" + vehicle.getCapacity());
					vehiclesList.add(vehicleMap);
				}
			}
			responsedUser.put("vehicles", vehiclesList);
			
		}else{	// customer user
			Customer customer = user.getCustomer();
			responsedUser.put("userType", "customer");
			responsedUser.put("creditCardNumber", customer.getCcvNumber());
			responsedUser.put("expiration", customer.getExpiration());
			responsedUser.put("ccv", customer.getCcvNumber());
			responsedUser.put("zipcode", customer.getZipcode().getZipcode());
			
			List<Job> jobs = customer.getJobs();
			List<Map> jobsList = getJobList(jobs);
			
			responsedUser.put("jobs", jobsList);
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(responsedUser);
	}
	
	private List<Map> getJobList(List<Job> jobs){
		List<Map> jobsList = new ArrayList<Map>();
		
		for (Job job: jobs){
			Map<String, String> jobsMap = new HashMap<String, String>();
			
			String customerFirstname = job.getCustomer().getUser().getFirstname();
			String customerLastname = job.getCustomer().getUser().getLastname();
			String customerEmail = job.getCustomer().getUser().getEmail();
			
			String driverFirstname = job.getDriver().getUser().getFirstname();
			String driverLastname = job.getDriver().getUser().getLastname();
			String driverEmail = job.getDriver().getUser().getEmail();
			
			String distance = "" + job.getDistance();
			String cost = "" + job.getCost();
			String date = job.getDate().toString();
			String tip = "" + job.getTip();
			
			String departure = "";
			if (job.getSourceAddress() != null){ 
				departure = this.convertAddressIntoString(job.getSourceAddress());
			}else{
				departure = "" + job.getSourceCoordination().getLatitude();
				departure += ", " + job.getSourceCoordination().getLongitude();
			}
				
			
			String destination = "";
			if (job.getDestinationAddress() != null){
				destination = this.convertAddressIntoString(job.getDestinationAddress());
			}else{
				destination = "" + job.getDestinationCoordination().getLatitude();
				destination += ", " + job.getDestinationCoordination().getLongitude();
			}
			
			
			jobsMap.put("customerName", customerFirstname + " " + customerLastname);
			jobsMap.put("customerEmail", customerEmail);
			jobsMap.put("driverEmail", driverEmail);
			jobsMap.put("driverName", driverFirstname + " " + driverLastname);
			jobsMap.put("departure", departure);
			jobsMap.put("destination", destination);
			jobsMap.put("distance", distance);
			jobsMap.put("cost", cost);
			jobsMap.put("date", date);
			jobsMap.put("tip", tip);
			
			jobsList.add(jobsMap);
			
			System.out.println("got request");
		}
		
		return jobsList;
	}
	
	private String convertAddressIntoString(Address address){
		if (address == null) return null;
		
		String stringAddress = "";
		
		stringAddress = address.getHouse().getNumber();
		stringAddress += " " + address.getStreet().getName();
		stringAddress += " " + address.getCity().getName();
		stringAddress += ", " + address.getState().getAbbreviation();
		stringAddress += " " + address.getZipcode().getZipcode();
		
		
		return stringAddress;
	}
	
	@Path("jobs")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllJob(String data){
		Map<String, Object> responsedUser = new HashMap<String, Object>();
		
		List<Job> jobs = service.getAllJob();
		List<Map> jobsList = getJobList(jobs);
		
		responsedUser.put("jobs", jobsList);
		
		Gson gson = new Gson();
		
		return gson.toJson(responsedUser);
	}
	
	@Path("shareGCMRegId")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceId(String data) throws JSONException, IOException{
		
		JSONObject json_obj = new JSONObject(data);
		
		String userId = json_obj.getString("gcmUserId");
		String deviceId = json_obj.getString("gcmRegisterId");
		
		System.out.println("******Send Device Id*******");
		System.out.println("User Id: " + userId);
		System.out.println("Device Id: " + deviceId);

		 //server will do this
		 
//		Sender sender = new Sender(projectId);
//		Message message = new Message.Builder().timeToLive(30)
//				.delayWhileIdle(true).addData("message", deviceId).build();
//		System.out.println("regId: " + deviceId);
//		Result result = sender.send(message, deviceId, 1);
		
		return Response.status(200).entity("Send Device Id").build();
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
		
		JSONObject json_obj = null;
		String str_userId = null;
		String str_password = null;
		try{
			json_obj = new JSONObject(data);
			str_userId = json_obj.getString("userid");
			str_userId = json_obj.getString("password");
			
			//check in database see if match: match return OK, else return FAILED
			
		} catch (JSONException e){
			
		}
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
