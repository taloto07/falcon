package com.falcon.hosting.test.database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class GeneralTest {
	public static void main(String[] args) throws ParseException{
		String email = "";
		
		if (StringUtils.isAlphanumeric(email)){
			System.out.println("This is number");
		}else{
			System.out.println("This is NOT number");
		}
	}
}
