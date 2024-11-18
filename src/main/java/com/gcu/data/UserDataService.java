package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UserRepository;

/**
 * Data Service for user data
 */
@Service
public class UserDataService implements DataAccessInterface<UserEntity> {

	//Declare variables
	@Autowired private UserRepository registerRepository;
	
	
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
}