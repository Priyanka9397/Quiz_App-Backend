package com.example.quizapp.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.quizapp.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quizapp.model.Role;
import com.example.quizapp.model.User;
import com.example.quizapp.repository.UserRepository;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public User addUser(User user) {
        logger.debug("Adding user: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Assign "STUDENT" role by default
        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Role 'STUDENT' not found"));
        user.setRoles(Collections.singleton(studentRole));
        User savedUser = userRepository.save(user);

        // Send welcome email
        emailService.sendRegistrationEmail(savedUser.getEmail(), savedUser.getFirstName());

        return savedUser;
    }

    @Transactional
    public User addAdmin(User admin) {
        logger.debug("Adding admin: {}", admin.getEmail());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        // Assign both "ADMIN" and "STUDENT" roles
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role 'ADMIN' not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        admin.setRoles(roles);

        User savedAdmin = userRepository.save(admin);

        // Send admin-specific welcome email
        emailService.sendRegistrationEmail(savedAdmin.getEmail(), "Admin " + savedAdmin.getFirstName());

        return savedAdmin;
    }

    @Transactional
    public User editUser(String id, User user) {
        logger.debug("Editing user with id: {}", id);
        //find user exits
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
            existingUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : existingUser.getFirstName());
            existingUser.setLastName(user.getLastName() != null ? user.getLastName() : existingUser.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void disableUser(String id) {
        logger.debug("Disabling user with id: {}", id);
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        logger.debug("Getting all users");
        
        List<User> users = userRepository.findAll();
        // return all users where role is "STUDENT"
        users.removeIf(user -> !user.getRoles().iterator().next().getName().equals("STUDENT"));
        return users;
    }

    public User getUserById(String id) {
        logger.debug("Getting user by id: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        logger.debug("Getting user by email: {}", email);
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public void updateUserBatchId(String userId, String batchId) {
        logger.debug("Updating batch id for user with id: {}", userId);
        userRepository.findById(userId).map(existingUser -> {
            existingUser.setBatchId(batchId);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    //// get all users in a batch
    public List<User> getUsersByBatchId(String batchId) {
        logger.debug("Getting all users in batch with id: {}", batchId);
        return userRepository.findByBatchId(batchId);
    }
}
