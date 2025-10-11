package com.examapp.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examapp.user_service.model.UserProfile;
import com.examapp.user_service.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserProfile> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile profile) {
        return ResponseEntity.ok(userService.updateUserProfile(userId, profile));
    }
    
    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<UserProfile>> getUserFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserFriends(userId));
    }
    
    @PostMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<String> addFriend(
            @PathVariable Long userId,
            @PathVariable Long friendId) {
        userService.addFriend(userId, friendId);
        return ResponseEntity.ok("Friend added successfully");
    }
    
    @DeleteMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<String> removeFriend(
            @PathVariable Long userId,
            @PathVariable Long friendId) {
        userService.removeFriend(userId, friendId);
        return ResponseEntity.ok("Friend removed successfully");
    }
    
    @PostMapping("/{userId}/stats")
    public ResponseEntity<String> updateUserStats(
            @PathVariable Long userId,
            @RequestParam Integer score) {
        userService.updateUserStats(userId, score);
        return ResponseEntity.ok("Stats updated successfully");
    }
    
    @GetMapping("/leaderboard")
    public ResponseEntity<List<UserProfile>> getLeaderboard() {
        return ResponseEntity.ok(userService.getLeaderboard());
    }
    
    @PostMapping("/search")
    public ResponseEntity<List<UserProfile>> searchUsers(@RequestBody List<Long> userIds) {
        return ResponseEntity.ok(userService.searchUsers(userIds));
    }
}