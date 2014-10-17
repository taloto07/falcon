package com.falcon.hosting.test.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.falcon.hosting.doa.Customer;
import com.falcon.hosting.guice.MainModule;
import com.falcon.hosting.service.FalconPersistenceInitializer;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ValidateCar {
	private static FalconService service;
	private static Injector injector;
	
	public static void main(String[] args){
		injector = Guice.createInjector(new MainModule());
		injector.getInstance(FalconPersistenceInitializer.class);
		service = injector.getInstance(FalconService.class);	
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
//		Car car = new Car();
//		car.setManufacturer("Apple");
//		car.setLicensePlate("afd45");
//		car.setSitCount(2);
//		car.setPassword("12345678");
//		car.setConfirmPassword("123456780");
//		car.setCreditCard("tex");
//		
//		Set<ConstraintViolation<Car>> validatorCar = validator.validate(car);
//		
//		if (validatorCar.isEmpty()){
//			System.out.println("Car is valid.");
//		}else{
//			for (ConstraintViolation<Car> cv: validatorCar){
//				System.out.print(cv.getPropertyPath().toString() + ": ");
//				System.out.println(cv.getMessage());
//				
//			}
//		}
		
//		System.out.println();
		
//		RegisterDriverValidation validate = new RegisterDriverValidation();
//		validate.setFalconService(service);
//		validate.setFirstname("c");
//		validate.setLastname("lim");
//		validate.setEmail("chamnaplim@yahoo");
//		validate.setConfirmEmail("chamnaplim@yahoo");
//		validate.setDob("12-12-12");
//		validate.setPassword("1234567");
//		validate.setConfirmPassword("1234567");
//		validate.setAddress("1214 145th st sw");
//		validate.setCity("redmond");
//		validate.setState("1");
//		validate.setZipcode("12345");
//		validate.setPhoneNumber("01231313131");
//		validate.setDrivingLicense("adsf");
//		validate.setDLFH("dlfh");
//		validate.setDrivingLicenseExpiration("12-12-12");
//		validate.setDLFHExpiration("12-12-12");
//		validate.setVehicleMake("1");
//		validate.setVehicleModel("2");
//		validate.setVehicleYear("1994");
//		validate.setBankAccountNumber("123");
//		validate.setBankName("b");
//		validate.setLicensePlate("123");
//		validate.setCapacity("4");
		
//		Set<ConstraintViolation<RegisterDriverValidation>> validateRegisterDriver = validator.validate(validate);
//		
//		if (validateRegisterDriver.isEmpty()){
//			System.out.println("Register Driver is valid.");
//		}else{
//			for (ConstraintViolation<RegisterDriverValidation> rv: validateRegisterDriver){
//				System.out.println(rv.getPropertyPath().toString() + ": " + rv.getMessage());
//			}
//		}
	}
}
