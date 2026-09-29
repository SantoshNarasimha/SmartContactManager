package com.api.demo.entities;

import java.util.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USER")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="Name can not be empty")
	@Size(min = 3, max = 50, message = "Name size must be between 3 and 50 characters")
	private String name;
	
	@Column(unique=true)
	private String email;
	
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(
	    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
	    message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character"
	)
	private String password;

	private String role;
	private String enabled;
	private String imageUrl;
	
	@Column(length=500)
	@NotBlank(message= "please enter about yourself here! ")
	private String about;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private List<Contacts> contacts= new ArrayList<>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	
	
	
	public List<Contacts> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about + ", contacts=" + contacts
				+ "]";
	}
	
}