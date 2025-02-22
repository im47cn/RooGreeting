package com.example.greetings.application.service;

import com.example.greetings.domain.model.Greeting;
import com.example.greetings.domain.service.GreetingDomainService;
import com.example.greetings.infrastructure.cache.GreetingCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GreetingApplicationService {
    private final GreetingDomainService greetingDomainService;
    private final GreetingCacheService greetingCacheService;

    @Transactional
    public Greeting saveGreeting(String username) {
        // 创建并保存问候
        Greeting greeting = greetingDomainService.createGreeting(username);
        Greeting savedGreeting = greetingDomainService.saveGreeting(greeting);

        // 更新缓存
        try {
            greetingCacheService.incrementDailyGreetingCount(
                username, 
                savedGreeting.getGreetingDate()
            );
        } catch (Exception e) {
            // 缓存操作失败不影响主流程
            e.printStackTrace();
        }

        return savedGreeting;
    }

    public List<Object[]> getDailyGreetingCounts() {
        return greetingDomainService.getDailyGreetingCounts();
    }

    public Map<String, Long> getTodayGreetingCounts() {
        String today = LocalDateTime.now().toLocalDate().toString();
        try {
            return greetingCacheService.getDailyGreetingCount(today);
        } catch (Exception e) {
            // 缓存读取失败时返回空Map
            e.printStackTrace();
            return Map.of();
        }
    }
}