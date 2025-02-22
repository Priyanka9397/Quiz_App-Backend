package com.example.quizapp.service;

import com.example.quizapp.model.Answer;
import com.example.quizapp.model.Batch;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Task;

import java.util.List;

public class TasksResponse {
    private Task task;
    private Quiz quiz;
    private String status;
    private Answer answer;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
