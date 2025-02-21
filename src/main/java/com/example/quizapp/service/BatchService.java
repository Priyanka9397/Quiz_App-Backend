package com.example.quizapp.service;

import com.example.quizapp.model.Batch;
import com.example.quizapp.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Transactional
    public Batch createBatch(String batchName) {
        Batch batch = new Batch(batchName);
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
