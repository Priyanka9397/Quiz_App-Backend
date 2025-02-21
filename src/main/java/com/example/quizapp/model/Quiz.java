package com.example.quizapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "quizzes")
public class Quiz {

    @Id
    private String id;
    private String quizName;
    private List<Question> questions;
    private int timeForQuestion;

    @Transient
    public static final String SEQUENCE_NAME = "quizzes_sequence";

    public Quiz() {
    }

    public Quiz(String quizName, List<Question> questions, int timeForQuestion) {
        this.quizName = quizName;
        this.questions = questions;
        this.timeForQuestion = timeForQuestion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getTimeForQuestion() {
        return timeForQuestion;
    }

    public void setTimeForQuestion(int timeForQuestion) {
        this.timeForQuestion = timeForQuestion;
    }
}
