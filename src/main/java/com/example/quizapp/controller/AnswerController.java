package com.example.quizapp.controller;

import com.example.quizapp.model.Answer;
import com.example.quizapp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/submit")
    public Answer submitAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

//    @GetMapping("/")
//    public List<Answer> getAllAnswers() {
//        return answerService.getAllAnswers();
//    }
}
