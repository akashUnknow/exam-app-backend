package com.examapp.test_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examapp.test_service.dto.StartTestRequest;
import com.examapp.test_service.dto.SubmitTestRequest;
import com.examapp.test_service.dto.TestResult;
import com.examapp.test_service.model.Challenge;
import com.examapp.test_service.model.TestSession;
import com.examapp.test_service.service.TestService;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "*")
public class TestController {
    
    @Autowired
    private TestService testService;
    
    @PostMapping("/start")
    public ResponseEntity<TestSession> startTest(@RequestBody StartTestRequest request) {
        return ResponseEntity.ok(testService.startTest(request));
    }
    
    @PostMapping("/submit")
    public ResponseEntity<TestResult> submitTest(@RequestBody SubmitTestRequest request) {
        return ResponseEntity.ok(testService.submitTest(request));
    }
    
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TestSession>> getUserTestHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(testService.getUserTestHistory(userId));
    }
    
    @PostMapping("/challenges")
    public ResponseEntity<Challenge> createChallenge(
            @RequestParam Long challengerId,
            @RequestParam Long challengedId,
            @RequestParam Long categoryId) {
        return ResponseEntity.ok(testService.createChallenge(challengerId, challengedId, categoryId));
    }
    
    @GetMapping("/challenges/{userId}")
    public ResponseEntity<List<Challenge>> getUserChallenges(@PathVariable Long userId) {
        return ResponseEntity.ok(testService.getUserChallenges(userId));
    }
}