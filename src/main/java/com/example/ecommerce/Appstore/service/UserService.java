package com.example.ecommerce.Appstore.service;

import java.util.Optional;

import com.example.ecommerce.Appstore.model.User;
import com.example.ecommerce.Appstore.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public boolean processForgotPassword(String email)
	{
		   Optional<User> optionalUser=userRepository.findByEmail(email);
		   
		   if(optionalUser.isPresent())
		   {
			   System.out.println("password reset link sent :"+email);
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	}

}
