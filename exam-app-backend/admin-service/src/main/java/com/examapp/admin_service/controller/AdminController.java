package com.examapp.admin_service.controller;

import com.examapp.admin_service.dto.QuestionRequest;
import com.examapp.admin_service.model.ExamCategory;
import com.examapp.admin_service.model.Question;
import com.examapp.admin_service.service.AdminService;
import com.examapp.admin_service.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequest request) {
        return ResponseEntity.ok(adminService.createQuestion(request));
    }
    
    @PostMapping("/questions/with-image")
    public ResponseEntity<Question> createQuestionWithImage(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("questionText") String questionText,
            @RequestParam(value = "questionImage", required = false) MultipartFile questionImage,
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("option3") String option3,
            @RequestParam("option4") String option4,
            @RequestParam(value = "option1Image", required = false) MultipartFile option1Image,
            @RequestParam(value = "option2Image", required = false) MultipartFile option2Image,
            @RequestParam(value = "option3Image", required = false) MultipartFile option3Image,
            @RequestParam(value = "option4Image", required = false) MultipartFile option4Image,
            @RequestParam("correctOption") Integer correctOption,
            @RequestParam("difficulty") String difficulty,
            @RequestParam(value = "explanation", required = false) String explanation) {
        
        QuestionRequest request = new QuestionRequest();
        request.setCategoryId(categoryId);
        request.setQuestionText(questionText);
        request.setOption1(option1);
        request.setOption2(option2);
        request.setOption3(option3);
        request.setOption4(option4);
        request.setCorrectOption(correctOption);
        request.setDifficulty(difficulty);
        request.setExplanation(explanation);
        
        if (questionImage != null && !questionImage.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(questionImage);
            request.setQuestionImageUrl(imageUrl);
        }
        
        if (option1Image != null && !option1Image.isEmpty()) {
            request.setOption1ImageUrl(fileStorageService.storeFile(option1Image));
        }
        if (option2Image != null && !option2Image.isEmpty()) {
            request.setOption2ImageUrl(fileStorageService.storeFile(option2Image));
        }
        if (option3Image != null && !option3Image.isEmpty()) {
            request.setOption3ImageUrl(fileStorageService.storeFile(option3Image));
        }
        if (option4Image != null && !option4Image.isEmpty()) {
            request.setOption4ImageUrl(fileStorageService.storeFile(option4Image));
        }
        
        return ResponseEntity.ok(adminService.createQuestion(request));
    }
    
    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Long id, 
            @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(adminService.updateQuestion(id, request));
    }
    
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        adminService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully");
    }
    
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(adminService.getAllQuestions());
    }
    
    @GetMapping("/categories")
    public ResponseEntity<List<ExamCategory>> getAllCategories() {
        return ResponseEntity.ok(adminService.getAllCategories());
    }
    
    @PostMapping("/categories")
    public ResponseEntity<ExamCategory> createCategory(@RequestBody ExamCategory category) {
        return ResponseEntity.ok(adminService.createCategory(category));
    }
}