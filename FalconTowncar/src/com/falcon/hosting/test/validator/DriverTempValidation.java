package com.falcon.hosting.test.validator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DriverTempValidation {
	@NotNull
	@NotEmpty
	private String firstname;
	
	@NotNull
	@NotEmpty
	private String lastname;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String confirmPassword;
	
	@AssertTrue(message="password doesn&#39;t match")
	public boolean isPasswordMatch(){
		return password.equals(confirmPassword);
	}
	
	@NotNull
	@NotEmpty
	private String email;
	
	@NotNull
	@NotEmpty
	private String confirmEmail;
	
	@AssertTrue(message="Email doesn&#39;t match!")
	public boolean isEmailMatch(){
		return email.equalsIgnoreCase(confirmEmail);
	}
	
	// constructor
	public DriverTempValidation(HttpServletRequest request){
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		email = request.getParameter("email");
		confirmEmail = request.getParameter("confirmEmail");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
	}
	
	// contructor
	public DriverTempValidation(){
	
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	
}
