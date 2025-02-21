package com.example.quizapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.Batch;
import com.example.quizapp.model.User;
import com.example.quizapp.service.BatchService;
import com.example.quizapp.service.UserService;

@RestController
@RequestMapping("/api/admin/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Batch> createBatch(@RequestBody Map<String, Object> request) {
        String batchName = (String) request.get("batchName");
        return ResponseEntity.ok(batchService.createBatch(batchName));
    }

    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Batch> getBatchById(@PathVariable String id) {
        Batch batch = batchService.getBatchById(id);
        if (batch != null) {
            return ResponseEntity.ok(batch);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable String id) {
        batchService.deleteBatch(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateUserBatchId")
    public ResponseEntity<String> updateUserBatchId(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String batchId = request.get("batchId");
        userService.updateUserBatchId(userId, batchId);
        return ResponseEntity.ok("\"User batch updated successfully\"");
    }

    // get all users in a batch
    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersInBatch(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUsersByBatchId(id));
    }
}
