package com.examapp.auth_service.service;

import com.examapp.auth_service.model.OTPToken;
import com.examapp.auth_service.repository.OTPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPService {
    private final JavaMailSender mailSender;
    private final OTPRepository otpRepository;

    public void sendOTP(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));

        OTPToken otpToken = OTPToken.builder()
                .email(email)
                .otp(otp)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .isUsed(false)
                .build();
        otpRepository.save(otpToken);
        System.out.println("OTP for " + email + ": " + otp);

        // Send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp + " (valid for 5 minutes)");
        mailSender.send(message);
    }

    public boolean verifyOTP(String email, String otp) {
        Optional<OTPToken> otpToken = otpRepository
            .findByEmailAndOtp(email, otp);

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