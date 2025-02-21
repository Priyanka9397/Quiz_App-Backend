package com.example.quizapp.controller;

import com.example.quizapp.model.Answer;
import com.example.quizapp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/submit")
    public Answer submitAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }
}
