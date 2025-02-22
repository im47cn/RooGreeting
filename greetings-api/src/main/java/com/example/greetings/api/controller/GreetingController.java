package com.example.greetings.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.greetings.application.service.GreetingApplicationService;
import com.example.greetings.domain.model.Greeting;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/greetings")
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingApplicationService greetingApplicationService;

    @PostMapping("/hello")
    public ResponseEntity<Greeting> sayHello(@RequestParam String username) {
        try {
            Greeting greeting = greetingApplicationService.saveGreeting(username);
            return ResponseEntity.ok(greeting);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listGreetings() {
        try {
            // 获取历史记录
            List<Object[]> historicalCounts = greetingApplicationService.getDailyGreetingCounts();
            // 获取今日实时统计
            Map<String, Long> todayCounts = greetingApplicationService.getTodayGreetingCounts();

            Map<String, Object> response = Map.of(
                "historical", historicalCounts,
                "today", todayCounts
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}