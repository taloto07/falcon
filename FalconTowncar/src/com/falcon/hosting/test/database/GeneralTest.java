package com.falcon.hosting.test.database;

import org.apache.commons.lang3.StringUtils;

public class GeneralTest {
	public static void main(String[] args){
		String str = "123.3";
		
		System.out.println(StringUtils.isNumeric(str));
		
	}
}
