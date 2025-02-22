package com.example.greetings.domain.service;

import com.example.greetings.domain.model.Greeting;
import com.example.greetings.domain.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GreetingDomainService {
    private final GreetingRepository greetingRepository;

    public Greeting createGreeting(String username) {
        return Greeting.createGreeting(username);
    }

    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public List<Object[]> getDailyGreetingCounts() {
        return greetingRepository.findDailyGreetingCounts();
    }
}