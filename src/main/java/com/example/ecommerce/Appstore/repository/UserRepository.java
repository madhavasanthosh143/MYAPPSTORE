package com.example.ecommerce.Appstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.Appstore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserNameAndPassword(String userName, String password);

	Optional<User> findByUserName(String userName);

	List<User> findByEmail(String email);

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

}
