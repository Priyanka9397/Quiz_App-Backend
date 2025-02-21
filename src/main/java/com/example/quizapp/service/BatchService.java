package com.example.quizapp.service;

import com.example.quizapp.model.Batch;
import com.example.quizapp.model.User;
import com.example.quizapp.repository.BatchRepository;
import com.example.quizapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Batch createBatch(String batchName, List<String> userIds) {
        // Verify that all users exist
        boolean allUsersExist = userRepository.findAllById(userIds).size() == userIds.size();
        if (!allUsersExist) {
            throw new RuntimeException("One or more users not found");
        }
        
        Batch batch = new Batch(batchName, userIds);
        return batchRepository.save(batch);
    }

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public Batch getBatchById(String id) {
        return batchRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteBatch(String id) {
        batchRepository.deleteById(id);
    }
}
