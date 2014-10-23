package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;


import com.falcon.hosting.doa.Driver;
import com.falcon.hosting.doa.Group;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.test.validator.DriverTempValidation;
import com.falcon.hosting.test.validator.SHA256Conv;

public class RegisterDriverTempServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterDriverTempServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		
		ST body = templates.getInstanceOf("registerdrivertemp");
		
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		page.add("title", "Driver Registration");
		body.add("contextPath", contextPath);
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
		
		DriverTempValidation form = new DriverTempValidation(request);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<DriverTempValidation>> isFormValid = validator.validate(form);
		
		// form is valid
		if (isFormValid.isEmpty() && service.getUserByEmail(form.getEmail()) == null){
				insertDriver(form);
				response.sendRedirect(contextPath + "dashboard");
		}else{ // form is not valid
			ST body = templates.getInstanceOf("registerdrivertemp");
			
			if (!isFormValid.isEmpty()){
				// add error message for each field
				List<String> propertyPaths = new ArrayList<String>(); 
				for(ConstraintViolation<DriverTempValidation> rdv: isFormValid){
					String propertyPath = rdv.getPropertyPath().toString();
					// set error messages to view
					if (!propertyPaths.contains(propertyPath)){
						propertyPaths.add(propertyPath);
						body.add(propertyPath, rdv.getMessage() + " ");
					}
				}
			}else{ // error: email is already existed
				body.add("email", "Email is already exist!");
				body.add("emailMatch", "Email is already exist!");
			}
			
			this.checkLogin(page, request);
			body.add("contextPath", contextPath);
			body.add("errorMessage", "Please correct the highlight fields!");
			body.add("formValue", form);
			page.add("contextPath", contextPath);
			page.add("title", "Driver Registration");
			page.add("body", body.render());
		}
		
		out.write(page.render());
		out.flush();
	}
	
	private void insertDriver(DriverTempValidation form){
		User user = new User();
		user.setFirstname(form.getFirstname());
		user.setLastname(form.getLastname());
		user.setEmail(form.getEmail());
		// convert password to sha256
		SHA256Conv sha256 = new SHA256Conv(form.getPassword());
		user.setPassword(sha256.getConvPass());
		
		// add to driver's group
		Group driverGroup = service.getGroupByName("driver");
		List<Group> groups = new ArrayList<Group>();
		groups.add(driverGroup);
		user.setGroups(groups);
		// add user to database
		service.addUser(user);
		
		Driver driver = new Driver();
		driver.setUser(user);
		service.addDriver(driver);
	}
}
