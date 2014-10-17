package com.falcon.hosting.test.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.falcon.hosting.doa.User;
import com.falcon.hosting.service.FalconService;

public class RegisterDriverValidation {
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
	@NotEmpty
	private String address;
	
	@NotNull
	@NotEmpty
	private String city;
	
	@NotNull
	@Min(1)
	@Digits(fraction = 0, integer = 11)
	private String state;
	
	@NotNull
	@Length(min = 5, max = 5)
	@Digits(fraction = 0, integer = 11)
	private String zipcode;
	
	@NotNull
	@Length(min = 10, max = 11)
	@Digits(fraction = 0, integer = 11)
	private String phoneNumber;
	
	@NotNull
	@NotEmpty
	@Size(max=15)
	private String drivingLicense;
	
	@NotNull
	@NotEmpty
	private String drivingLicenseExpiration;
	
	@NotNull
	@NotEmpty
	@Size(max=15)
	private String DLFH;
	
	@NotNull
	@NotEmpty
	private String DLFHExpiration;
	
	@NotNull
	@NotEmpty
	@Min(1)
	@Digits(fraction = 0, integer = 11, message="Please select one of the vehicle makes!")
	private String vehicleMake;
	
	@NotNull
	@NotEmpty
	@Min(1)
	@Digits(fraction = 0, integer = 11)
	private String vehicleModel;
	
	@NotNull
	@NotEmpty
	@Size(min = 4, max = 5)
	@Digits(fraction = 0, integer = 11)
	private String vehicleYear;
	
	@NotNull
	@NotEmpty
	private String licensePlate;
	
	@NotNull
	@NotEmpty
	private String capacity;
	
	@NotNull
	@NotEmpty
	private String bankName;
	
	@NotNull
	@NotEmpty
	private String bankAccountNumber;
	
	public RegisterDriverValidation(HttpServletRequest request){
		this.setFirstname(request.getParameter("firstname"));
		this.setLastname(request.getParameter("lastname"));
		this.setEmail(request.getParameter("email"));
		this.setConfirmEmail(request.getParameter("confirmEmail"));
		this.setDob(request.getParameter("dob"));
		this.setPassword(request.getParameter("password"));
		this.setConfirmPassword(request.getParameter("confirmPassword"));
		this.setAddress(request.getParameter("address"));
		this.setCity(request.getParameter("city"));
		this.setState(request.getParameter("state"));
		this.setZipcode(request.getParameter("zipcode"));
		this.setPhoneNumber(request.getParameter("phoneNumber"));
		this.setDrivingLicense(request.getParameter("drivingLicense"));
		this.setDrivingLicenseExpiration(request.getParameter("drivingLicenseExpiration"));
		this.setDLFH(request.getParameter("DLFH"));
		this.setDLFHExpiration(request.getParameter("DLFHExpiration"));
		this.setBankName(request.getParameter("bankName"));
		this.setBankAccountNumber(request.getParameter("bankAccountNumber"));
		this.setVehicleMake(request.getParameter("vehicleMake"));
		this.setVehicleModel(request.getParameter("vehicleModel"));
		this.setVehicleYear(request.getParameter("vehicleYear"));
		this.setLicensePlate(request.getParameter("vehicleLicensePlate"));
		this.setCapacity(request.getParameter("vehicleCapacity"));
		
		fieldName.put("emailExist", "Email");
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getDrivingLicenseExpiration() {
		return drivingLicenseExpiration;
	}

	public void setDrivingLicenseExpiration(String drivingLicenseExpiration) {
		this.drivingLicenseExpiration = drivingLicenseExpiration;
	}

	public String getDLFH() {
		return DLFH;
	}

	public void setDLFH(String dLFH) {
		DLFH = dLFH;
	}

	public String getDLFHExpiration() {
		return DLFHExpiration;
	}

	public void setDLFHExpiration(String dLFHExpiration) {
		DLFHExpiration = dLFHExpiration;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleYear() {
		return vehicleYear;
	}

	public void setVehicleYear(String vehicleYear) {
		this.vehicleYear = vehicleYear;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
}
