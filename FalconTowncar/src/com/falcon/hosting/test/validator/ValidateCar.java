package com.falcon.hosting.test.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.falcon.hosting.doa.User;

public class ValidateCar {
	public static void main(String[] args){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Car car = new Car();
		car.setManufacturer("toyota");
		car.setLicensePlate("adfa");
		car.setSitCount(2);
		car.setPassword("12345678");
		car.setConfirmPassword("23456789");
		
		Set<ConstraintViolation<Car>> validatorCar = validator.validate(car);
		
		if (validatorCar.isEmpty()){
			System.out.println("Car is valid.");
		}else{
			for (ConstraintViolation<Car> cv: validatorCar){
				System.out.println(cv.getMessage());
			}
		}
	}
}
