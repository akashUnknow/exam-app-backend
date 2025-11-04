package com.examapp.auth_service.controller;

import com.examapp.auth_service.dto.AuthResponse;
import com.examapp.auth_service.service.GoogleOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/google")
@CrossOrigin(origins = "*")
public class GoogleOAuthController {

    @Autowired
    private GoogleOAuthService googleOAuthService;

    /**
     * Endpoint to handle Google OAuth login
     * Frontend should send the Google ID token received after user authentication
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> googleLogin(@RequestBody Map<String, String> request) {
        String idToken = request.get("idToken");

        if (idToken == null || idToken.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        AuthResponse response = googleOAuthService.authenticateWithGoogle(idToken);
        return ResponseEntity.ok(response);
    }

    /**
     * Optional: Get Google OAuth URL for frontend
     */
    @GetMapping("/auth-url")
    public ResponseEntity<Map<String, String>> getGoogleAuthUrl() {
        String authUrl = googleOAuthService.getGoogleAuthUrl();
        return ResponseEntity.ok(Map.of("authUrl", authUrl));
    }
}