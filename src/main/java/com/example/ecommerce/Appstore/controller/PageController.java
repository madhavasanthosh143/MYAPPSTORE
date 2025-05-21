package com.example.ecommerce.Appstore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

	@Controller
	public class PageController {

	    @GetMapping("/login-page")
	    public String loginPage() {
	        return "login.html";
	    }

	    @GetMapping("/register-page")
	    public String registerPage() {
	        return "register.html";
	    }
	    
	        @GetMapping("/products-page")
	        public String showProducts() {
	            return "product.html"; // products.html
	        }
   
	    }
	

