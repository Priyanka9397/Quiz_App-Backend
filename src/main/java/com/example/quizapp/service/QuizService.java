package com.example.quizapp.service;

import com.example.quizapp.model.Quiz;
import com.example.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Transactional
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz updateQuiz(String id, Quiz quiz) {
        return quizRepository.findById(id).map(existingQuiz -> {
            existingQuiz.setQuizName(quiz.getQuizName());
            existingQuiz.setQuestions(quiz.getQuestions());
            existingQuiz.setAnswers(quiz.getAnswers());
            return quizRepository.save(existingQuiz);
        }).orElse(null);
    }

    @Transactional
    public void deleteQuiz(String id) {
        quizRepository.deleteById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(String id) {
        return quizRepository.findById(id).orElse(null);
    }
}
