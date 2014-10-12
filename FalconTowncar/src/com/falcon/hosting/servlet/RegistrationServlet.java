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
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String confirmEmail = request.getParameter("confirmEmail");
		String inputPassword = request.getParameter("password");
		String inputConfirmPassword = request.getParameter("confirmPassword");
		String phoneNumber = request.getParameter("phoneNumber");
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(confirmEmail);
		System.out.println(inputPassword);
		System.out.println(inputConfirmPassword);
		System.out.println(phoneNumber);
		//User u = new User();
		//service.addUser(u);
		
		/*
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
		page.add("script", datePicker + startDatePicker);
		// end ending javascript
				
		this.checkLogin(page, request);
		body.add("contextPath", contextPath);
		
		body.add("errorMessage", "First Name: " + firstName);
		if(! email.equals(confirmEmail)){
			//error for email mismatch
			body.add("errorMessage", "<br/>Emails do not match");
		}
		if(! inputPassword.equals(inputConfirmPassword)) {
			//error for password mismatch	
			body.add("errorMessage", "<br/>Passwords do not match");
		}
		
		
		body.add("errorDOB", "has-error");
		
		page.add("contextPath", contextPath);
		page.add("body", body.render());
		
		out.write(page.render());
		out.flush();*/
	}

}
