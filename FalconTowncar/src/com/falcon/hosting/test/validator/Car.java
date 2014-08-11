package com.falcon.hosting.test.validator;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {
	@NotNull
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
	
	@AssertTrue(message="Password doesn't matched!")
	private boolean isPasswordMatch(){
		return password.equals(confirmPassword);
	}
	
	public Car(){
		
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
