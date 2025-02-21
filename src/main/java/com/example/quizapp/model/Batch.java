package com.example.quizapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

@Document(collection = "batches")
public class Batch {

    @Id
    private String id;
    private String batchName;

    @Transient
    public static final String SEQUENCE_NAME = "batches_sequence";

    public Batch() {
    }

    public Batch(String batchName) {
        this.batchName = batchName;
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
}
