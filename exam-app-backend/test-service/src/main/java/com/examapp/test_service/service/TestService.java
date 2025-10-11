package com.examapp.test_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examapp.test_service.dto.StartTestRequest;
import com.examapp.test_service.dto.SubmitTestRequest;
import com.examapp.test_service.dto.TestResult;
import com.examapp.test_service.model.Challenge;
import com.examapp.test_service.model.TestSession;
import com.examapp.test_service.model.UserAnswer;
import com.examapp.test_service.repository.ChallengeRepository;
import com.examapp.test_service.repository.TestSessionRepository;
import com.examapp.test_service.repository.UserAnswerRepository;

@Service
public class TestService {
    
    @Autowired
    private TestSessionRepository testSessionRepository;
    
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    
    @Autowired
    private ChallengeRepository challengeRepository;
    
    public TestSession startTest(StartTestRequest request) {
        TestSession session = new TestSession();
        session.setUserId(request.getUserId());
        session.setCategoryId(request.getCategoryId());
        session.setMode(request.getMode());
        session.setTotalQuestions(request.getQuestionCount());
        
        return testSessionRepository.save(session);
    }
    
    public TestResult submitTest(SubmitTestRequest request) {
        TestSession session = testSessionRepository.findById(request.getTestSessionId())
            .orElseThrow(() -> new RuntimeException("Test session not found"));
        
        int correctCount = 0;
        int incorrectCount = 0;
        int unansweredCount = 0;
        
        for (Map.Entry<Long, SubmitTestRequest.AnswerData> entry : request.getAnswers().entrySet()) {
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setTestSession(session);
            userAnswer.setQuestionId(entry.getKey());
            userAnswer.setSelectedOption(entry.getValue().getSelectedOption());
            userAnswer.setCorrectOption(entry.getValue().getCorrectOption());
            
            if (entry.getValue().getSelectedOption() == null) {
                unansweredCount++;
                userAnswer.setIsCorrect(false);
            } else if (entry.getValue().getSelectedOption().equals(entry.getValue().getCorrectOption())) {
                correctCount++;
                userAnswer.setIsCorrect(true);
            } else {
                incorrectCount++;
                userAnswer.setIsCorrect(false);
            }
            
            userAnswerRepository.save(userAnswer);
        }
        
        // Calculate score
        int score = (int) Math.round((correctCount * 100.0) / session.getTotalQuestions());
        
        // Update session
        session.setEndTime(LocalDateTime.now());
        session.setCompleted(true);
        session.setScore(score);
        session.setCorrectAnswers(correctCount);
        session.setIncorrectAnswers(incorrectCount);
        session.setUnansweredQuestions(unansweredCount);
        session.setTimeTaken(request.getTimeTaken());
        
        testSessionRepository.save(session);
        
        // Create result
        TestResult result = new TestResult();
        result.setTestSessionId(session.getId());
        result.setScore(score);
        result.setCorrectAnswers(correctCount);
        result.setIncorrectAnswers(incorrectCount);
        result.setUnansweredQuestions(unansweredCount);
        result.setTotalQuestions(session.getTotalQuestions());
        result.setTimeTaken(request.getTimeTaken());
        
        return result;
    }
    
    public Challenge createChallenge(Long challengerId, Long challengedId, Long categoryId) {
        Challenge challenge = new Challenge();
        challenge.setChallengerId(challengerId);
        challenge.setChallengedId(challengedId);
        challenge.setCategoryId(categoryId);
        
        return challengeRepository.save(challenge);
    }
    
    public List<TestSession> getUserTestHistory(Long userId) {
        return testSessionRepository.findByUserIdOrderByStartTimeDesc(userId);
    }
    
    public List<Challenge> getUserChallenges(Long userId) {
        return challengeRepository.findByChallengerIdOrChallengedId(userId, userId);
    }
}