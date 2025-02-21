package com.example.quizapp.controller;

import com.example.quizapp.model.Batch;
import com.example.quizapp.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<Batch> createBatch(@RequestBody Map<String, Object> request) {
        String batchName = (String) request.get("batchName");
        @SuppressWarnings("unchecked")
        List<String> userIds = (List<String>) request.get("userIds");
        return ResponseEntity.ok(batchService.createBatch(batchName, userIds));
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
}
