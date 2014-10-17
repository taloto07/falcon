package com.falcon.hosting.test.database;

import org.apache.commons.lang3.StringEscapeUtils;

public class GeneralTest {
	public static void main(String[] args){
		String message="<p>password doesn't match'.</p>";
		String re = message.replace("'", "&quot;");
		System.out.println(re);
	}
}
