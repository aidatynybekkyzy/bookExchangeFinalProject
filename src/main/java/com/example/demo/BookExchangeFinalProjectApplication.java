package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo.*")
@EnableJpaRepositories("com.example.demo.*")
@EntityScan("com.example.demo.*")
public class BookExchangeFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookExchangeFinalProjectApplication.class, args);
    }

   /* @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));


            userService.addUser(new User("Aidatyn", "12345678",
                    "aida@gmail.com", "aida tynybek kyzy", "Osh"));
        };
    }*/

}
