package com.example.greetings.domain.repository;

import java.util.List;

import com.example.greetings.domain.model.Greeting;

public interface GreetingRepository {
    Greeting save(Greeting greeting);
    List<Object[]> findDailyGreetingCounts();
}