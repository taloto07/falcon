package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.Address;
import com.falcon.hosting.doa.City;
import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.Zipcode;
import com.falcon.hosting.test.validator.JobValidation;


public class AddJobServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public AddJobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String contextPath = this.getContextPath();
		
		// get views
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("add_jobs");
		
		// get customers
		List<Customer> customers = service.getAllCustomer();
		String customerString = "";
		for (Customer c : customers){
			customerString += "<option value=" + c.getId() + ">" + c.getUser().getFirstname() + " " + c.getUser().getLastname() +  "</option>";
		}
		
		// get drivers
		List<Driver> drivers = service.getAllDriver();
		String driverString = "";
		for (Driver d: drivers){
			driverString += "<option value=" + d.getId() + ">" + d.getUser().getFirstname() + " " + d.getUser().getLastname() +  "</option>";
		}
		
		// get source states
		List<State> states = service.getAllStateASC();
		String sourceStateString = "";
		for (State s: states){
			sourceStateString += "<option value=" + s.getId() + ">" + s.getAbbreviation().toUpperCase() + "</option>";
		}
		
		// get destination states
		String destStateString = "";
		for (State s: states){
			destStateString += "<option value=" + s.getId() + ">" + s.getAbbreviation().toUpperCase() + "</option>";
		}
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		
		// body
		body.add("contextPath", contextPath);
		body.add("customers", customerString);
		body.add("drivers", driverString);
		body.add("sourceStates", sourceStateString);
		body.add("destStates", destStateString);
		body.add("todayDate", df.format(date));
		
		// add javascript to template
		ST script = templates.getInstanceOf("javascript");
		script.add("contextPath", contextPath);
		script.add("filename", "bootstrap-datepicker.js");
		String datePicker = script.render();
				
		script.remove("filename");
		script.add("filename", "start-date-picker-script.js");
		String startDatePicker = script.render();
		
		page.add("script", datePicker + startDatePicker);
		// end adding javascript
		
		// page
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		page.add("title", "Add Job");
		page.add("body", body.render());
		
		out.print(page.render());
		
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String contextPath = this.getContextPath();
		
		// get views
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("add_jobs");
		
		JobValidation jobValidation = new JobValidation(request);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<JobValidation>> isValid = validator.validate(jobValidation);
		
		if (!isValid.isEmpty()){	// validation fail
			// add error message for each field
			List<String> propertyPaths = new ArrayList<String>();
			System.out.println("***********before getting all error message**************");
			for (ConstraintViolation<JobValidation> j: isValid){
				String propertyPath = j.getPropertyPath().toString();
				
				// set error messages to view
				if (!propertyPaths.contains(propertyPath)){
					propertyPaths.add(propertyPath);
					body.add(propertyPath, j.getMessage());
					System.out.println(propertyPath + " : " + j.getMessage());
				}
			}
			System.out.println("*********************************");
			
			// get customers
			List<Customer> customers = service.getAllCustomer();
			String customerString = "";
			for (Customer c : customers){
				String selectedCustomer = "";
				if (c.getId() == Integer.parseInt(jobValidation.getCustomerId()))
					selectedCustomer = "selected";
				customerString += "<option " + selectedCustomer + " value=" + c.getId() + ">" + 
					c.getUser().getFirstname() + " " + c.getUser().getLastname() +  "</option>";
			}
			
			// get drivers
			List<Driver> drivers = service.getAllDriver();
			String driverString = "";
			for (Driver d: drivers){
				String selectedDriver = "";
				if(d.getId() == Integer.parseInt(jobValidation.getDriverId()))
					selectedDriver = "selected";
				driverString += "<option " + selectedDriver + " value=" + d.getId() + ">" + 
						d.getUser().getFirstname() + " " + d.getUser().getLastname() +  "</option>";
			}
			
			// get source states
			List<State> states = service.getAllStateASC();
			String sourceStateString = "";
			for (State s: states){
				String selectedState = "";
				if (s.getId() == Integer.parseInt(jobValidation.getSourceStateId()))
					selectedState = "selected";
				sourceStateString += "<option " + selectedState + " value=" + s.getId() + ">" + 
					s.getAbbreviation().toUpperCase() + "</option>";
			}
			
			// get destination states
			String destStateString = "";
			for (State s: states){
				String selectedState = "";
				if (s.getId() == Integer.parseInt(jobValidation.getDestStateId()))
					selectedState = "selected";
				destStateString += "<option " + selectedState + " value=" + s.getId() + ">" + 
					s.getAbbreviation().toUpperCase() + "</option>";
			}
			
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			
			// body
			body.add("contextPath", contextPath);
			body.add("customers", customerString);
			body.add("drivers", driverString);
			body.add("todayDate", df.format(date));
			body.add("sourceStates", sourceStateString);
			body.add("destStates", destStateString);
			body.add("form", jobValidation);
			
			// add javascript to template
			ST script = templates.getInstanceOf("javascript");
			script.add("contextPath", contextPath);
			script.add("filename", "bootstrap-datepicker.js");
			String datePicker = script.render();
					
			script.remove("filename");
			script.add("filename", "start-date-picker-script.js");
			String startDatePicker = script.render();
			
			page.add("script", datePicker + startDatePicker);
			// end adding javascript
		}else{ // validation pass and load form again with success notification
			
			// get customers
			List<Customer> customers = service.getAllCustomer();
			String customerString = "";
			for (Customer c : customers){
				customerString += "<option value=" + c.getId() + ">" + c.getUser().getFirstname() + " " + c.getUser().getLastname() +  "</option>";
			}
			
			// get drivers
			List<Driver> drivers = service.getAllDriver();
			String driverString = "";
			for (Driver d: drivers){
				driverString += "<option value=" + d.getId() + ">" + d.getUser().getFirstname() + " " + d.getUser().getLastname() +  "</option>";
			}
			
			// get source states
			List<State> states = service.getAllStateASC();
			String sourceStateString = "";
			for (State s: states){
				sourceStateString += "<option value=" + s.getId() + ">" + s.getAbbreviation().toUpperCase() + "</option>";
			}
			
			// get destination states
			String destStateString = "";
			for (State s: states){
				destStateString += "<option value=" + s.getId() + ">" + s.getAbbreviation().toUpperCase() + "</option>";
			}
			
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			
			// body
			body.add("contextPath", contextPath);
			body.add("customers", customerString);
			body.add("drivers", driverString);
			body.add("sourceStates", sourceStateString);
			body.add("destStates", destStateString);
			body.add("todayDate", df.format(date));
			body.add("success", "1");
			
			// add javascript to template
			ST script = templates.getInstanceOf("javascript");
			script.add("contextPath", contextPath);
			script.add("filename", "bootstrap-datepicker.js");
			String datePicker = script.render();
					
			script.remove("filename");
			script.add("filename", "start-date-picker-script.js");
			String startDatePicker = script.render();
			
			page.add("script", datePicker + startDatePicker);
			// end adding javascript
			
			addJobToDatabase(jobValidation);
		}
		
		// page
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		page.add("title", "Add Job");
		page.add("body", body.render());
		
		out.print(page.render());
		
		out.flush();
	}
	
	private boolean addJobToDatabase(JobValidation job){
		String sourceAddress = job.getSourceAddress();
		
		int indexOfFirstSpace = sourceAddress.indexOf(' ');
		String house = sourceAddress.substring(0, indexOfFirstSpace);
		String street = sourceAddress.substring(indexOfFirstSpace + 1);
		
		House shouse = service.getHouseByNumber(house);
		
		City city = service.getCityByName(job.getSourceCity());
		if (city == null){
			city = new City();
			city.setName(job.getSourceCity());
			service.addCity(city);
		}
		
		Zipcode zipcode = service.getZipcodeByZipcdoe(Integer.parseInt(job.getSourceZipcode()));
		if (zipcode == null){
			zipcode = new Zipcode();
			zipcode.setZipcode(Integer.parseInt(job.getSourceZipcode()));
			service.addZipcode(zipcode);
		}
		
		
		
		
		return true;
	}
	
	private Address addSourceAddress(JobValidation job){
		System.out.println("new");
		return null;
	}

}
