package com.examapp.test_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "test_sessions")
public class TestSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private Long categoryId;
    
    private String mode; // SOLO, CHALLENGE
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private Integer score;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
    private Integer unansweredQuestions;
    
    private Integer timeTaken; // in seconds
    
    private Boolean completed = false;
    
    @OneToMany(mappedBy = "testSession", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers;
    
    @PrePersist
    protected void onCreate() {
        startTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    
    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }
    
    public Integer getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(Integer correctAnswers) { this.correctAnswers = correctAnswers; }
    
    public Integer getIncorrectAnswers() { return incorrectAnswers; }
    public void setIncorrectAnswers(Integer incorrectAnswers) { this.incorrectAnswers = incorrectAnswers; }
    
    public Integer getUnansweredQuestions() { return unansweredQuestions; }
    public void setUnansweredQuestions(Integer unansweredQuestions) { this.unansweredQuestions = unansweredQuestions; }
    
    public Integer getTimeTaken() { return timeTaken; }
    public void setTimeTaken(Integer timeTaken) { this.timeTaken = timeTaken; }
    
    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
    
    public List<UserAnswer> getUserAnswers() { return userAnswers; }
    public void setUserAnswers(List<UserAnswer> userAnswers) { this.userAnswers = userAnswers; }
}