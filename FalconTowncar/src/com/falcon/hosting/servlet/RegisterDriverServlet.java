package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.Make;
import com.falcon.hosting.doa.Model;
import com.falcon.hosting.doa.State;

public class RegisterDriverServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterDriverServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		
		ST body = templates.getInstanceOf("registerdriver");
		
		// add javascript to template
		ST script = templates.getInstanceOf("javascript");
		script.add("contextPath", contextPath);
		script.add("filename", "bootstrap-datepicker.js");
		String datePicker = script.render();
		
		script.remove("filename");
		script.add("filename", "start-date-picker-script.js");
		String startDatePicker = script.render();
		// end ending javascript
		
		page.add("script", datePicker + startDatePicker);
		
		// get all states from database
		List<State> states = service.getAllStateASC();
		for (State state: states){
			state.setAbbreviation(state.getAbbreviation().toUpperCase());
		}
		
		// get all vehicle makes from database
		List<Make> makes = service.getAllMakeASC();
		for (Make make: makes){
			make.setName(make.getName().toUpperCase());
		}
		
		// get all vehicle models from database
		List<Model> models = service.getAllModelASC();
		for (Model model: models){
			model.setName(model.getName().toUpperCase());
		}
		
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		body.add("contextPath", contextPath);
		body.add("states", states);
		body.add("makes", makes);
		body.add("models", models);
		page.add("body", body.render());
		
		out.write(page.render());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		process(request, response);
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		
		ST body = templates.getInstanceOf("registerdriver");
		
		// add javascript to template
		ST script = templates.getInstanceOf("javascript");
		script.add("contextPath", contextPath);
		script.add("filename", "bootstrap-datepicker.js");
		String datePicker = script.render();
		
		script.remove("filename");
		script.add("filename", "start-date-picker-script.js");
		String startDatePicker = script.render();
		// end ending javascript
		
		page.add("script", datePicker + startDatePicker);
		
		// get all states from database
		List<State> states = service.getAllStateASC();
		for (State state: states){
			state.setAbbreviation(state.getAbbreviation().toUpperCase());
		}
		
		// get all vehicle makes from database
		List<Make> makes = service.getAllMakeASC();
		for (Make make: makes){
			make.setName(make.getName().toUpperCase());
		}
		
		// get all vehicle models from database
		List<Model> models = service.getAllModelASC();
		for (Model model: models){
			model.setName(model.getName().toUpperCase());
		}
		
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		body.add("contextPath", contextPath);
		body.add("states", states);
		body.add("makes", makes);
		body.add("models", models);
		page.add("body", body.render());
		
		out.write(page.render());
		out.flush();
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String confirmEmail = request.getParameter("confirmEmail");
		String dob = request.getParameter("dob");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String phoneNumber = request.getParameter("phoneNumber");
		String drivingLicense = request.getParameter("drivingLicense");
		String drivingLicenseExpiration = request.getParameter("drivingLicenseExpiration");
		String DLFH = request.getParameter("DLFH");
		String DLFHExpiration = request.getParameter("DLFHExpiration");
		String bankName = request.getParameter("bankName");
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		String vehicleMake = request.getParameter("vehicleMake");
		String vehicleModel = request.getParameter("vehicleModel");
		String vehicleYear = request.getParameter("vehicleYear");
		String vehicleLicensePlate = request.getParameter("vehicleLicensePlate");
		String vehicleCapacity = request.getParameter("vehicleCapacity");
		
		System.out.println("First Name: " + firstname);
		System.out.println("Last Name: " + lastname);
		System.out.println("Email: " + email);
		System.out.println("DOB: " + dob);
		System.out.println("Password: " + password);
		System.out.println("Confirm Password: " + confirmPassword);
		System.out.println("Address: " + address);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("Zipcode: " + zipcode);
		System.out.println("Phone Number: " + phoneNumber);
		System.out.println("Driving License: " + drivingLicense);
		System.out.println("Expiration: " + drivingLicenseExpiration);
		System.out.println("Driving License For Hiring: " + DLFH);
		System.out.println("Expiration: " + DLFHExpiration);
		System.out.println("Bankk Name: " + bankName);
		System.out.println("Bank Account Number: " + bankAccountNumber);
		System.out.println("Make: " + vehicleMake);
		System.out.println("Model: " + vehicleModel);
		System.out.println("Year: " + vehicleYear);
		System.out.println("License Plate: " + vehicleLicensePlate);
		System.out.println("Capacity: " + vehicleCapacity);
		
	}

}
