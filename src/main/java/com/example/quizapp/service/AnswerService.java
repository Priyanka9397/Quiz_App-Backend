package com.example.quizapp.service;

import com.example.quizapp.model.Answer;
import com.example.quizapp.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByTaskId(String taskId) {
        return answerRepository.findByTaskId(taskId);
    }
}
