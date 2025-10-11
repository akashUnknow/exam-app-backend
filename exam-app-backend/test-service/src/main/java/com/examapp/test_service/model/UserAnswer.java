package com.examapp.test_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_answers")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "test_session_id")
    private TestSession testSession;
    
    private Long questionId;
    private Integer selectedOption;
    private Integer correctOption;
    private Boolean isCorrect;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public TestSession getTestSession() { return testSession; }
    public void setTestSession(TestSession testSession) { this.testSession = testSession; }
    
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
    
    public Integer getSelectedOption() { return selectedOption; }
    public void setSelectedOption(Integer selectedOption) { this.selectedOption = selectedOption; }
    
    public Integer getCorrectOption() { return correctOption; }
    public void setCorrectOption(Integer correctOption) { this.correctOption = correctOption; }
    
    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
}