package com.example.quizapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizapp.model.Batch;
import com.example.quizapp.model.User;
import com.example.quizapp.repository.BatchRepository;
import com.example.quizapp.repository.UserRepository;

@Service
public class    AnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BatchRepository batchRepository;

    public Map<String, Object> getUserAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        List<User> users = userRepository.findAll();

        long totalUsers = users.size();
        long totalStudents = users.stream().filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("STUDENT"))).count();
        long totalAdmins = users.stream().filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))).count();

        analytics.put("totalUsers", totalUsers);
        analytics.put("totalStudents", totalStudents);
        analytics.put("totalAdmins", totalAdmins);

        return analytics;
    }

    public Map<String, Long> getStudentsInEachBatch() {
        Map<String, Long> batchStudentCount = new HashMap<>();
        List<Batch> batches = batchRepository.findAll();

        for (Batch batch : batches) {
            long studentCount = userRepository.findByBatchId(batch.getId()).stream()
                    .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("STUDENT")))
                    .count();
            batchStudentCount.put(batch.getBatchName(), studentCount);
        }

        return batchStudentCount;
    }
}
