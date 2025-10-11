package com.examapp.test_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long challengerId;
    private Long challengedId;
    private Long categoryId;
    
    private String status; // PENDING, ACCEPTED, COMPLETED
    
    private LocalDateTime createdAt;
    
    private Long challengerSessionId;
    private Long challengedSessionId;
    
    private Long winnerId;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = "PENDING";
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getChallengerId() { return challengerId; }
    public void setChallengerId(Long challengerId) { this.challengerId = challengerId; }
    
    public Long getChallengedId() { return challengedId; }
    public void setChallengedId(Long challengedId) { this.challengedId = challengedId; }
    
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public Long getChallengerSessionId() { return challengerSessionId; }
    public void setChallengerSessionId(Long challengerSessionId) { this.challengerSessionId = challengerSessionId; }
    
    public Long getChallengedSessionId() { return challengedSessionId; }
    public void setChallengedSessionId(Long challengedSessionId) { this.challengedSessionId = challengedSessionId; }
    
    public Long getWinnerId() { return winnerId; }
    public void setWinnerId(Long winnerId) { this.winnerId = winnerId; }
}