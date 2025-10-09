package com.examapp.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examapp.auth_service.model.OTPToken;

@Repository
public interface OTPRepository extends JpaRepository<OTPToken, Long> {
    Optional<OTPToken> findByPhoneNumberAndOtpAndIsUsedFalse(String phoneNumber, String otp);
}