package com.gcu.data.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Instance of a product from the database
 */
@Document(collection="products")
public class ProductEntity {
	
	@Id	
	private ObjectId id;
	private String name;
	private String description;
	private int year;
	private float price;
	private String image;
	private String createdBy;
	private String phone;
	private String email;
	private String otherContacts;
	
	/**
	 * Non-Default constructor for the ProductEntity
	 * @param id ID of the product in the database
	 * @param make name of the item
	 * @param model description of the item
	 * @param year Year of the item
	 * @param price Price of the item
	 */
	public ProductEntity(ObjectId id, String name, String description, int year, float price, 
			String image, String createdBy, String phone, String email, String otherContacts) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.year = year;
		this.price = price;
		this.image = image;
		this.createdBy = createdBy;
		this.phone = phone;
		this.email = email;
		this.otherContacts = otherContacts;
	}

	/**
	 * Default constructor for ProductEntity
	 */
	public ProductEntity() {
		super();
		/*
		this.id= new ObjectId();
		this.name = "";
		this.description = "";
		this.year = 0;
		this.price = 0;
		this.image = "";
		this.createdBy = "";
		*/
	}

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param make the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param model the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreateBy(String createBy) {
		this.createdBy = createBy;
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
	 * Getter for other connection
	 * @return email
	 */
	public String getOtherContacts() {
		return this.otherContacts;
	}
	
	/**
	 * A setter for other connection
	 * @param otherConnection to other connection
	 */
	public void setOtherContacts(String otherContacts) {
		this.otherContacts = otherContacts;
	}
	
	
}
