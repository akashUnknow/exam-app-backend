package com.examapp.auth_service.service;

import com.examapp.auth_service.model.User;
import com.examapp.auth_service.repository.UserRepository;
import com.examapp.auth_service.dto.*;
import com.examapp.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private OTPService otpService;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsVerified(false);
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getId().toString(), user.getName(), 
                              user.getEmail(), user.getRole());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getId().toString(), user.getName(), 
                              user.getEmail(), user.getRole());
    }
    
    public void sendOTP(String email) {
        otpService.sendOTP(email);
    }

    //verify OTP
    public AuthResponse verifyOTP(String email, String otp) {
        if (!otpService.verifyOTP(email, otp)) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        User user = userRepository.findByPhoneNumber(email)
            .orElseGet(() -> {
                User newUser = new User();
                newUser.setPhoneNumber(email);
                newUser.setIsVerified(true);
                newUser.setName("User");
                return userRepository.save(newUser);
            });

        user.setIsVerified(true);
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getId().toString(), user.getName(),
                              user.getEmail() != null ? user.getEmail() : "", user.getRole());
    }
}