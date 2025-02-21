package com.example.quizapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.User;
import com.example.quizapp.service.UserDetailsServiceImpl;
import com.example.quizapp.service.UserService;
import com.example.quizapp.util.JwtUtil;

@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthController {
    private static final Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@RequestBody User user) {
        logger.info("Authenticating admin: {}", user.getEmail());
        try {
            logger.debug("Attempting authentication for user: {}", user.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            logger.debug("Authentication successful for user: {}", user.getEmail());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(user.getEmail());
            logger.info("Admin authenticated successfully: {}", user.getEmail());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            logger.error("Authentication failed for admin: {}", user.getEmail(), e);
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        userService.addUser(user);
        return ResponseEntity.ok("Admin registered successfully");
    }
}
