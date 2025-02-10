package com.gcu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.ProductServiceInterface;
import com.gcu.model.ProductModel;

import jakarta.validation.Valid;

/**
 * Controller for the products
 */
@Controller
@RequestMapping(path={"/"})
public class ProductController {
	
	
	@Autowired
	private ProductServiceInterface productService;
	
	
	/**
	 * Displays the products table
	 * @param model Model for page generation
	 * @param session Session info for checking if user is logged in
	 * @return Loads the products template
	*/
	@GetMapping(path="products")
	public String displayProducts(Model model) {
		List<ProductModel> products = productService.getProducts();
    	model.addAttribute("title", "Listed Items");
    	model.addAttribute("products", products);
    	model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	return "products";
	}
	
	
	@GetMapping(path="myproducts")
	public String displayUserProducts(Model model) {
	    // Get the username of the currently authenticated user
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();

	    // Retrieve all products and filter them by the createdBy field
	    List<ProductModel> products = productService.getProducts();
	    List<ProductModel> userProducts = new ArrayList<>();

	    for (ProductModel product : products) {
	        if (product.getCreatedBy().equals(username)) {
	            userProducts.add(product);
	        }
	    }

	    // Add attributes to the model
	    model.addAttribute("title", "My Products");
	    model.addAttribute("products", userProducts);
	    model.addAttribute("user", username);

	    // Return the separate view for user-specific products
	    return "myproducts";
	}
	
	
	/**
	 * Displays the products table
	 * @param model Model for page generation
	 * @param session Session info for checking if user is logged in
	 * @return Loads the products template
	*/
	@GetMapping(path="products/displayItem/{productId}")
	public String displaySingleProducts(@PathVariable ObjectId productId, Model model) {
		ProductModel productModel = productService.getProductById(productId);	    	    
	    model.addAttribute("productModel", productModel);
	    model.addAttribute("title", "Product's Details");
    	
    	return "displayItem";
	}
	
	
	/**
	 * Opens the newProduct html file
	 * @param model Model for the webpage
	 * @param session Session that is used for remembering login
	 * @return Directs to the createProduct template
	 */
	@GetMapping(path="products/newProduct")
	public String displayNewProductForm(Model model) {
		
		ProductModel productModel = new ProductModel();
    	model.addAttribute("productModel", productModel);
    	model.addAttribute("title", "New Product");
		
		return "createProduct";
	}
 
	/**
	 * Creates a new product for the application
	 * @param productModel ProductModel containing any information on the product
	 * @param bindingResult BindingResult used in verifying information
	 * @param model Model for the webpage
	 * @return Redirects to the products HTML template
	 */
	@PostMapping(path="products/newProduct")
	public String createProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
    	{
    		model.addAttribute("title", "Post an Item");
    		return "createProduct";
    	}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		productModel.setCreatedBy(auth.getName());
		
		productService.createProduct(productModel);
		
		List<ProductModel> products = productService.getProducts();
		
		model.addAttribute("products", products);
		
		return "redirect:/myproducts";
	}

}
