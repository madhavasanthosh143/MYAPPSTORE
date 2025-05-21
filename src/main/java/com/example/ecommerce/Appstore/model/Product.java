package com.example.ecommerce.Appstore.model;

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
    private String imagUrl;
	 
	public Product(Long id, String name, String description, double price,String category,String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.imagUrl=imgUrl;
	}
	
	public String getImagUrl() {
		return imagUrl;
	}

	public void setImagUrl(String imagUrl) {
		this.imagUrl = imagUrl;
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
