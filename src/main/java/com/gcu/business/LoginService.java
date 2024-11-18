package com.gcu.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcu.data.UserDataService;
import com.gcu.data.entity.UserEntity;
import com.gcu.model.LoginModel;

/**
 * Service for Login logic
 */
@Component
public class LoginService implements LoginServiceInterface
{
	@Autowired
	UserDataService service;
	
	 @Override
	    public boolean login(LoginModel loginModel) {
	        UserEntity user = service.findByName(loginModel.getUsername());
	        if (user != null && user.getPassword().equals(loginModel.getPassword())) {
	            // User found and password matches
	            return true;
	        }
	        return false; // Invalid credentials
	    }
	
}

