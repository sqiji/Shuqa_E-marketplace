package com.gcu.data;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * Interface to access data
 * @param <T> Entity of the data accessed
 */
public interface DataAccessInterface<T> {
	/**
	 * Finds all
	 * @return List of entities
	 */
	public List<T> findAll();
	/**
	 * Finds item by id
	 * @param id ID of item
	 * @return The Item entity
	 */
	public T findById(ObjectId id);
	/**
	 * Creates an item
	 * @param t Entity of the item
	 * @return True - success, False - not success
	 */
	public boolean create(T t);
	/**
	 * Updates an item
	 * @param t New item
	 * @return True - success, False - not success
	 */
	public boolean update(T t);
	/**
	 * Deletes an element
	 * @param t Element to delete
	 * @return True - deleted, False - not deleted
	 */
	public boolean delete(T t);

	/**
	 * Find element by it's name
	 * @param name Searched by username for User, and name for product
	 * @return Found element
	 */
	public T findByName(String name);
	

	/**
	 * Find element by it's userId
	 * @param userId Searched by userId for User, and productId for product
	 * @return Found element
	 */
	public T getByUser(String id);
	
}
