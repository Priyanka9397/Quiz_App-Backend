package com.example.quizapp.service;

import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Task;

import java.util.List;
import java.util.Map;

public class TaskResults {
    private Task task;
    private List<Map<String, Object>> scores;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Map<String, Object>> getScores() {
        return scores;
    }

    public void setScores(List<Map<String, Object>> scores) {
        this.scores = scores;
    }
}
