package com.examapp.test_service.dto;

import java.util.Map;

public class SubmitTestRequest {
    private Long testSessionId;
    private Map<Long, AnswerData> answers; // questionId -> answer data
    private Integer timeTaken;
    
    // Getters and Setters
    public Long getTestSessionId() { return testSessionId; }
    public void setTestSessionId(Long testSessionId) { this.testSessionId = testSessionId; }
    
    public Map<Long, AnswerData> getAnswers() { return answers; }
    public void setAnswers(Map<Long, AnswerData> answers) { this.answers = answers; }
    
    public Integer getTimeTaken() { return timeTaken; }
    public void setTimeTaken(Integer timeTaken) { this.timeTaken = timeTaken; }
    
    // Inner class for answer data
    public static class AnswerData {
        private Integer selectedOption;
        private Integer correctOption;
        
        public Integer getSelectedOption() { return selectedOption; }
        public void setSelectedOption(Integer selectedOption) { this.selectedOption = selectedOption; }
        
        public Integer getCorrectOption() { return correctOption; }
        public void setCorrectOption(Integer correctOption) { this.correctOption = correctOption; }
    }
}