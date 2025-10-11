package com.examapp.user_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private Long userId; // Same as auth service user ID
    
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    
    private Integer totalScore = 0;
    private Integer testsCompleted = 0;
    private Integer rank;
    
    @ManyToMany
    @JoinTable(
        name = "user_friends",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<UserProfile> friends;
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    
    public Integer getTestsCompleted() { return testsCompleted; }
    public void setTestsCompleted(Integer testsCompleted) { this.testsCompleted = testsCompleted; }
    
    public Integer getRank() { return rank; }
    public void setRank(Integer rank) { this.rank = rank; }
    
    public List<UserProfile> getFriends() { return friends; }
    public void setFriends(List<UserProfile> friends) { this.friends = friends; }
}