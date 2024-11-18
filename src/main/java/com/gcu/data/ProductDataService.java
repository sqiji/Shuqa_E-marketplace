	package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.ProductEntity;
import com.gcu.data.repository.ProductsRepository;

/**
 * Data Service for the products
 */
@Service
public class ProductDataService implements DataAccessInterface<ProductEntity> {

	@Autowired private ProductsRepository productsRepository;
	
	/**
	 * Constructor
	 * @param productsRepo Product repository
	 * @param dataSource DataSource
	 */
	public ProductDataService(ProductsRepository productsRepo) {
		productsRepository = productsRepo;
	}
	
	@Override
	public List<ProductEntity> findAll() {
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		try {
			var orderIterable = productsRepository.findAll();
			orderIterable.forEach(products::add);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	
	/*
	@Override
	public ProductEntity getByUser(String userId) {
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		try {
			var orderIterable = productsRepository.findAll();
			orderIterable.forEach(products::add);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	*/
	

	@Override
	public boolean create(ProductEntity product) {
		try {
			productsRepository.save(product);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public ProductEntity findById(ObjectId id) {
		Optional<ProductEntity> entityOp = productsRepository.findById(id);
		
		ProductEntity entity = null;
		if(entityOp.isPresent()) {
			entity = entityOp.get();
		}
		return entity;
	}

	@Override
	public boolean update(ProductEntity product) {
//	    String sql = "UPDATE products SET make = ?, model = ?, year = ?, price = ? WHERE id = " + product.getId();
//	    try {
//	    	jdbcTemplateObject.update(sql,product.getMake(),product.getModel(),product.getYear(),product.getPrice());
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//        return true;
		
		try {
			productsRepository.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(ProductEntity product) {
		try {
			productsRepository.deleteById(product.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public ProductEntity findByName(String name) {
		return null;
	}

	@Override
	public ProductEntity getByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
