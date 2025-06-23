package com.example.ecommerce.Appstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Appstore.model.Product;
import com.example.ecommerce.Appstore.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private ProductService productservice;

	public ProductController(ProductService productservice) {
		super();
		this.productservice = productservice;
	}

	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody List<Product> product) {
	    product.forEach(p -> System.out.println("Received Product: " + p.getName()));
		productservice.saveAll(product);
		return ResponseEntity.ok("Product added successfully");
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productservice.getAllProducts(); // Or similar logic
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		if (id != null) {
			Product updated = productservice.updateProduct(id, product);

			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productservice.deleteProduct(id);
		return ResponseEntity.ok("Product deleted successfully");
	}

	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
		List<Product> products = productservice.searchProducts(keyword);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/category/{category}")
	public List<Product> getByCategory(@PathVariable String category) {
		return productservice.getProductsByCategory(category);
	}

	@GetMapping("/subcategory")
	public List<Product> getBySubCategory(@RequestParam String name) {
		return productservice.getProductsBySubCategory(name);
	}

}
