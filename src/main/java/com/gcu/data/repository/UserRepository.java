package com.gcu.data.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcu.data.entity.UserEntity;



/**
 * RegisterRepository interface that inheritance from CrudRepository
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {
	UserEntity findByUsername(String username);
}
