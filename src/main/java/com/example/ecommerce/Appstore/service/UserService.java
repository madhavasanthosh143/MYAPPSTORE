// UserService.java
package com.example.ecommerce.Appstore.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Appstore.DTO.LoginRequestDTO;
//import com.example.ecommerce.Appstore.DTO.OtpRequest;
import com.example.ecommerce.Appstore.DTO.RegisterRequestDTO;

import com.example.ecommerce.Appstore.model.User;
import com.example.ecommerce.Appstore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String register(RegisterRequestDTO dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		user.setVerified(false);
		userRepository.save(user);
		return "user register succesfully";
	}

	public String login(LoginRequestDTO dto) {
		User user = userRepository.findByUserName(dto.getUserName())
				.orElseThrow(() -> new RuntimeException("User not found"));
		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		String otp = String.format("%06d", new Random().nextInt(999999)); // 6-digit OTP
		user.setOtp(otp);
		user.setVerified(false); // Make sure this method exists in your entity
		userRepository.save(user);

		// 4. Simulate OTP sending (console log here)
		System.out.println("OTP sent to user " + user.getUserName() + ": " + otp);

		// 5. Return response
		return "Login successful. OTP sent to registered email.";

	}

}
