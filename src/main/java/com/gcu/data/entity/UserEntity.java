package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Instance of a user from the database
 */
@Document(collection="users")
public class UserEntity {

	@Id		
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	
	@Indexed(unique=true) private String password;
	
	@Indexed(unique=true) private String username;
	@Indexed(unique=true) private String email;
	
	
	/**
	 * Non-Default constructor for the UserEntity
	 * @param firstName		user first name
	 * @param lastName		user last name    
	 * @param phone			user phone number
	 * @param email			user email
	 * @param password		user password
	 * @param username		user username
	 */
	public UserEntity(String firstName, String lastName, String phone, String email, String password,
			String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	
	/**
	 * default constructor
	 */
	public UserEntity() {
		super();
		/*
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.email = "";
		this.password = "";
		this.username = "";
		*/
	}

	/**
	 * Get method for ID
	 * @return   id
	 */
	public String getId() {
		return id;
	}


	/**
	 * Setter for the id
	 * @param id The id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * Get method for first name
	 * @return   firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for first name
	 * @param firstName First name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get method for last name
	 * @return  lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the lastName
	 * @param lastName Last Name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get method for phone
	 * @return   phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * Default setter for phone
	 * @param phone Phone to set 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get method for email
	 * @return   email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * Setter for email
	 * @param email Email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get method for password
	 * @return   password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Setter for the password
	 * @param password password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get method for username
	 * @return   username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Setter for the username
	 * @param username Username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	} 
	
}
