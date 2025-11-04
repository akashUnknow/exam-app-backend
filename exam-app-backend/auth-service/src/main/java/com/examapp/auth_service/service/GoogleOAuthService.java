package com.examapp.auth_service.service;

import com.examapp.auth_service.dto.AuthResponse;
import com.examapp.auth_service.model.User;
import com.examapp.auth_service.repository.UserRepository;
import com.examapp.auth_service.util.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class GoogleOAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    /**
     * Authenticate user with Google ID Token
     */
    public AuthResponse authenticateWithGoogle(String idToken) {
        try {
            // Verify the Google ID token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken googleIdToken = verifier.verify(idToken);

            if (googleIdToken == null) {
                throw new RuntimeException("Invalid Google ID token");
            }

            GoogleIdToken.Payload payload = googleIdToken.getPayload();

            String googleId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            Boolean emailVerified = payload.getEmailVerified();

            if (!emailVerified) {
                throw new RuntimeException("Email not verified by Google");
            }

            // Check if user exists
            Optional<User> existingUser = userRepository.findByGoogleId(googleId);

            User user;
            boolean isNewUser = false;

            if (existingUser.isPresent()) {
                // User exists - login
                user = existingUser.get();
                System.out.println("Existing user logging in: " + email);
            } else {
                // Check if email already exists with different auth method
                Optional<User> emailUser = userRepository.findByEmail(email);

                if (emailUser.isPresent()) {
                    // Link Google account to existing user
                    user = emailUser.get();
                    user.setGoogleId(googleId);
                    user.setIsVerified(true);
                    user = userRepository.save(user);
                    System.out.println("Linked Google account to existing user: " + email);
                } else {
                    // New user - register
                    user = new User();
                    user.setGoogleId(googleId);
                    user.setEmail(email);
                    user.setName(name != null ? name : "User");
                    user.setIsVerified(true);
                    user.setRole("USER");
                    user = userRepository.save(user);
                    isNewUser = true;
                    System.out.println("New user registered via Google: " + email);
                }
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

            // Log login message
            String loginMessage = isNewUser
                    ? "New account created and logged in with email: " + user.getEmail()
                    : "You are logged in with email: " + user.getEmail();

            System.out.println(loginMessage);

            return new AuthResponse(
                    token,
                    user.getId().toString(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );

        } catch (Exception e) {
            throw new RuntimeException("Google authentication failed: " + e.getMessage());
        }
    }

    /**
     * Get Google OAuth authorization URL
     */
    public String getGoogleAuthUrl() {
        String scope = "email profile";
        return String.format(
                "https://accounts.google.com/o/oauth2/v2/auth?" +
                        "client_id=%s&" +
                        "redirect_uri=%s&" +
                        "response_type=code&" +
                        "scope=%s",
                googleClientId,
                redirectUri,
                scope
        );
    }
}