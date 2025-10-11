package com.examapp.test_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examapp.test_service.model.TestSession;

@Repository
public interface TestSessionRepository extends JpaRepository<TestSession, Long> {
    List<TestSession> findByUserIdOrderByStartTimeDesc(Long userId);
    List<TestSession> findByUserIdAndCompletedTrue(Long userId);
}