package com.gcu.business;

import java.util.List;

import org.bson.types.ObjectId;

import com.gcu.model.ProductModel;

/**
 * Interface for the product service's methods
 */
public interface ProductServiceInterface {

	/**
	 * Gets all products
	 * @return List of product models
	 */
	public List<ProductModel> getProducts();
	
	/**
	 * Creates a product
	 * @param product Product to create and add to DB
	 * @return True - successful addition, False - unsuccessful addition
	 */
	public boolean createProduct(ProductModel product);
	
	/**
     * Updates an existing product.
     * 
     * @param productModel The product model containing updated information.
     */
    void updateProduct(ProductModel productModel);

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    ProductModel getProductById(ObjectId productId);
    
    /**
     * Deletes a product by id
     * @param id Id of the product to delete
     * @return True - delete was successful, False - delete was unsuccessful
     */
	public boolean delete(ObjectId id);
}
