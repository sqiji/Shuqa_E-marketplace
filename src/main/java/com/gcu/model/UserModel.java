package com.gcu.model;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * User Model containing all necessary information
 */
public class UserModel {
	
	private String userId;

	@NotEmpty(message="First name is required")
	@Size(min=3, max=100, message="First name should be at least 3 characters")
	private String firstName;
	
	@NotEmpty(message="Last name is required")
	@Size(min=3, max=100, message="Last name should be at least 3 characters")
	private String lastName;
	
	@NotEmpty(message="Email is required")
	@Size(min=1, message="Email is required")
	@Email(message="Must be a vaild email address")
	private String email;
	
	@NotEmpty(message="A phone number is required")
	@Size(min=10, max=10, message="Must be exactly 10 characters")
	private String phone;
	
	@NotEmpty(message="Username is required")
	@Size(min=5, max=70, message="Username must be at least 5 characters")
	private String username;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, max=70, message="Password must be at least 8 characters")
	private String password;

	
	
	
	
	
	/**
	 * Non Default constructor
	 * @param firstName user first name
	 * @param lastName  user last name
	 * @param email  user email
	 * @param phone  user phone number
	 * @param username  user username
	 * @param password  user password
	 */
	public UserModel(
			String firstName,
			String lastName,
			String email,
			String phone,
			String username,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
	
	
	/**
	 * Default Constructor for the user model
	 */
	public UserModel() {
		super();
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.phone = "";
		this.username = "";
		this.password = "";
	}
	
	public String getUserID() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Simple getter for the First Name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Simple setter for the First Name
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Simple getter for the Last Name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Simple setter for the Last Name
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Simple getter for the email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Simple setter for the email
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Simple getter for the phone
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Simple setter for the phone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Simple getter for the username
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Simple setter for the username
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Simple getter for the password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Simple setter for the password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
