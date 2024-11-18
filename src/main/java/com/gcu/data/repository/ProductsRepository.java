package com.gcu.data.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcu.data.entity.ProductEntity;

/**
 * Repository for the Product database logic
 */
public interface ProductsRepository extends MongoRepository<ProductEntity, ObjectId> {
	void deleteById(ObjectId id);
	
	
}
