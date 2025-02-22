package com.example.greetings.infrastructure.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GreetingCacheService {
    private final JedisPool jedisPool;
    private static final String DAILY_GREETING_KEY = "daily_greeting:";

    public void incrementDailyGreetingCount(String username, String date) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = DAILY_GREETING_KEY + date;
            jedis.hincrBy(key, username, 1);
            // 设置过期时间为30天
            jedis.expire(key, 30 * 24 * 60 * 60);
        }
    }

    public Map<String, Long> getDailyGreetingCount(String date) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = DAILY_GREETING_KEY + date;
            Map<String, String> counts = jedis.hgetAll(key);
            Map<String, Long> result = new HashMap<>();
            
            counts.forEach((username, count) -> 
                result.put(username, Long.parseLong(count)));
            
            return result;
        }
    }

    public void clearCache(String date) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = DAILY_GREETING_KEY + date;
            jedis.del(key);
        }
    }
}