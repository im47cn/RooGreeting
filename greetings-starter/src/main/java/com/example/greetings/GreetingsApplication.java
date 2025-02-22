package com.example.greetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
    scanBasePackages = {
        "com.example.greetings.domain",
        "com.example.greetings.infrastructure",
        "com.example.greetings.application",
        "com.example.greetings.api"
    }
)
@EntityScan("com.example.greetings.domain.model")
@EnableJpaRepositories("com.example.greetings.infrastructure.repository")
public class GreetingsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreetingsApplication.class, args);
    }
}