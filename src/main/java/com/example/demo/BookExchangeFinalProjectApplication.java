package com.example.demo;

import com.example.demo.dto.UserCreationDto;
import com.example.demo.entity.Role;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
public class BookExchangeFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookExchangeFinalProjectApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   @Bean
   CommandLineRunner run(UserService userService, BookService bookService) {
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_MANAGER"));
            userService.saveRole(new Role("ROLE_ADMIN"));

            userService.addUser(new UserCreationDto(null, "Aida Tynybek kyzy", "AidaTyn",
                    "aidatynybekkyzy@gmail.com",
                    "password", "@AidaTyn", "Osh",
                    new ArrayList<>(), Collections.emptySet()));
            userService.addUser(new UserCreationDto(null, "Aizirek Tynybek kyzy", "Aizirek",
                    "aizirektynybekkyzy@gmail.com",
                    "password", "@Aizirek", "Osh", new ArrayList<>(), Collections.emptySet()));
            userService.addUser(new UserCreationDto(null, "Rysbek Seidaliev", "Rysbek",
                    "rysbek@gmail.com",
                    "password", "@Rysbek", "Osh",
                    new ArrayList<>(), Collections.emptySet()));

            userService.addRoleToUser("AidaTyn", "ROLE_ADMIN");
            userService.addRoleToUser("Aizirek", "ROLE_MANAGER");
            userService.addRoleToUser("AidaTyn", "ROLE_USER");
        };
    }
    //TODO CASCADE TYPE TO READ; Otional.ofnullable


}
