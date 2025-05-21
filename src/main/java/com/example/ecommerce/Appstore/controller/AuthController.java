package com.example.ecommerce.Appstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ecommerce.Appstore.model.User;
import com.example.ecommerce.Appstore.repository.UserRepository;
import com.example.ecommerce.Appstore.service.UserService;
@Controller
public class AuthController {
	@Autowired
	private UserRepository userRepo;
	
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody User user) {
	    return userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword())
	    		
	            .map(u -> ResponseEntity.ok().header("Location", "/product-list.html").build()) // Redirects to products page
	          // ResponseEntity.ok().header("Location", "/product-view.html").build();

	            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
	}


    @PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user)
	{
    	String username =user.getUserName();
    	String passWord =user.getPassword();
    	if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required.");
        }

        if (username.length() < 1 || username.length() > 20  ) {
            return ResponseEntity.badRequest().body("Username must be between 1 and 20 characters.");
        }

        if (passWord == null || passWord.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required.");
        }

        if (passWord.length() < 7 || passWord.length() > 20) {
            return ResponseEntity.badRequest().body("Password must be between 7 and 20 characters.");
        }
    		
		userRepo.save(user);
		return ResponseEntity.ok("user successfully registered");	
    	}
    	 
	

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        boolean result = userService.processForgotPassword(email);

        if (result) {
            return ResponseEntity.ok("Reset instructions sent to your email.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

}
