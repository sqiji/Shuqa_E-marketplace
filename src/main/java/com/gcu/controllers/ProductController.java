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
	
	
	/*
	@GetMapping(path="/byUser")
	public String displayProductsByUser(Model model) {
		List<ProductModel> products = productService.getProducts(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("title", "Our cars");
    	model.addAttribute("products", products);
    	model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	return "products";
	}
	*/
	
	
	
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
    		model.addAttribute("title", "Post Item");
    		return "createProduct";
    	}
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		productModel.setCreatedBy(auth.getName());
		
		productService.createProduct(productModel);
		
		List<ProductModel> products = productService.getProducts();
		
		model.addAttribute("products", products);
		
		return "redirect:/myproducts";
	}
	
	/**
	 * Loads edit product page
	 * @param model Model for webpage
	 * @param productId ID of product to edit
	 * @return Redirects to products page
	 */
	@GetMapping(path="products/editProduct/{productId}")
	public String displayEdit(@PathVariable ObjectId productId, Model model) {
	    ProductModel productModel = productService.getProductById(productId);

	    // Check if the product exists
	    if (productModel == null) {
	        return "redirect:/myproducts";
	    }
	    
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    
	    // Check to see if user made this
	    if(!productModel.getCreatedBy().equals(username)) {
	    	return "redirect:/products";
	    }
	    
	    model.addAttribute("productModel", productModel);
	    model.addAttribute("title", "Edit Product");
	    model.addAttribute("user", username);

	    return "editProduct";
	}
	
	/**
	 * Edits the given product
	 * @param productId ProductID from the url
	 * @param productModel ProductModel with new information
	 * @param bindingResult Binding Result used for data validation
	 * @param model Model for website generation
	 * @return Redirect to products if the update was successful
	 */
	@PostMapping(path="products/editProduct/{productId}")
	public String updateProduct(@PathVariable ObjectId productId, @Valid ProductModel productModel, BindingResult bindingResult, Model model) {
	    if(bindingResult.hasErrors()) {
	        model.addAttribute("title", "Edit Product");
	        return "editProduct";
	    }
	    
	    productModel.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

	    productModel.setId(productId);
	    productService.updateProduct(productModel);

	    return "redirect:/myproducts";
	}
	
	/**
	 * Deletes the product on post request
	 * @param model Model for website generation
	 * @param id ProductID from the url
	 * @return Redirect back to products
	 */
	@PostMapping(path="products/deleteProduct/{id}")
	public String deleteProduct( Model model, @PathVariable("id") ObjectId id) {
		productService.delete(id);
		
		List<ProductModel> products = productService.getProducts();
		
		model.addAttribute("products", products);
		return "redirect:/myproducts";
	}
	
	
}
