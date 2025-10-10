package com.examapp.auth_service.controller;

import com.examapp.auth_service.dto.*;
import com.examapp.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestBody OTPRequest request) {
        authService.sendOTP(request.getPhoneNumber());
        return ResponseEntity.ok("OTP sent successfully");
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOTP(@RequestBody OTPRequest request) {
        return ResponseEntity.ok(authService.verifyOTP(request.getPhoneNumber(), request.getOtp()));
    }
}