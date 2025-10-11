package com.examapp.test_service.dto;

public class TestResult {
    private Long testSessionId;
    private Integer score;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
    private Integer unansweredQuestions;
    private Integer totalQuestions;
    private Integer timeTaken;
    
    // Getters and Setters
    public Long getTestSessionId() { return testSessionId; }
    public void setTestSessionId(Long testSessionId) { this.testSessionId = testSessionId; }
    
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    
    public Integer getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(Integer correctAnswers) { this.correctAnswers = correctAnswers; }
    
    public Integer getIncorrectAnswers() { return incorrectAnswers; }
    public void setIncorrectAnswers(Integer incorrectAnswers) { this.incorrectAnswers = incorrectAnswers; }
    
    public Integer getUnansweredQuestions() { return unansweredQuestions; }
    public void setUnansweredQuestions(Integer unansweredQuestions) { this.unansweredQuestions = unansweredQuestions; }
    
    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }
    
    public Integer getTimeTaken() { return timeTaken; }
    public void setTimeTaken(Integer timeTaken) { this.timeTaken = timeTaken; }
}