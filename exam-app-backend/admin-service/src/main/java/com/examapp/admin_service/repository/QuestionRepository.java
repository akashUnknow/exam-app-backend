package com.examapp.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.examapp.admin_service.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategoryIdAndIsActiveTrue(Long categoryId);
    
    @Query(value = "SELECT * FROM questions WHERE category_id = ?1 AND is_active = true ORDER BY RAND() LIMIT ?2", 
           nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(Long categoryId, Integer limit);
}