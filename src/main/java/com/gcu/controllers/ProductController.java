package com.gcu.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
		
		System.out.println("Fetching product with ID: " + productId); 
		
		if (productModel == null) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
   		}
	    model.addAttribute("productModel", productModel);
	    //model.addAttribute("title", "Product's Details");
    	
		System.out.println("Product Model: " + productModel);
    	return "displayItem";
	}
	
	
	/**
	 * Opens the newProduct html file
	 * @param model Model for the webpage
	 * @param session Session that is used for remembering login
	 * @return Directs to the createProduct template
	 */
	@GetMapping(path="products/createProduct")
	public String displayCreateForm(Model model) {
		
		ProductModel productModel = new ProductModel();
    	model.addAttribute("productModel", productModel);
		
		return "createProduct";
	}
 
	/**
	 * Creates a new product for the application
	 * @param productModel ProductModel containing any information on the product
	 * @param bindingResult BindingResult used in verifying information
	 * @param model Model for the webpage
	 * @return Redirects to the products HTML template
	 */
	@PostMapping(path="products/createProduct")
	public String createProduct(@Valid ProductModel productModel, BindingResult bindingResult,
			@RequestParam(required = false) MultipartFile[] files, Model model) {

		

		// Debugging Step
		System.out.println("Received files: " + (files != null ? files.length : "null"));

		if (productModel.getYear() != null && !productModel.getYear().isEmpty()) {
			try {
				int year = Integer.parseInt(productModel.getYear());
				if (year < 1950 || year > 2025) {
					bindingResult.rejectValue("year", "error.productModel", "The year must be between 1950 and 2025");
				}
			} catch (NumberFormatException e) {
				bindingResult.rejectValue("year", "error.productModel", "Year must be a valid number");
			}
		}

		if (files == null) {
			System.out.println("File array is NULL");
		} else {
			for (MultipartFile file : files) {
				System.out.println("File received: " + file.getOriginalFilename() + " | Size: " + file.getSize());
			}
		}

		if(files == null || files.length == 0 || files[0].isEmpty()){
			System.out.println("File upload failed: No files received!");
			model.addAttribute("error", "At least one photo is required");
			model.addAttribute("title", "Post an Item");
			model.addAttribute("productModel", productModel);
			return "createProduct";
		}

		 // Validate file types
		 for (MultipartFile file : files) {
			String contentType = file.getContentType();
			if (contentType == null || !contentType.startsWith("image/")) {
				model.addAttribute("error", "Invalid file type. Please upload only image files.");
				model.addAttribute("title", "Post an Item");
				model.addAttribute("productModel", productModel);
				return "createProduct";
			}
		}

		try{

			//Ensure the uploaded file is exist
			String uploadDir = "uploads/";
			Files.createDirectories(Paths.get(uploadDir));

			List<String> imagePaths = new ArrayList<>();
			
			for (MultipartFile file : files){
				if(!file.isEmpty()){
					//Save the picture
					String imageName = StringUtils.cleanPath(file.getOriginalFilename());
					Path imagePath = Paths.get(uploadDir + imageName);
					Files.write(imagePath, file.getBytes());
                	imagePaths.add("/uploads/" + imageName);
				}
			}

			
			productModel.setImages(imagePaths);
		} catch(IOException e){
			throw new RuntimeException("File upload failed", e);
		}

		// Check for basic field validations FIRST
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Post an Item");
			return "createProduct";
		}

		//Set createBy
	
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
	
	@PostMapping(path="products/editProduct/{productId}")
	public String updateProduct(@PathVariable ObjectId productId,
							@Valid @ModelAttribute("productModel") ProductModel productModel,
							BindingResult bindingResult,
							@RequestParam(value = "files", required = false) MultipartFile[] files,
							Model model) {

		// Validate year if provided
		if (productModel.getYear() != null && !productModel.getYear().isEmpty()) {
			try {
				int year = Integer.parseInt(productModel.getYear());
				if (year < 1950 || year > 2025) {
					bindingResult.rejectValue("year", "error.productModel", "The year must be between 1950 and 2025");
				}
			} catch (NumberFormatException e) {
				bindingResult.rejectValue("year", "error.productModel", "Year must be a valid number");
			}
		}

		// Check if new files were uploaded (optional for update)
		if (files != null && files.length > 0 && !files[0].isEmpty()) {
			// Validate file types if files were uploaded
			for (MultipartFile file : files) {
				String contentType = file.getContentType();
				if (contentType == null || !contentType.startsWith("image/")) {
					bindingResult.rejectValue("files", "error.productModel", "Invalid file type. Please upload only image files.");
					break;
				}
			}
		}

		// Check for any validation errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Edit Product");
			
			// Load existing images to redisplay them
			ProductModel existingProduct = productService.getProductById(productId);
			if (existingProduct != null && existingProduct.getImages() != null) {
				productModel.setImages(existingProduct.getImages());
			}
			
			return "editProduct";
		}

		try {
			// Handle file upload if new files were provided
			List<String> imagePaths = new ArrayList<>();
			if (files != null && files.length > 0 && !files[0].isEmpty()) {
				String uploadDir = "uploads/";
				Files.createDirectories(Paths.get(uploadDir));

				for (MultipartFile file : files) {
					if (!file.isEmpty()) {
						String imageName = StringUtils.cleanPath(file.getOriginalFilename());
						Path imagePath = Paths.get(uploadDir + imageName);
						Files.write(imagePath, file.getBytes());
						imagePaths.add("/uploads/" + imageName);
					}
				}
			}

			// Get the existing product
			ProductModel existingProduct = productService.getProductById(productId);
			if (existingProduct == null) {
				return "redirect:/myproducts";
			}

			// Update product fields
			existingProduct.setName(productModel.getName());
			existingProduct.setDescription(productModel.getDescription());
			existingProduct.setPrice(productModel.getPrice());
			existingProduct.setYear(productModel.getYear());
			existingProduct.setLocation(productModel.getLocation());
			existingProduct.setPhone(productModel.getPhone());
			existingProduct.setEmail(productModel.getEmail());
			existingProduct.setOtherContacts(productModel.getOtherContacts());

			// Only update images if new ones were uploaded
			if (!imagePaths.isEmpty()) {
				existingProduct.setImages(imagePaths);
			}

			// Update the product
			productService.updateProduct(existingProduct);

			return "redirect:/myproducts";

		} catch (IOException e) {
			model.addAttribute("error", "File upload failed: " + e.getMessage());
			model.addAttribute("title", "Edit Product");
			
			// Load existing images to redisplay them
			ProductModel existingProduct = productService.getProductById(productId);
			if (existingProduct != null && existingProduct.getImages() != null) {
				productModel.setImages(existingProduct.getImages());
			}
			
			return "editProduct";
		}
	}


	
	// /**
	//  * Edits the given product
	//  * @param productId ProductID from the url
	//  * @param productModel ProductModel with new information
	//  * @param bindingResult Binding Result used for data validation
	//  * @param model Model for website generation
	//  * @return Redirect to products if the update was successful
	//  */
	// @PostMapping(path="products/editProduct/{productId}")
	// public String updateProduct(@PathVariable ObjectId productId, 
    //                       @Valid @ModelAttribute("productModel") ProductModel productModel,
    //                       BindingResult bindingResult,
    //                       @RequestParam(value = "files", required = false) MultipartFile[] files, 
    //                       Model model) throws IOException {

	// 	 // Manually validate price if binding failed
	// 	 if (bindingResult.hasFieldErrors("price")) {
	// 		bindingResult.rejectValue("price", "error.price", "Please enter a valid price");
	// 	}
		
	// 	//Check for any validation errors (including @Valid annotations)
	// 	if (bindingResult.hasErrors()) {
	// 		model.addAttribute("title", "Edit Product");
			
	// 		// Ensure we have the existing images for redisplay
	// 		ProductModel existingProduct = productService.getProductById(productId);
	// 		if (existingProduct != null && existingProduct.getImages() != null) {
	// 			productModel.setImages(existingProduct.getImages());
	// 		}
			
	// 		return "editProduct";
	// 	}

	// 	//Set the creator (if needed)
	// 	productModel.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

	// 	//Fetch the existing product
	// 	ProductModel existingProduct = productService.getProductById(productId);
	// 	if (existingProduct == null) {
	// 		return "redirect:/myproducts";
	// 	}

	// 	//Handle file uploads
	// 	List<String> imagePaths = handleFileUploads(files, bindingResult, model);
	// 	if (bindingResult.hasErrors()) {
	// 		model.addAttribute("title", "Edit Product");
	// 		return "editProduct";
	// 	}


	// 	//Update product fields
	// 	updateProductFields(existingProduct, productModel, imagePaths);

	// 	//Save updated product
	// 	productService.updateProduct(existingProduct);

	// 	return "redirect:/myproducts";
	// }


	// // Helper method for file upload handling
	// private List<String> handleFileUploads(MultipartFile[] files, BindingResult bindingResult, Model model) 
	// 	throws IOException {
		
	// 	List<String> imagePaths = new ArrayList<>();
		
	// 	if (files != null && files.length > 0 && !files[0].isEmpty()) {
	// 		String uploadDir = "uploads/";
	// 		Files.createDirectories(Paths.get(uploadDir));

	// 		for (MultipartFile file : files) {
	// 			if (!file.isEmpty()) {
	// 				String contentType = file.getContentType();
	// 				if (contentType == null || !contentType.startsWith("image/")) {
	// 					bindingResult.rejectValue("files", "error.productModel", "Only image files are allowed");
	// 					break;
	// 				}

	// 				String imageName = StringUtils.cleanPath(file.getOriginalFilename());
	// 				Path imagePath = Paths.get(uploadDir + imageName);
	// 				Files.write(imagePath, file.getBytes());
	// 				imagePaths.add("/uploads/" + imageName);
	// 			}
	// 		}
	// 	}
		
	// 	return imagePaths;
	// }

	// // Helper method for updating product fields
	// private void updateProductFields(ProductModel existingProduct, 
	// 							ProductModel updatedProduct, 
	// 							List<String> newImagePaths) {
		
	// 	existingProduct.setName(updatedProduct.getName());
	// 	existingProduct.setDescription(updatedProduct.getDescription());
	// 	existingProduct.setPrice(updatedProduct.getPrice());
	// 	existingProduct.setYear(updatedProduct.getYear());
	// 	existingProduct.setPhone(updatedProduct.getPhone());
	// 	existingProduct.setEmail(updatedProduct.getEmail());
	// 	existingProduct.setOtherContacts(updatedProduct.getOtherContacts());
		
	// 	if (newImagePaths != null && !newImagePaths.isEmpty()) {
	// 		existingProduct.setImages(newImagePaths);
	// 	}
	// }
	// @PostMapping(path="products/editProduct/{productId}")
	// public String updateProduct(@PathVariable ObjectId productId, @Valid @ModelAttribute("productModel") ProductModel productModel, 
	// 		@RequestParam(value = "file", required = false) MultipartFile[] files, BindingResult bindingResult, Model model) throws IOException{

	   
	// 	if(bindingResult.hasErrors()) {
	// 		model.addAttribute("title", "Edit Product");
	// 		return "editProduct";
	// 	}

	// 	//Check year validation
	// 	if (productModel.getYear() != null && !productModel.getYear().isEmpty()) {
	// 		try {
	// 			int year = Integer.parseInt(productModel.getYear());
	// 			if (year < 1950 || year > 2025) {
	// 				bindingResult.rejectValue("year", "error.productModel", "The year must be between 1950 and 2025");
	// 			}
	// 		} catch (NumberFormatException e) {
	// 			bindingResult.rejectValue("year", "error.productModel", "Year must be a valid number");
	// 		}
	// 	}

	//     productModel.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());


	// 	//Fetching the existing product form database
	//     ProductModel existingProduct = productService.getProductById(productId);
	// 	if(existingProduct == null){
	// 		return "dredirect:/myproducts";
	// 	}

	// 	//File upload validation for images
	// 	List<String> imagePaths = new ArrayList<>();
	// 	if(files != null && files.length > 0 && !files[0].isEmpty()){
			
	// 		// Upload new image if valid
	// 		String uploadDir = "uploads/";
    // 		Files.createDirectories(Paths.get(uploadDir));

	// 		for (MultipartFile file : files) {
	// 			if (!file.isEmpty()) {
	// 				// Validate file type (only images allowed)
	// 				String contentType = file.getContentType();
	// 				if (contentType == null || !contentType.startsWith("image/")) {
	// 					model.addAttribute("error", "Only image files are allowed.");
	// 					model.addAttribute("title", "Edit Product");
	// 					return "editProduct";
	// 				}
	
	// 				// Save the image if valid
	// 				String imageName = StringUtils.cleanPath(file.getOriginalFilename());
	// 				Path imagePath = Paths.get(uploadDir + imageName);
	// 				Files.write(imagePath, file.getBytes());
	// 				imagePaths.add("/uploads/" + imageName);
	// 			}
	// 		}

	// 		// Only set new images if uploaded, otherwise keep existing images
	// 		if (!imagePaths.isEmpty()) {
	// 			existingProduct.setImages(imagePaths);
	// 		}
	// 	}

	// 	// Preserve existing images if no new images are uploaded
	// 	if (imagePaths.isEmpty()) {
	// 		existingProduct.setImages(existingProduct.getImages());
	// 	}

	// 	// Only update the changed fields
	// 	existingProduct.setName(productModel.getName());
	// 	existingProduct.setDescription(productModel.getDescription());
	// 	existingProduct.setPrice(productModel.getPrice());
	// 	existingProduct.setYear(productModel.getYear());
	// 	existingProduct.setPhone(productModel.getPhone());
	// 	existingProduct.setEmail(productModel.getEmail());
	// 	existingProduct.setOtherContacts(productModel.getOtherContacts());

	// 	//Save updated product
	// 	productService.updateProduct(existingProduct);
	//     return "redirect:/myproducts";
	// }


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


	//Adding search to the website
	@GetMapping(path="products/search")
	public String searchProducts(@RequestParam("query") String query, Model model) {
		List<ProductModel> searchResults = productService.searchProducts(query);
		model.addAttribute("products", searchResults);
		return "products"; // Return to the products page
	}


}
	