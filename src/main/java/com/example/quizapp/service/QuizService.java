package com.example.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.quizapp.model.Quiz;
import com.example.quizapp.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Transactional
    public Quiz createQuiz(Quiz quiz) {
        if (!isValidQuiz(quiz)) {
            throw new IllegalArgumentException("Invalid quiz data");
        }
        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz updateQuiz(String id, Quiz quiz) {
        if (!isValidQuiz(quiz)) {
            throw new IllegalArgumentException("Invalid quiz data");
        }
        return quizRepository.findById(id).map(existingQuiz -> {
            existingQuiz.setQuizName(quiz.getQuizName());
            existingQuiz.setQuestions(quiz.getQuestions());
            existingQuiz.setTimeForQuestion(quiz.getTimeForQuestion());
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

    private boolean isValidQuiz(Quiz quiz) {
        return quiz != null && StringUtils.hasText(quiz.getQuizName()) && quiz.getQuestions() != null && !quiz.getQuestions().isEmpty();
    }
}
