package com.example.ecommerce.Appstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce.Appstore.model.Product;
import com.example.ecommerce.Appstore.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	private ProductRepository productrepo;
	
	public List<Product> getAllProducts()
	{
	return	productrepo.findAll();
	}
	
	@GetMapping("/product/{id:\\\\d+}")
	public  Product getProductId(Long id)
	{
	return	productrepo.findById(id).orElse(null);
	}
	
	public Product saveProduct(Product product)
	{
		return	productrepo.save(product);
	}
	
	public Product updateProduct(Long id, Product updatedProduct) {
	    Product existing = productrepo.findById(id).orElseThrow();
	    existing.setName(updatedProduct.getName());
	    existing.setDescription(updatedProduct.getDescription());
	    existing.setCategory(updatedProduct.getCategory());
	    existing.setPrice(updatedProduct.getPrice());
	    return  productrepo.save(existing);
	}

	
	public void deleteProduct(Long id) {
	    productrepo.deleteById(id); // Assuming you are using JPA Repository
	}

	 public List<Product> getProductsByCategory(String category) {
	        return productrepo.findByCategory(category);
}
}
