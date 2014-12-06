package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.User;


public class EditPageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public EditPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String contextPath = this.getContextPath();
		
		//PrintWriter for writing to page
		PrintWriter out = response.getWriter();
		
		//Set up String Templates
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = null;
		
		//Acquire user from database
		String email = request.getRemoteUser();
		User user = service.getUserByEmail(email);
		
		String editPage = "";

		//Check if you're editing Customer info
		if (user.getCustomer() != null){
			body = editCustomer(request, response);
			editPage = "Edit Customer Info";
		}
		
		//Check if you're editing Driver info
		else if(user.getDriver() != null) {
			body = editDriver(request, response);
			editPage = "Edit Driver Info";
		}

		//Check if you're editing Owner info
		else {
			//Owner doesn't need to edit info <check with Chamnap>
			//body = editOwner(request, response);
			//editPage = "Edit Owner Info";
		}
		//Make sure user is logged in before displaying info
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		page.add("title", editPage);
		page.add("body", body.render());
		out.print(page.render());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setCharacterEncoding("UTF-8");
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
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/login");
			
		} else {
			errorMessage = "Email is invalid.";				
		
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
		}*/
	}
	private ST editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ST body = null;
		
		return body;
	}
	private ST editDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ST body = null;
		
		return body;
	}
}
