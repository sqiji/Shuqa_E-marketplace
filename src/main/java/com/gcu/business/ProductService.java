package com.gcu.business;

import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gcu.data.ProductDataService;
import com.gcu.data.entity.ProductEntity;
import com.gcu.model.ProductModel;

/**
 * Logic for the products
 */
@Component
public class ProductService implements ProductServiceInterface{

	@Autowired private ProductDataService service;
	
	@Override
	public List<ProductModel> getProducts() {
		var productEntities = service.findAll();
		
		List<ProductModel> productDomain = new ArrayList<ProductModel>();
		for(var entity : productEntities) {
			productDomain.add(new ProductModel(entity.getName(), entity.getYear(), entity.getDescription(), 
					Math.round(entity.getPrice() * 100.0) / 100.0, entity.getImage(), entity.getCreatedBy(), 
					entity.getPhone(), entity.getEmail(), entity.getOtherContacts(), entity.getId()));
		}
		
		return productDomain;	
	}
	
	@Override
	public boolean createProduct(ProductModel product) {
		return service.create(new ProductEntity(new ObjectId(), product.getName(), product.getDescription(), 
				product.getYear(),(float) product.getPrice(), product.getImage(), product.getCreatedBy()
				,product.getPhone(), product.getEmail(), product.getOtherContacts())); 
	}

	@Override
	public void updateProduct(ProductModel productModel) {
        ProductEntity entityToUpdate = new ProductEntity(productModel.getId(), productModel.getName(),
                productModel.getDescription(), productModel.getYear(), (float) productModel.getPrice(), 
                productModel.getImage(), productModel.getCreatedBy(), productModel.getPhone(),
                productModel.getEmail(), productModel.getOtherContacts());
        
        service.update(entityToUpdate);		
	}

	@Override
	public ProductModel getProductById(ObjectId productId) {
		
        ProductEntity entity = service.findById(productId);

        if (entity != null) {
            return new ProductModel(entity.getName(), entity.getYear(), entity.getDescription(),
                    Math.round(entity.getPrice() * 100.0) / 100.0, entity.getImage(), entity.getCreatedBy(), 
                    entity.getPhone(), entity.getEmail(), entity.getOtherContacts(), entity.getId());
        } else {
            return null;
        }
	}
	
	@Override
	public boolean delete(ObjectId id) {
		ProductEntity entity = new ProductEntity();
		
		entity.setId(id);
		return service.delete(entity);
	}
	
		
}
