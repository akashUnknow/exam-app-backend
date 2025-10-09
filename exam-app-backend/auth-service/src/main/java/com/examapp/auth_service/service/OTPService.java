package com.examapp.auth_service.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examapp.auth_service.model.OTPToken;
import com.examapp.auth_service.repository.OTPRepository;

@Service
public class OTPService {
    
    @Autowired
    private OTPRepository otpRepository;
    
    public void sendOTP(String phoneNumber) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        
        OTPToken otpToken = new OTPToken();
        otpToken.setPhoneNumber(phoneNumber);
        otpToken.setOtp(otp);
        otpToken.setExpiryTime(LocalDateTime.now().plusMinutes(10));
        otpRepository.save(otpToken);
        
        System.out.println("OTP for " + phoneNumber + ": " + otp);
    }
    
    public boolean verifyOTP(String phoneNumber, String otp) {
        Optional<OTPToken> otpToken = otpRepository
            .findByPhoneNumberAndOtpAndIsUsedFalse(phoneNumber, otp);
        
        if (otpToken.isPresent()) {
            OTPToken token = otpToken.get();
            if (token.getExpiryTime().isAfter(LocalDateTime.now())) {
                token.setIsUsed(true);
                otpRepository.save(token);
                return true;
            }
        }
        return false;
    }
}