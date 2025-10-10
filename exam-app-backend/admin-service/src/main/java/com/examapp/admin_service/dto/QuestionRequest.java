package com.examapp.admin_service.dto;

public class QuestionRequest {
    private Long categoryId;
    private String questionText;
    private String questionImageUrl;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option1ImageUrl;
    private String option2ImageUrl;
    private String option3ImageUrl;
    private String option4ImageUrl;
    private Integer correctOption;
    private String difficulty;
    private String explanation;
    
    // Getters and Setters
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    
    public String getQuestionImageUrl() { return questionImageUrl; }
    public void setQuestionImageUrl(String questionImageUrl) { this.questionImageUrl = questionImageUrl; }
    
    public String getOption1() { return option1; }
    public void setOption1(String option1) { this.option1 = option1; }
    
    public String getOption2() { return option2; }
    public void setOption2(String option2) { this.option2 = option2; }
    
    public String getOption3() { return option3; }
    public void setOption3(String option3) { this.option3 = option3; }
    
    public String getOption4() { return option4; }
    public void setOption4(String option4) { this.option4 = option4; }
    
    public String getOption1ImageUrl() { return option1ImageUrl; }
    public void setOption1ImageUrl(String option1ImageUrl) { this.option1ImageUrl = option1ImageUrl; }
    
    public String getOption2ImageUrl() { return option2ImageUrl; }
    public void setOption2ImageUrl(String option2ImageUrl) { this.option2ImageUrl = option2ImageUrl; }
    
    public String getOption3ImageUrl() { return option3ImageUrl; }
    public void setOption3ImageUrl(String option3ImageUrl) { this.option3ImageUrl = option3ImageUrl; }
    
    public String getOption4ImageUrl() { return option4ImageUrl; }
    public void setOption4ImageUrl(String option4ImageUrl) { this.option4ImageUrl = option4ImageUrl; }
    
    public Integer getCorrectOption() { return correctOption; }
    public void setCorrectOption(Integer correctOption) { this.correctOption = correctOption; }
    
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    
    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}