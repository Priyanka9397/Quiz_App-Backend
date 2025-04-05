
package com.example.quizapp.model;

import com.example.quizapp.model.Role;
import com.example.quizapp.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleSeeder {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("STUDENT").isEmpty()) {
                roleRepository.save(new Role("STUDENT"));
            }
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                roleRepository.save(new Role("ADMIN"));
            }
        };
    }
}

