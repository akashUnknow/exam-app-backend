package com.examapp.test_service.dto;

public class StartTestRequest {
    private Long userId;
    private Long categoryId;
    private String mode;
    private Integer questionCount = 10;
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    
    public Integer getQuestionCount() { return questionCount; }
    public void setQuestionCount(Integer questionCount) { this.questionCount = questionCount; }
}