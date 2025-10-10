package com.examapp.auth_service.repository;

import com.examapp.auth_service.model.OTPToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTPToken, Long> {
    Optional<OTPToken> findByPhoneNumberAndOtpAndIsUsedFalse(String phoneNumber, String otp);
}