package com.example.ecommerce.Appstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Appstore.DTO.OtpVerification;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
	Optional<OtpVerification> findTopByEmailOrderByIdDesc(String email);
}
