package com.falcon.hosting.test.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterUserValidation {
	public Map<String, String> fieldName = new HashMap<String, String>();

	@NotNull
	@NotEmpty
	private String firstname;
	
	@NotNull
	@NotEmpty
	private String lastname;
	
	@NotNull
	@NotEmpty
	@Email 
	private String email;
	
	@NotNull
	@NotEmpty
	private String confirmEmail;
	
	@AssertTrue(message="Email doesn&#39;t match!")
	private boolean isEmailMatch(){
		return email.equalsIgnoreCase(confirmEmail);
	}
	
	@NotNull
	@NotEmpty
	private String dob;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String confirmPassword;
	
	@AssertTrue(message="Password doesn&#39;t match!")
	private boolean isPasswordMatch(){
		return password.equals(confirmPassword);
	}

	@NotNull
	@Length(min = 10, max = 11)
	@Digits(fraction = 0, integer = 11)
	private String phoneNumber;
	
	
	
	// constructor for page
	public RegisterUserValidation(HttpServletRequest request){
		this.setFirstname(request.getParameter("firstname"));
		this.setLastname(request.getParameter("lastname"));
		this.setEmail(request.getParameter("email"));
		this.setConfirmEmail(request.getParameter("confirmEmail"));
		this.setDob(request.getParameter("dob"));
		this.setPassword(request.getParameter("password"));
		this.setConfirmPassword(request.getParameter("confirmPassword"));		
		this.setPhoneNumber(request.getParameter("phoneNumber"));
				
		fieldName.put("emailExist", "Email");
	}
	
	// empty contructor
	public RegisterUserValidation(){
		
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
}
