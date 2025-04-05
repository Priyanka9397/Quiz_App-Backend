package com.example.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String firstName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to QuizApp!");
        message.setText("Hello " + firstName + ",\n\nWelcome to QuizApp! Your account has been successfully created.\n\nBest Regards,\nQuizApp Team");

        try {
            mailSender.send(message);
            System.out.println("Email sent to " + toEmail); // Log the success
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage()); // Log any errors
        }
    }
}
