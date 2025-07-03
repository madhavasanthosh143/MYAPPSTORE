package com.example.ecommerce.Appstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
	private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String subCategory;
    private String imageUrl;
    @Column(name = "user_name")
    private String userName;
	 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Product(Long id, String name, String description, double price,String category,String imageUrl,String subCategory,String userName) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.subCategory=subCategory;
		this.imageUrl=imageUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public void setImagUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Product() {
		super();
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
    
}
