package com.example.quizapp.repository;

import com.example.quizapp.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {
    List<Answer> findByTaskId(String taskId);
    //List<Answer> findAllByUserId(String userId);

    List<Answer> findByStudentId(String userId);
}
