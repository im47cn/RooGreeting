package com.example.greetings.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "greetings")
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime greetingTime;

    @Column(nullable = false)
    private String greetingMessage;

    @Column(name = "greeting_date", nullable = false)
    private String greetingDate;

    public static Greeting createGreeting(String username) {
        LocalDateTime now = LocalDateTime.now();
        return new Greeting()
                .setUsername(username)
                .setGreetingTime(now)
                .setGreetingDate(now.toLocalDate().toString())
                .setGreetingMessage("Hello " + username);
    }
}