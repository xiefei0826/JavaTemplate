package com.example.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Qualifier("redisTemplate")
    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisController(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test")
    public String Test() {
        redisTemplate.opsForValue().set("key1", "value1");
        return redisTemplate.opsForValue().get("key1").toString();
    }

    @GetMapping("/exec")
    public String Execute() {

        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("key2","value2");
                return null;
            }
        });
        return "ok";
    }
}
