package com.examapp.auth_service.dto;

public class AuthResponse {
    private String token;
    private String userId;
    private String name;
    private String email;
    private String role;
    private String message;  // Added for OAuth feedback
    private Boolean isNewUser;  // Added to distinguish login vs registration

    // Constructor without message (for backward compatibility)
    public AuthResponse(String token, String userId, String name, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Constructor with message and isNewUser
    public AuthResponse(String token, String userId, String name, String email, String role,
                        String message, Boolean isNewUser) {
        this.token = token;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.message = message;
        this.isNewUser = isNewUser;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getIsNewUser() { return isNewUser; }
    public void setIsNewUser(Boolean isNewUser) { this.isNewUser = isNewUser; }
}