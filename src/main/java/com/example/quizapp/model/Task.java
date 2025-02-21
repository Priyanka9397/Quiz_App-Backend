package com.example.quizapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String quizId;
    private String batchId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Transient
    public static final String SEQUENCE_NAME = "tasks_sequence";

    public Task() {
    }

    public Task(String quiz, String batchId, LocalDateTime startTime, LocalDateTime endTime) {
        this.quizId = quiz;
        this.batchId = batchId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quiz) {
        this.quizId = quiz;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
