package com.gcu.business;


import com.gcu.model.LoginModel;

/**
 * Interface for the logic service
 */
public interface LoginServiceInterface {


	/**
	 * Logs the user into the application
	 * @param loginModel LoginModel with the login credentials
	 * @return True - successfully logged in, False - unsuccessfully logged in
	 */
	public boolean login(LoginModel loginModel);
}
