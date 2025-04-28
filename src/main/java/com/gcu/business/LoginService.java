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
    private UserDataService service;  // Use UserDataService, not UserRepository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean login(LoginModel loginModel) {
        
        UserEntity user = service.findByUsername(loginModel.getUsername());  
        if (user != null && passwordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }
}
	


