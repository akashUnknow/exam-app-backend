package com.examapp.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examapp.user_service.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    
    @Query("SELECT u FROM UserProfile u ORDER BY u.totalScore DESC")
    List<UserProfile> findAllByOrderByTotalScoreDesc();
    
    @Query("SELECT u FROM UserProfile u WHERE u.userId IN :userIds")
    List<UserProfile> findByUserIdIn(List<Long> userIds);
}