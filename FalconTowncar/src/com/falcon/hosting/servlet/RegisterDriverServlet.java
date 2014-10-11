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
		
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		body.add("contextPath", contextPath);
		body.add("states", states);
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
		
		String firstName = request.getParameter("firstname");
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		body.add("errorMessage", "First Name: " + firstName);
		body.add("errorMessage", "<br/>Bank Account Number: " + bankAccountNumber);
		body.add("errorFirstName", "has-error");
		body.add("errorDOB", "has-error");
		
		page.add("contextPath", contextPath);
		page.add("body", body.render());
		
		out.write(page.render());
		out.flush();
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

}
