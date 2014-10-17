package com.falcon.hosting.test.validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Conv {
	public static final String algorithm = "SHA-256";
	public String toConvert = "";
	private String converted = "";
	
	//Constructor converts string to hex via sha 256
	public SHA256Conv(String s) {
		toConvert = s;
		converted = convertStrToSHA256(s);
	}
	
	//Algorithm for converting string to hex
	private String convertStrToSHA256(String s) {
		if(s.length()==0) return "";
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
        md.update(s.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString();
	}
	
	//To string returns converted hex string
	public String toString() {
		return converted;
	}
}
