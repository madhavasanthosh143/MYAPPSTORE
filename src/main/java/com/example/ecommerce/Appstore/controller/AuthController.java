package com.example.ecommerce.Appstore.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Appstore.DTO.OtpVerification;
import com.example.ecommerce.Appstore.DTO.OtpVerifyDTO;
import com.example.ecommerce.Appstore.DTO.ResetPasswordDTO;
import com.example.ecommerce.Appstore.model.User;
import com.example.ecommerce.Appstore.repository.OtpVerificationRepository;
import com.example.ecommerce.Appstore.repository.UserRepository;
import com.example.ecommerce.Appstore.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
	private UserRepository userRepo;
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OtpVerificationRepository otpRepo;

	private Map<String, Integer> otpStore = new ConcurrentHashMap<>();

	public AuthController(UserRepository userRepo, UserService userService) {
		this.userRepo = userRepo;
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		String username = user.getUserName();
		String passWord = user.getPassword();
		String email = user.getEmail();
		if (username == null || username.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Username is required.");
		}

		if (username.length() < 1 || username.length() > 20) {
			return ResponseEntity.badRequest().body("Username must be between 1 and 20 characters.");
		}

		if (passWord == null || passWord.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Password is required.");
		}

		if (passWord.length() < 7 || passWord.length() > 20) {
			return ResponseEntity.badRequest().body("Password must be between 7 and 20 characters.");
		}
		if (email == null || email.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Email is required.");
		}

		if (userRepo.existsByUserName(username)) {
			return ResponseEntity.badRequest().body("Username already exists.");
		}

		if (userRepo.existsByEmail(email)) {
			return ResponseEntity.badRequest().body("Email already registered.");
		}

		// Password encoding
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Generate OTP (6-digit)
		String otp = String.format("%06d", new Random().nextInt(999999));
		System.out.println("OTP for " + username + ": " + otp);

		// user.setPassword(passwordEncoder.encode(passWord));
		user.setOtp(otp);
		userRepo.save(user);

		return ResponseEntity.ok("Registered successfully. OTP sent to email (check console for now).");
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		// 1. Find user by username only (NOT password)
		Optional<User> optionalUser = userRepo.findByUserName(user.getUserName());

		// 2. If user not found
		if (optionalUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		}

		User dbUser = optionalUser.get();

		// 3. Match encoded password
		if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
		}

		// 4. Generate OTP if needed
		String otp = String.format("%06d", new Random().nextInt(999999));
		dbUser.setOtp(otp);
		dbUser.setVerified(false);
		userRepo.save(dbUser);
		System.out.println("OTP for " + dbUser.getUserName() + ": " + otp);

		// 5. Redirect to product-list.html
		return ResponseEntity.status(HttpStatus.FOUND) // 302 Redirect
				.header("Location", "/product-list.html").body("Login successful. OTP sent.");
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtp(@RequestBody OtpVerifyDTO dto) {
	    return userRepo.findByUserName(dto.getUserName())
	            .map(user -> {
	                if (user.getOtp() == null) {
	                    return ResponseEntity.badRequest().body("OTP not generated or already used. Please login again.");
	                }

	                if (!user.getOtp().equals(dto.getOtp())) {
	                    return ResponseEntity.badRequest().body("Invalid OTP");
	                }

	                user.setVerified(true);
	                user.setOtp(null); // Clear OTP after successful verification
	                userRepo.save(user);
	                return ResponseEntity.ok("OTP verified successfully.");
	            })
	            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO dto) {
	    Optional<User> optionalUser = userRepo.findByUserName(dto.getUserName());
	    if (optionalUser.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }

	    User user = optionalUser.get();
	    user.setPassword(passwordEncoder.encode(dto.getNewPassword())); // encode before saving
	    userRepo.save(user);

	    return ResponseEntity.ok("Password updated successfully");
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
	    String email = payload.get("email");

	    List<User> existingUsers = userRepo.findByEmail(email);
	    if (existingUsers.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this email.");
	    }

	    int otp = new Random().nextInt(900000) + 100000;
	    System.out.println("OTP for " + email + ": " + otp);

	    // save OTP logic here...

	    return ResponseEntity.ok("OTP sent to your email.");
	}


}
