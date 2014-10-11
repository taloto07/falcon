package com.falcon.hosting.test.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.falcon.hosting.doa.Customer;

public class ValidateCar {
	public static void main(String[] args){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Car car = new Car();
		car.setManufacturer("Apple");
		car.setLicensePlate("afd45");
		car.setSitCount(2);
		car.setPassword("12345678");
		car.setConfirmPassword("123456780");
		car.setCreditCard("tex");
		
		Set<ConstraintViolation<Car>> validatorCar = validator.validate(car);
		
		if (validatorCar.isEmpty()){
			System.out.println("Car is valid.");
		}else{
			for (ConstraintViolation<Car> cv: validatorCar){
				System.out.print(cv.getPropertyPath().toString() + ": ");
				System.out.println(cv.getMessage());
				
			}
		}
		
//		System.out.println();
//		
//		Customer cust = new Customer();
//		cust.setCreditCardNumber("test");
//		
//		Set<ConstraintViolation<Customer>> validatorCustomer = validator.validate(cust);
//		if (validatorCustomer.isEmpty()){
//			System.out.println("Customer is valid");
//		}else{
//			for (ConstraintViolation<Customer> c: validatorCustomer){
//				System.out.print(c.getPropertyPath().toString() + ": ");
//				System.out.println(c.getMessage());
//				
//			}
//		}
	}
}
