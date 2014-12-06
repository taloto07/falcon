package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
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

import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.Make;
import com.falcon.hosting.doa.Model;
import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.test.validator.RegisterDriverValidation;
import com.falcon.hosting.test.validator.RegisterUserValidation;
import com.falcon.hosting.test.validator.SHA256Conv;

public class RegistrationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("registration");
		
		// add javascript to template
		ST script = templates.getInstanceOf("javascript");
		script.add("contextPath", contextPath);
		script.add("filename", "bootstrap-datepicker.js");
		String datePicker = script.render();
				
		script.remove("filename");
		script.add("filename", "start-date-picker-script.js");
		String startDatePicker = script.render();
		// end adding javascript

		page.add("script", datePicker + startDatePicker);
		
		List<State> states = service.getAllStateASC();
		for (State state: states){
			state.setAbbreviation(state.getAbbreviation().toUpperCase());
		}
		
		body.add("contextPath", contextPath);
		page.add("contextPath", contextPath);
		page.add("title", "Registration");
		body.add("states", states);
		page.add("body", body.render());
		
		out.print(page.render());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
			
		String errorMessage = "";
		String contextPath = this.getContextPath();
			
		PrintWriter out = response.getWriter();
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("registration");
			
			//add validator to check input
		RegisterUserValidation form = new RegisterUserValidation(request);
			
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<RegisterUserValidation>> isFormValid = validator.validate(form);
		
		System.out.println("\n\n\n"+ validator.toString()+ "\n\n\n");
		System.out.println("\n\n\n"+ isFormValid.toString()+ "\n\n\n");
		System.out.println("\n\n\n"+ isFormValid.isEmpty()+ "\n\n\n");
		
		if(isFormValid.isEmpty() && service.getUserByEmail(form.getEmail()) == null) {
			try {
				insertUser(form);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("./login");
			
		} else {
			/*
			 * if (!isFormValid.isEmpty()){
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
			 */
			if (!isFormValid.isEmpty()){
				// add error message for each field
				List<String> propertyPaths = new ArrayList<String>(); 
				for(ConstraintViolation<RegisterUserValidation> rdv: isFormValid){
					String propertyPath = rdv.getPropertyPath().toString();
					
					if (!propertyPaths.contains(propertyPath)){
						propertyPaths.add(propertyPath);
						body.add(propertyPath, rdv.getMessage() + " ");
					}
				}
			}
			//errorMessage = "Email is invalid.";				
		
			// add javascript to template
			ST script = templates.getInstanceOf("javascript");
			script.add("contextPath", contextPath);
			script.add("filename", "bootstrap-datepicker.js");
			String datePicker = script.render();
						
			script.remove("filename");
			script.add("filename", "start-date-picker-script.js");
			String startDatePicker = script.render();
			// end javascript things
	
			page.add("script", datePicker + startDatePicker);
			
			body.add("contextPath", contextPath);
			body.add("errorMessage", "Please correct the highlighted fields!");			
			body.add("formValue", form);
			page.add("contextPath", contextPath);
			page.add("title", "Driver Registration");
			page.add("body", body.render());
				
			out.print(page.render());
			out.flush();
		}
	}
	
	private boolean insertUser(RegisterUserValidation form) throws ParseException{
		
		// add user to database
		User user = new User();
		user.setFirstname(form.getFirstname());
		user.setLastname(form.getLastname());
		user.setEmail(form.getEmail());
		
		SHA256Conv sha256Conv = new SHA256Conv(form.getPassword()); // convert to sha256 format
		user.setPassword(sha256Conv.getConvPass());
		user.setPhoneNumber(form.getPhoneNumber());
		
		// add customer group to this user
		Group userGroup = service.getGroupByName("customer");
		List<Group> groups = new ArrayList<Group>();
		groups.add(userGroup);
		user.setGroups(groups);

		service.addUser(user);
		Customer cust = new Customer();
		cust.setUser(user);	
		
		service.addCustomer(cust);
		
		return true;
	}
}
