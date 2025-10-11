package com.examapp.test_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examapp.test_service.model.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByTestSessionId(Long testSessionId);
}