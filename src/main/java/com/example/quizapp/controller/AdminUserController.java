///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//package com.example.quizapp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.quizapp.model.User;
//import com.example.quizapp.service.UserService;
//
//@RestController
//@RequestMapping("/admin/users")
//public class AdminUserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addUser(@RequestBody User user) {
//        if (userService.getUserByEmail(user.getEmail()) != null) {
//
//            return ResponseEntity.badRequest().body("Email is already taken");
//        }
//        User newUser = userService.addUser(user);
//        return ResponseEntity.ok("User Added - " + newUser.getEmail());
//    }
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<User> editUser(@PathVariable String id, @RequestBody User user) {
//        User updatedUser = userService.editUser(id, user);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//        userService.disableUser(id);
//        return ResponseEntity.noContent().build();
//    }
//}
