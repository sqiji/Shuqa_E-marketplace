package com.gcu.data.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gcu.data.entity.ProductEntity;

/**
 * Repository for the Product database logic
 */
public interface ProductsRepository extends MongoRepository<ProductEntity, ObjectId> {
	void deleteById(ObjectId id);

	@Query("{ '$or': [ { 'name': { '$regex': ?0, '$options': 'i' } }, { 'description': { '$regex': ?0, '$options': 'i' } } ] }")
    List<ProductEntity> getByNameOrDescription(String query);
	
}
