package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.State;
import com.falcon.hosting.doa.User;

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
		
		List<State> states = service.getAllStateASC();
		for (State state: states){
			state.setAbbreviation(state.getAbbreviation().toUpperCase());
		}
		
		page.add("script", datePicker + startDatePicker);
		
		body.add("contextPath", contextPath);
		page.add("contextPath", contextPath);
		page.add("title", "Registration");
		body.add("states", states);
		page.add("body", body.render());
		
		out.print(page.render());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage = processInfo(request, response);
		//process data from form
		if(errorMessage.length()==0)
			//redirect back to login page (successfully added user)
			response.sendRedirect("/FalconTowncar/login");
		else {
			//Emails / Passwords did not match, display error
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
			
			List<State> states = service.getAllStateASC();
			for (State state: states){
				state.setAbbreviation(state.getAbbreviation().toUpperCase());
			}
			
			page.add("script", datePicker + startDatePicker);

			body.add("errorMessage", errorMessage);
			body.add("contextPath", contextPath);
			page.add("contextPath", contextPath);
			page.add("title", "Registration");
			body.add("states", states);
			page.add("body", body.render());
			
			out.print(page.render());
			out.flush();
		}
	}
	
	private String processInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//store all data from the form in String variables
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String confirmEmail = request.getParameter("confirmEmail");
		String inputPassword = request.getParameter("password");
		String inputConfirmPassword = request.getParameter("confirmPassword");
		String phoneNumber = request.getParameter("phoneNumber");
		//validate password/email match
		if(! inputPassword.equals(inputConfirmPassword))
			return "Passwords do not match";
		else if(! email.equals(confirmEmail))
			return "Emails do not match";
		//create a new user with the database attributes
		User u = new User();
		u.setFirstname(firstName);
		u.setLastname(lastName);
		u.setEmail(email);
		u.setPassword(inputPassword);
		u.setPhoneNumber(phoneNumber);
		//attempt to add user to the database
		if( service.addUser(u)) {
			return "";
		} else
			return "A user with that e-mail already exists";
	}
	
}
