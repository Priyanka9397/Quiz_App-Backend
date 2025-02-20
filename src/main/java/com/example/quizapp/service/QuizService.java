package com.example.quizapp.service;

import com.example.quizapp.model.Quiz;
import com.example.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(String id, Quiz quiz) {
        Optional<Quiz> existingQuiz = quizRepository.findById(id);
        if (existingQuiz.isPresent()) {
            Quiz updatedQuiz = existingQuiz.get();
            updatedQuiz.setQuizName(quiz.getQuizName());
            updatedQuiz.setQuestions(quiz.getQuestions());
            updatedQuiz.setAnswers(quiz.getAnswers());
            return quizRepository.save(updatedQuiz);
        } else {
            return null;
        }
    }

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
