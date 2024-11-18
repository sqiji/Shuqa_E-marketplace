package com.gcu.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model for the LoginController
 */
public class LoginModel 
{
	@NotEmpty(message="User name is a reqiured field")
	@Size(min=5, max=50, message="Username must be at least 5 characters")
	private String username;
	
	@NotEmpty(message="Password is required")
	@Size(min=1, max=50, message="Password must be at least 8 characters")
	private String password;
	
	
	
	
	public LoginModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	

	public LoginModel() {
		super();
		this.username = "";
		this.password = "";
	}



	/**
	 * Simple getter for the username
	 * @return The username
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Simple getter for the password
	 * @return The password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Simple setter for the username
	 * @param name Username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Simple setter for the password
	 * @param pass Password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
}
