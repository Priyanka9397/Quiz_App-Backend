package com.example.quizapp.service;

import com.example.quizapp.model.Batch;
import com.example.quizapp.model.User;
import com.example.quizapp.repository.BatchRepository;
import com.example.quizapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserRepository userRepository;

    public Batch createBatch(String batchName, List<String> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        Batch batch = new Batch(batchName, users);
        return batchRepository.save(batch);
    }

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public Batch getBatchById(String id) {
        return batchRepository.findById(id).orElse(null);
    }

    public void deleteBatch(String id) {
        batchRepository.deleteById(id);
    }
}
