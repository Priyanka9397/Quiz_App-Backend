package com.example.quizapp.controller;

import com.example.quizapp.model.Role;
import com.example.quizapp.model.User;
import com.example.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        User newUser = userService.addUser(user);
        return ResponseEntity.ok("User Added - "+ newUser.getEmail());
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody User admin) {
        if (userService.getUserByEmail(admin.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        User newAdmin = userService.addAdmin(admin);
        return ResponseEntity.ok("Admin Registered Successfully - " + newAdmin.getEmail());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.editUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableUser(@PathVariable String id) {
        userService.disableUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
