package com.example.ecommerce.Appstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Appstore.model.Product;
import com.example.ecommerce.Appstore.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
	private ProductService productservice;
       
    @GetMapping
    public List<Product> listAll()
    {
    	return productservice.getAllProducts();
    }
	@GetMapping("/{id}")
    public Product get(@PathVariable Long id)
    {
    	return productservice.getProductId(id);
    }
    @PostMapping
    public Product create(@RequestBody Product product)
    {
    	return productservice.saveProduct(product);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    	if(id!= null)
    	{
        Product updated = productservice.updateProduct(id, product);
        
        return ResponseEntity.ok(updated);
    	}
    	else
    	{
    		 return ResponseEntity.badRequest().build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productservice.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productservice.getProductsByCategory(category);
    }

	
}
