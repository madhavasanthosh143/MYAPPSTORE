package com.example.ecommerce.Appstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.ecommerce.Appstore.model.User;
import com.example.ecommerce.Appstore.repository.ProductRepository;
import com.example.ecommerce.Appstore.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private ProductService productservice;

	private ProductRepository productRepo;

	public ProductController(ProductService productservice, ProductRepository productRepo) {
		super();
		this.productservice = productservice;
		this.productRepo = productRepo;
	}

	@PostMapping("/upload-multiple")
	public ResponseEntity<String> uploadMultipleProducts(@RequestBody List<Product> products, HttpServletRequest request) {
	    User user = (User) request.getSession().getAttribute("user");

	    if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only logged-in admins can upload products.");
	    }

	    productRepo.saveAll(products); // Save all products at once
	    return ResponseEntity.ok("✅ Products uploaded successfully!");
	}


	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody List<Product> productList, HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");

		productList.forEach(p -> {
			System.out.println("Received Product: " + p.getName());
			p.setUserName(user.getUserName());
		});

		if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can add products.");
		}

		productservice.saveAll(productList);
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

	@GetMapping("/cheap")
	public List<Product> getPriceRange(@RequestParam double min, @RequestParam double max) {
		return productservice.getProductPriceRange(min, max);
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
