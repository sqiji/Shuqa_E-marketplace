package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gcu.data.UserDataService;
import com.gcu.data.entity.UserEntity;
import com.gcu.model.UserModel;

/**
 * Service for register logic
 */
@Component
public class RegistrationService implements RegistrationServiceInterface {

	//Declare service type of RegisterDataDervice
	@Autowired private UserDataService service;
	
	
	/**
	 * Method that get users from database
	 */
	@Override
	public List<UserModel> getUsers(){
		var registerEntities = service.findAll();
		
		List<UserModel> registerDomain = new ArrayList<UserModel>();
		for (var entity : registerEntities) {
			registerDomain.add(new UserModel(entity.getFirstName(), entity.getLastName(), entity.getPhone(),
					entity.getEmail(), entity.getPassword(), entity.getPassword()));
		}

		return registerDomain;
	}

	/**
	 * Method that create new user
	 */
	@Override
	public boolean register(UserModel register) {
		return service.create(new UserEntity(register.getFirstName(), register.getLastName(), register.getPhone(),
				register.getEmail(), register.getPassword(), register.getUsername()));
	}

}
	

