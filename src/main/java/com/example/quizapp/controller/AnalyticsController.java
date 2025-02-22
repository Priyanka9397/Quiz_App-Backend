package com.example.quizapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.service.AnalyticsService;

@RestController
@RequestMapping("/api/admin/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUserAnalytics() {
        Map<String, Object> analytics = analyticsService.getUserAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/students-in-batches")
    public ResponseEntity<Map<String, Long>> getStudentsInEachBatch() {
        Map<String, Long> batchStudentCount = analyticsService.getStudentsInEachBatch();
        return ResponseEntity.ok(batchStudentCount);
    }
}
