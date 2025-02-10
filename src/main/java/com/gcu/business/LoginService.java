package com.gcu.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gcu.data.UserDataService;
import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UserRepository;
import com.gcu.model.LoginModel;

/**
 * Service for Login logic
 */
@Component
public class LoginService implements LoginServiceInterface
{

    @Autowired
    private UserRepository userRepository;  // Use UserRepository

	@Autowired
    private UserDataService userDataService;  // Use UserDataService, not UserRepository

    @Override
    public boolean login(LoginModel loginModel) {
        UserEntity user = userDataService.findByUsername(loginModel.getUsername());  
        if (user != null && user.getPassword().equals(loginModel.getPassword())) {
            return true;
        }
        return false;
    }


	// @Autowired
	// UserDataService service;


	// @Override
	// public boolean login(LoginModel loginModel) {
		
	// 	System.out.println("LoginService: Checking user credentials...");  // Debugging

	// 	UserEntity user = service.findByName(loginModel.getUsername());

	// 	if(user != null && user.getPassword().equals(loginModel.getPassword())) {

	// 		// User found and password matches
	// 		return true;
	// 	}
	// 	return false; // Invalid credentials
	// }
	
}

