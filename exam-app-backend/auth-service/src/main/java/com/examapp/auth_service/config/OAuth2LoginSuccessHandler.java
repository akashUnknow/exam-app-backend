package com.examapp.auth_service.config;

import com.examapp.auth_service.model.User;
import com.examapp.auth_service.repository.UserRepository;
import com.examapp.auth_service.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user information from Google
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String googleId = oAuth2User.getAttribute("sub");
        Boolean emailVerified = oAuth2User.getAttribute("email_verified");

        // Check if email is verified by Google
        if (emailVerified == null || !emailVerified) {
            String errorUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/login")
                    .queryParam("error", "email_not_verified")
                    .queryParam("message", "Email not verified by Google")
                    .build().toUriString();
            getRedirectStrategy().sendRedirect(request, response, errorUrl);
            return;
        }

        // Check if user exists in database
        Optional<User> existingUser = userRepository.findByEmail(email);

        User user;
        String message;
        boolean isNewUser = false;

        if (existingUser.isPresent()) {
            // User exists - LOGIN
            user = existingUser.get();

            // Update Google ID if not set
            if (user.getGoogleId() == null || user.getGoogleId().isEmpty()) {
                user.setGoogleId(googleId);
                user.setIsVerified(true);
                user = userRepository.save(user);
            }

            message = "login_successful";
            System.out.println("✓ LOGIN SUCCESSFUL - User: " + email);

        } else {
            // New user - REGISTRATION
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setGoogleId(googleId);
            user.setIsVerified(true);
            user.setRole("USER");
            user = userRepository.save(user);

            isNewUser = true;
            message = "registration_successful";
            System.out.println("✓ REGISTRATION SUCCESSFUL - New user created: " + email);
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        // Redirect to frontend with token and appropriate message
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/oauth/callback")
                .queryParam("token", token)
                .queryParam("userId", user.getId())
                .queryParam("name", user.getName())
                .queryParam("email", user.getEmail())
                .queryParam("role", user.getRole())
                .queryParam("message", message)
                .queryParam("isNewUser", isNewUser)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}