package com.example.quizapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.quizapp.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByBatchId(String batchId);
}
