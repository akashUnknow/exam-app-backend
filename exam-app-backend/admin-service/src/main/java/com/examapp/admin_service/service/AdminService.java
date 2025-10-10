package com.examapp.admin_service.service;

import com.examapp.admin_service.dto.QuestionRequest;
import com.examapp.admin_service.model.*;
import com.examapp.admin_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private ExamCategoryRepository categoryRepository;
    
    public Question createQuestion(QuestionRequest request) {
        ExamCategory category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        
        Question question = new Question();
        question.setCategory(category);
        question.setQuestionText(request.getQuestionText());
        question.setQuestionImageUrl(request.getQuestionImageUrl());
        question.setOption1(request.getOption1());
        question.setOption2(request.getOption2());
        question.setOption3(request.getOption3());
        question.setOption4(request.getOption4());
        question.setOption1ImageUrl(request.getOption1ImageUrl());
        question.setOption2ImageUrl(request.getOption2ImageUrl());
        question.setOption3ImageUrl(request.getOption3ImageUrl());
        question.setOption4ImageUrl(request.getOption4ImageUrl());
        question.setCorrectOption(request.getCorrectOption());
        question.setDifficulty(request.getDifficulty());
        question.setExplanation(request.getExplanation());
        question.setIsActive(true);
        
        return questionRepository.save(question);
    }
    
    public Question updateQuestion(Long id, QuestionRequest request) {
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        
        question.setQuestionText(request.getQuestionText());
        question.setQuestionImageUrl(request.getQuestionImageUrl());
        question.setOption1(request.getOption1());
        question.setOption2(request.getOption2());
        question.setOption3(request.getOption3());
        question.setOption4(request.getOption4());
        question.setCorrectOption(request.getCorrectOption());
        question.setDifficulty(request.getDifficulty());
        question.setExplanation(request.getExplanation());
        
        return questionRepository.save(question);
    }
    
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        question.setIsActive(false);
        questionRepository.save(question);
    }
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    public List<ExamCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public ExamCategory createCategory(ExamCategory category) {
        return categoryRepository.save(category);
    }
}