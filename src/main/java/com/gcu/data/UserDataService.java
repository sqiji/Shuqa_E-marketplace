package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UserRepository;
import com.gcu.model.UserModel;

/**
 * Data Service for user data
 */
@Service
public class UserDataService implements DataAccessInterface<UserEntity> {

	//Declare variables
	@Autowired private UserRepository registerRepository;
	

	public UserEntity findByUsername(String username) {
        return registerRepository.findByUsername(username);  // Delegates to repository
    }
	
	/**
	 * Constructor for UserDataService
	 * @param registerRepo Register repository
	 * @param dataSource DataSource
	 */
	public UserDataService(UserRepository registerRepo) {
		registerRepository = registerRepo;
	}
	
	/**
	 * Method that return user list
	 */
	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> users = new ArrayList<UserEntity>();
		try {
			var regesterIterable = registerRepository.findAll();
			regesterIterable.forEach(users::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Method that create a new user
	 */
	@Override
	public boolean create(UserEntity register) {
		try {
			registerRepository.save(register);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Method that update the user
	 */
	@Override
	public boolean update(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Method that delete the user
	 */
	@Override
	public boolean delete(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Method that return user by name
	 */
	@Override
	public UserEntity findByName(String name) {
		try {
			var user = registerRepository.findByUsername(name);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	


	@Override
	public UserEntity findById(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity getByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException{
		UserEntity user = registerRepository.findByEmail(email);

		
		if(user != null){
			user.setResetPasswordToken(token); 
			registerRepository.save(user);
		} else {
			throw new UserNotFoundException("Invalid email address, please enter a valid email");
		}
	}

	public UserEntity getPassword(String resetPasswordToken){
		return registerRepository.findByResetPasswordToken(resetPasswordToken);
	}

	public void updatePassword(UserEntity user, String newPassword){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String emcodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(emcodedPassword);
		user.setResetPasswordToken(null);
		registerRepository.save(user);	
	} 
}