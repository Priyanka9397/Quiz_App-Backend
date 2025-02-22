package com.example.quizapp.controller;

import java.util.List;

import com.example.quizapp.service.TasksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.Answer;
import com.example.quizapp.model.Task;
import com.example.quizapp.model.User;
import com.example.quizapp.service.AnswerService;
import com.example.quizapp.service.TaskService;
import com.example.quizapp.service.UserService;

@RestController
@RequestMapping("/api/student")
@Validated
public class StudentTaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/tasks")
    public List<TasksResponse> getTasksForStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        return taskService.getTasksForStudent(user);
    }

    @PostMapping("/submit-answer")
    public Answer submitAnswer(@Validated @RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @GetMapping("/me")
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email);
    }
}
