package com.examapp.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examapp.user_service.model.UserProfile;
import com.examapp.user_service.repository.UserProfileRepository;

@Service
public class UserService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public UserProfile getUserProfile(Long userId) {
        return userProfileRepository.findById(userId)
            .orElseGet(() -> createDefaultProfile(userId));
    }
    
    private UserProfile createDefaultProfile(Long userId) {
        UserProfile profile = new UserProfile();
        profile.setUserId(userId);
        profile.setName("User " + userId);
        profile.setTotalScore(0);
        profile.setTestsCompleted(0);
        return userProfileRepository.save(profile);
    }
    
    public UserProfile updateUserProfile(Long userId, UserProfile updatedProfile) {
        UserProfile profile = getUserProfile(userId);
        
        if (updatedProfile.getName() != null) {
            profile.setName(updatedProfile.getName());
        }
        if (updatedProfile.getEmail() != null) {
            profile.setEmail(updatedProfile.getEmail());
        }
        if (updatedProfile.getPhoneNumber() != null) {
            profile.setPhoneNumber(updatedProfile.getPhoneNumber());
        }
        if (updatedProfile.getProfileImageUrl() != null) {
            profile.setProfileImageUrl(updatedProfile.getProfileImageUrl());
        }
        
        return userProfileRepository.save(profile);
    }
    
    public List<UserProfile> getUserFriends(Long userId) {
        UserProfile profile = getUserProfile(userId);
        return profile.getFriends();
    }
    
    public void addFriend(Long userId, Long friendId) {
        UserProfile user = getUserProfile(userId);
        UserProfile friend = getUserProfile(friendId);
        
        if (!user.getFriends().contains(friend)) {
            user.getFriends().add(friend);
            userProfileRepository.save(user);
        }
        
        if (!friend.getFriends().contains(user)) {
            friend.getFriends().add(user);
            userProfileRepository.save(friend);
        }
    }
    
    public void removeFriend(Long userId, Long friendId) {
        UserProfile user = getUserProfile(userId);
        UserProfile friend = getUserProfile(friendId);
        
        user.getFriends().remove(friend);
        friend.getFriends().remove(user);
        
        userProfileRepository.save(user);
        userProfileRepository.save(friend);
    }
    
    public void updateUserStats(Long userId, Integer score) {
        UserProfile profile = getUserProfile(userId);
        profile.setTotalScore(profile.getTotalScore() + score);
        profile.setTestsCompleted(profile.getTestsCompleted() + 1);
        userProfileRepository.save(profile);
        
        // Update rankings
        updateRankings();
    }
    
    private void updateRankings() {
        List<UserProfile> allUsers = userProfileRepository.findAllByOrderByTotalScoreDesc();
        int rank = 1;
        for (UserProfile user : allUsers) {
            user.setRank(rank++);
            userProfileRepository.save(user);
        }
    }
    
    public List<UserProfile> getLeaderboard() {
        return userProfileRepository.findAllByOrderByTotalScoreDesc();
    }
    
    public List<UserProfile> searchUsers(List<Long> userIds) {
        return userProfileRepository.findByUserIdIn(userIds);
    }
}