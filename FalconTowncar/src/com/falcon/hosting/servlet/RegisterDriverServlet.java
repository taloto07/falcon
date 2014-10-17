package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import com.falcon.hosting.doa.Country;
import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.House;
import com.falcon.hosting.doa.License;
import com.falcon.hosting.doa.LicenseType;
import com.falcon.hosting.doa.Make;
import com.falcon.hosting.doa.Model;
import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.Street;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.Vehicle;
import com.falcon.hosting.doa.Zipcode;
import com.falcon.hosting.test.validator.RegisterDriverValidation;
import com.falcon.hosting.test.validator.SHA256Conv;

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
		String stateString = "";
		for (State state: states){
			stateString += "<option value='"+ state.getId() +"'>" + state.getName() + "</option>";
		}
		
		// get all vehicle makes from database
		List<Make> makes = service.getAllMakeASC();
		String makeString = "";
		for (Make make: makes){
			makeString += "<option value='"+ make.getId() +"'>" + make.getName().toUpperCase() + "</option>";
		}
		
		// get all vehicle models from database
		List<Model> models = service.getAllModelASC();
		String modelString = "";
		for (Model model: models){
			modelString += "<option value='"+ model.getId() +"'>" + model.getName().toUpperCase() + "</option>";
		}
		
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		page.add("title", "Driver Registration");
		body.add("contextPath", contextPath);
		body.add("states", stateString);
		body.add("makes", makeString);
		body.add("models", modelString);
		page.add("body", body.render());
		
		out.write(page.render());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		
		RegisterDriverValidation form = new RegisterDriverValidation(request);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<RegisterDriverValidation>> isFormValid = validator.validate(form);
		
		// form is valid
		if (isFormValid.isEmpty() && service.getUserByEmail(form.getEmail()) == null){
			try {
				insertDriver(form);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{ // form is not valid
			ST body = templates.getInstanceOf("registerdriver");
			
			if (!isFormValid.isEmpty()){
				// add error message for each field
				List<String> propertyPaths = new ArrayList<String>(); 
				for(ConstraintViolation<RegisterDriverValidation> rdv: isFormValid){
					String propertyPath = rdv.getPropertyPath().toString();
					
					if (!propertyPaths.contains(propertyPath)){
						propertyPaths.add(propertyPath);
						body.add(propertyPath, rdv.getMessage() + " ");
					}
				}
			}else{ // error: email is already existed
				body.add("email", "Email is already exist!");
				body.add("emailMatch", "Email is already exist!");
				System.out.println("Email is already exist!!!!!!!!!!!!!!");
			}
			
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
			String stateString = "";
			for (State state: states){
				String selected = "";
				if (state.getId() == Integer.parseInt(form.getState())) selected = "selected";
				stateString += "<option value='"+ state.getId() +"' " + selected +">" + state.getName() + "</option>";
			}
			
			// get all vehicle makes from database
			List<Make> makes = service.getAllMakeASC();
			String makeString = "";
			for (Make make: makes){
				String selected = "";
				if (make.getId() == Integer.parseInt(form.getVehicleMake())) selected = "selected";
				makeString += "<option value='"+ make.getId() +"' " + selected + ">" + make.getName().toUpperCase() + "</option>";
			}
			
			// get all vehicle models from database
			List<Model> models = service.getAllModelASC();
			String modelString = "";
			for (Model model: models){
				String selected = "";
				if (model.getId() == Integer.parseInt(form.getVehicleModel())) selected = "selected";
				modelString += "<option value='"+ model.getId() +"' " + selected + ">" + model.getName().toUpperCase() + "</option>";
			}
			
			this.checkLogin(page, request);
			body.add("contextPath", contextPath);
			body.add("errorMessage", "Please correct the highlight fields!");
			body.add("states", stateString);
			body.add("makes", makeString);
			body.add("models", modelString);
			body.add("formValue", form);
			page.add("contextPath", contextPath);
			page.add("title", "Driver Registration");
			page.add("body", body.render());
		}
		
		out.write(page.render());
		out.flush();
	}
	
	private boolean insertDriver(RegisterDriverValidation form) throws ParseException{
		
		// add user to database
		User user = new User();
		user.setFirstname(form.getFirstname());
		user.setLastname(form.getLastname());
		user.setEmail(form.getEmail());
		
		SHA256Conv sha256Conv = new SHA256Conv(form.getPassword()); // convert to sha256 format
		user.setPassword(sha256Conv.toString());
		user.setPhoneNumber(form.getPhoneNumber());
		
		// add driver group to this user
		Group driverGroup = service.getGroupByName("driver");
		List<Group> groups = new ArrayList<Group>();
		groups.add(driverGroup);
		user.setGroups(groups);
		service.addUser(user);
		
		// get house number and street name from address
		String address = form.getAddress();
		int index = address.indexOf(' ');
		String houseNumber = address.substring(0, index);
		String streetName = address.substring(index + 1);
		
		// add house to database if not exist
		House house = service.getHouseByNumber(houseNumber);
		if (house == null){
			house = new House();
			house.setNumber(houseNumber);
			service.addHouse(house);
		}
		
		// add street to database if not exist
		Street street = service.getStreetByName(streetName);
		if (street == null){
			street = new Street();
			street.setName(streetName);
			service.addStreet(street);
		}
		
		// add city to database if not exist
		City city = service.getCityByName(form.getCity());
		if (city == null){
			city = new City();
			city.setName(form.getCity());
			service.addCity(city);
		}
		
		// get state from database
		State state = service.getStateById(Integer.parseInt(form.getState()));
		
		// add zipcode to database if not exist
		Zipcode zipcode = service.getZipcodeByZipcdoe(Integer.parseInt(form.getZipcode()));
		if (zipcode == null){
			zipcode = new Zipcode();
			zipcode.setZipcode(Integer.parseInt(form.getZipcode()));
			service.addZipcode(zipcode);
		}
		
		// get country from database
		Country country = service.getCountryByName("united states");
		
		// add address to database
		Address addressDriver = new Address();
		addressDriver.setHouse(house);
		addressDriver.setStreet(street);
		addressDriver.setCity(city);
		addressDriver.setState(state);
		addressDriver.setZipcode(zipcode);
		addressDriver.setCountry(country);
		service.addAddress(addressDriver);
		
		// vehicle section
		// get vehicle make from database
		Make make = service.getMakeById(Integer.parseInt(form.getVehicleMake()));
		
		// get vehicle model from database
		Model model = service.getModelById(Integer.parseInt(form.getVehicleModel()));
		
		// add vehicle to database
		Vehicle vehicle = new Vehicle();
		vehicle.setMake(make);
		vehicle.setModel(model);
		vehicle.setYear(Integer.parseInt(form.getVehicleYear()));
		vehicle.setLicensePlateNumber(form.getLicensePlate());
		vehicle.setCapacity(Integer.parseInt(form.getCapacity()));
		service.addVehicle(vehicle);
		
		// add driver to database
		Driver driver = new Driver();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date dob = sdf.parse(form.getDob());
		driver.setDate_of_birth(dob);
		driver.setBankName(form.getBankName());
		driver.setBankAccountNumber(form.getBankAccountNumber());
		driver.setUser(user);
		driver.setAddress(addressDriver);
		driver.setCurrentVehicle(vehicle);
		driver.setShift((byte) 0);
		driver.setDriving((byte) 0);
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(vehicle);
		driver.setVehicles(vehicles);
		
		service.addDriver(driver);
		
		// add licenses to database
		LicenseType licenseType = service.getLicenseTypeByType("normal");
		License normalLicense = new License();
		
		Date normalLicenseExp = sdf.parse(form.getDrivingLicenseExpiration());
		normalLicense.setExpiration(normalLicenseExp);
		normalLicense.setLicenseNumber(form.getDrivingLicense());
		normalLicense.setLicenseType(licenseType);
		normalLicense.setDriver(driver);
		service.addLicense(normalLicense);
		
		licenseType = service.getLicenseTypeByType("professional");
		License proLicense = new License();
		Date proExp = sdf.parse(form.getDLFHExpiration());
		proLicense.setExpiration(proExp);
		proLicense.setLicenseNumber(form.getDLFH());
		proLicense.setLicenseType(licenseType);
		proLicense.setDriver(driver);
		service.addLicense(proLicense);
		
		// set the driver that own this vehicle
		vehicle.setDriver2(driver);
		service.addVehicle(vehicle);

		return true;
	}
}
