package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo.*")
@EnableJpaRepositories("com.example.demo.*")
@EntityScan("com.example.demo.*")
public class BookExchangeFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookExchangeFinalProjectApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService userService, BookService bookService) {
        return args -> {
            userService.addUser(new User("Aida","Tynybek kyzy",
                    "password",
                    "tynybekkyzyaida@gmail.com","Osh",Role.ADMIN,
                    LocalDateTime.now()));
            userService.addUser(new User("Rysbek","Seidaliev",
                    "password",
                    "tynybekkyzyaizirek@gmail.com ","Osh",Role.USER,
                    LocalDateTime.now()));
            bookService.addBook()

        };
    }

}
