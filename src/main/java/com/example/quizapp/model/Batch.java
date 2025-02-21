package com.example.quizapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Document(collection = "batches")
public class Batch {

    @Id
    private String id;
    private String batchName;
    private List<String> userIds;  // Changed from List<User> to List<String>

    @Transient
    public static final String SEQUENCE_NAME = "batches_sequence";

    public Batch() {
    }

    public Batch(String batchName, List<String> userIds) {
        this.batchName = batchName;
        this.userIds = userIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
