package com.api.demo.entities;

import org.springframework.stereotype.Controller;

@Controller
public class login_validation {
	
	private String email;
	
	private String password;

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

	public login_validation() {
		super();
	}

	@Override
	public String toString() {
		return "login_validation [email=" + email + ", password=" + password + "]";
	}
	
	
}
