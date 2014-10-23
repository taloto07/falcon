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
		
		DriverTempValidation form = new DriverTempValidation();
		form.setFirstname("cham");
		form.setLastname("lim");
		form.setPassword("123");
		form.setConfirmPassword("13");
		form.setEmail("email@email.com");
		form.setConfirmEmail("email@email.com");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<DriverTempValidation>> isFormValid = validator.validate(form);
		
		if (!isFormValid.isEmpty()){
			for(ConstraintViolation<DriverTempValidation> dtd: isFormValid){
				System.out.print(dtd.getPropertyPath() + " = " + dtd.getMessage() + "\n");
			}
		}else{
			System.out.println("Form is valid!!!");
		}
		
	}
}
