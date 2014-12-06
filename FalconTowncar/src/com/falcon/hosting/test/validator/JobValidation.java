package com.falcon.hosting.test.validator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class JobValidation { 
	@Range(min=1, message = "please choose driver")
	private String driverId;
	 
	@Range(min=1, message = "please choose customer")
	private String customerId;
	
	@NotNull 
	@NotEmpty
	private String sourceAddress;
	
	@NotNull 
	@NotEmpty
	private String sourceCity;
	
	@Range(min=1, message="Please choose departure state")
	private String sourceStateId;
	
	@NotNull 
	@Length(min = 5, max = 5) 
	@Digits(fraction = 0, integer = 11, message="value must be number")
	private String sourceZipcode;
	
	@NotNull 
	@NotEmpty
	private String destAddress;
	
	@NotNull 
	@NotEmpty
	private String destCity;
	
	@Range(min=1, message="Please choose destination state") 
	private String destStateId;
	
	@NotNull
	@Length(min = 5, max = 5) 
	@Digits(fraction = 0, integer = 11, message="value must be number")
	private String destZipcode;
	
	@NotNull 
	@NotEmpty
	private String jobDate;
	
	public JobValidation(HttpServletRequest request){
		this.setDriverId(request.getParameter("driver"));
		this.setCustomerId(request.getParameter("customer"));
		
		// source address assignment
		this.setSourceAddress(request.getParameter("sourceAddress"));
		this.setSourceCity(request.getParameter("sourceCity"));
		this.setSourceStateId(request.getParameter("sourceState"));
		this.setSourceZipcode(request.getParameter("sourceZipcode"));
		
		// destination address assignment
		this.setDestAddress(request.getParameter("destAddress"));
		this.setDestCity(request.getParameter("destCity"));
		this.setDestStateId(request.getParameter("destState"));
		this.setDestZipcode(request.getParameter("destZipcode"));
		
		this.setJobDate(request.getParameter("jobDate"));
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getSourceStateId() {
		return sourceStateId;
	}

	public void setSourceStateId(String sourceStateId) {
		this.sourceStateId = sourceStateId;
	}

	public String getSourceZipcode() {
		return sourceZipcode;
	}

	public void setSourceZipcode(String sourceZipcode) {
		this.sourceZipcode = sourceZipcode;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public String getDestStateId() {
		return destStateId;
	}

	public void setDestStateId(String destStateId) {
		this.destStateId = destStateId;
	}

	public String getDestZipcode() {
		return destZipcode;
	}

	public void setDestZipcode(String destZipcode) {
		this.destZipcode = destZipcode;
	}

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	
}
