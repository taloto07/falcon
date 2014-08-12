package com.falcon.hosting.test.validator;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Car {
	@NotNull(message="Manufacturer can not be null!")
	@NotEmpty(message="Manufacturer can not be empty!")
	private String manufacturer;
	
	@NotNull
	@Size(min=2, max=4)
	private String licensePlate;
	
	@Min(2)
	private int sitCount;
	
	@NotNull
	@Size(min=8, max=32)
	private String password;
	
	@NotNull
	private String confirmPassword;
	
	@NotEmpty
	private String creditCard;
	
	@AssertTrue(message="Password doesn't matched!")
	private boolean isPasswordMatch(){
		return password.equals(confirmPassword);
	}
	
	public Car(){
		
	}

	@CreditCardNumber
	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSitCount() {
		return sitCount;
	}

	public void setSitCount(int sitCount) {
		this.sitCount = sitCount;
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
}
