package com.falcon.hosting.restful.helper;

import com.falcon.hosting.doa.User;

public class UserFromJson {
	private String firstName;	// case sensitive for key in json to deserialize 
	private String lastName;
	private String email;
	private String password;
	private String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isNull(){
		if (firstName == null || lastName == null || email == null || password == null)
			return true;
		else 
			return false;
	}
	
	public void copyToUser(User user){
		user.setEmail(email);
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setPassword(password);
	}
	
	@Override
	public String toString() {
		String result = null;
		
		result = "First Name: " + firstName + "\n";
		result += "Last Name: " + lastName + "\n";
		result += "Email: " + email + "\n";
		result += "Image: " + image + "\n";
		
		return result;
	}
	
	
}
