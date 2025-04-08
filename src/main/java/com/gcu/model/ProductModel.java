package com.gcu.model;


import java.util.List;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Model for products
 */
public class ProductModel {
	
	private ObjectId id;
	
	@NotEmpty(message="name is required")
	@Size(min = 5, max = 50, message="Name must be between 5 and 50 characters")
	private String name;
	
	@NotEmpty(message="Description is required")
	@Size(min = 10, max = 500, message="Description must be between 10 and 50 characters")
	private String description;
	
	@Min(value = 1950, message="The year cannnot be less than 1950")
	@Max(value = 2025, message="The year cannnot be greater than 2025")
	private int year;
	
	@NotNull(message="Price is required")
	@Min(value = 1, message="The price cannot be less than $1")
	private double price;
	
	@Size(min=1, max=10, message="At least 1 image shoud be uploaded and no more than 10")
	private List<String> images;

	@NotEmpty(message="The pick up location is required")
	private String location;

	@NotEmpty(message="Phone number is required")
	@Pattern(regexp = "^\\(?[0-9]{3}\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}$", 
			message = "Phone number must be in a valid format (e.g., 8005555555, 800 555 5555, or 800-555-5555)")
	private String phone;
	
	@NotEmpty(message="Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", 
			message = "Email must be in a valid format")
	private String email;
	
	private String otherContacts;

	private String createdBy;

	/**
	 * Paramitarized constructor for the product
	 * @param id ID of the product
	 * @param name name of the car
	 * @param year Year of the car
	 * @param description description of the car
	 * @param price Price the car is being sold for
	 * @param phone user's phone
	 * @param image product's image
	 * @param location product's pick up location
	 * @param email user's email
	 * @param otherContacts user's onter contacts 
	 * @param createBy the user that create or post the item
	 */	
	public ProductModel(String name, int year, String description,
			double price,List<String> images, String location, String createdBy, String phone, String email, String otherContacts, ObjectId id) {
		super();
		this.name = name;
		this.year = year;
		this.description = description;
		this.price = price;
		this.images = images;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.otherContacts = otherContacts;
		this.createdBy = createdBy;
		this.id = id;
	}

	/**
	 * Default constructor for the Product description
	 */

	public ProductModel() {
		super();
		
		// this.name = "";
		// this.year = 0;
		// this.description = "";
		// this.price = 0.0;
		// this.image = image;
		// this.createdBy = "";
		// this.phone = "";
		// this.email = "";
		// this.otherContacts = "";
		// this.id = new ObjectId();
		
	}
	
	/**
	 * Simple getter for the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Simple setter for the name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Simple getter for the year
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Simple setter for the year
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Simple getter for the description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Simple setter for the description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Simple getter for the price
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Simple setter for the price
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Simple getter for the images
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}

	/**
	 * Simple setter for the images
	 * @param images the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}

	/**
	 * Simple getter for the pick up location
	 * @return pick up location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Simple setter for the images
	 * @param images the images to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Getter for Id
	 * @return the id
	 */
	public ObjectId getId() {
		return this.id;
	}
	
	/**
	 * A setter for Id
	 * @param newId Id to set
	 */
	public void setId(ObjectId newId) {
		this.id = newId;
	}
	
	/**
	 * Getter for Phone number
	 * @return the phone number
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * A setter for phone
	 * @param phone to phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Getter for Email
	 * @return the Email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * A setter for email
	 * @param email to email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Getter for other Contacts
	 * @return otherContacts
	 */
	public String getOtherContacts() {
		return this.otherContacts;
	}
	
	/**
	 * A setter for other contacts
	 * @param otherContacts to other otherContacts
	 */
	public void setOtherContacts(String otherContacts) {
		this.otherContacts = otherContacts;
	}

	

	/**
	 * Getter for create by
	 * @return createBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}


	/**
	 * A setter for create by
	 * @param createBy to create by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
