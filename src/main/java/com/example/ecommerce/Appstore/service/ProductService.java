package com.example.ecommerce.Appstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.ecommerce.Appstore.model.Product;
import com.example.ecommerce.Appstore.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productrepo;

	public List<Product> getAllProducts() {
		return productrepo.findAll();
	}
	public List<Product> saveAll(List<Product> products) {
	    return productrepo.saveAll(products);
	}


	public Product getProductId(Long id) {
	    return productrepo.findById(id).orElse(null);
	}
	
	public Product saveProduct(Product product) {
		return productrepo.save(product);
	}
	@PutMapping("/products/{id}")
	public Product updateProduct(Long id, Product updatedProduct) {
		return  productrepo.findById(id)
		.map(existing -> { 	
		if(existing.getPrice() >=1000)
		{	
		existing.setName(updatedProduct.getName());
		existing.setDescription(updatedProduct.getDescription());
		existing.setCategory(updatedProduct.getCategory());
		existing.setPrice(updatedProduct.getPrice());
		return productrepo.save(existing);
		}
		else
		{
			throw new IllegalArgumentException("price must be greater than 1000");
		}
	})
		.orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
	}

	public void deleteProduct(Long id) {
		productrepo.deleteById(id); // Assuming you are using JPA Repository
	}

	public List<Product> getProductsByCategory(String category) {
		return productrepo.findByCategory(category);
	}
	public List<Product> searchProducts(String keyword){
		return productrepo.findByNameContainingIgnoreCase(keyword);
	}
	public List<Product> getProductsBySubCategory(String subCategory)
	{
		return productrepo.findBySubCategoryIgnoreCase(subCategory);
	}
	
	}

