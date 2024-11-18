package com.gcu.business;

import java.util.List;
import com.gcu.model.UserModel;

/**
 * Interface for the RegistrationService
 */
public interface RegistrationServiceInterface {
	
	
	/**
	 * Gets all users
	 * @return List of user models
	 */
	public List<UserModel> getUsers();
	
	/**
	 * Registers the User
	 * @param register RegisterModel with all the information on the registration
	 * @return True - successful register, False - unsuccessful register
	 */
	public boolean register(UserModel register);

}
