package com.examapp.exam_service.controller;

import com.examapp.exam_service.model.ExamCategory;
import com.examapp.exam_service.model.Question;
import com.examapp.exam_service.repository.ExamCategoryRepository;
import com.examapp.exam_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamController {
    
    @Autowired
    private ExamCategoryRepository categoryRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @GetMapping("/categories")
    public ResponseEntity<List<ExamCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
    
    @GetMapping("/categories/{id}")
    public ResponseEntity<ExamCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found")));
    }
    
    @GetMapping("/categories/{id}/questions")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(questionRepository.findByCategoryIdAndIsActiveTrue(id));
    }
    
    @GetMapping("/questions/random/{categoryId}/{count}")
    public ResponseEntity<List<Question>> getRandomQuestions(
            @PathVariable Long categoryId, 
            @PathVariable Integer count) {
        List<Question> questions = questionRepository
            .findRandomQuestionsByCategory(categoryId, count);
        return ResponseEntity.ok(questions);
    }
}