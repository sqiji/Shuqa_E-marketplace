package com.gcu.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", 
			message = "Email must be in a valid format")
	private String email;
	
	@NotEmpty(message="A phone number is required")
	@Pattern(regexp = "^\\(?[0-9]{3}\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}$", 
			message = "Phone number must be in a valid format (e.g., 8005555555, 800 555 5555, or 800-555-5555)")
	private String phone;
	
	@NotEmpty(message="Username is required")
	@Size(min=5, max=70, message="Username must be at least 5 characters")
	private String username;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, message="Password must be at least 12 characters")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~])[A-Za-z\\d!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~]{8,}$",
         message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.")
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
