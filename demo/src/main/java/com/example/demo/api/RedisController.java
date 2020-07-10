package com.example.demo.api;

import org.apache.juli.logging.LogFactory;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private final RedisTemplate<Object, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private static Logger logger = Logger.getLogger(RedisController.class.getName());

    public RedisController(@Qualifier("stringRedisTemplate") StringRedisTemplate stringRedisTemplate, @Qualifier("redisTemplate") RedisTemplate<Object, Object> redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
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
                operations.opsForValue().set("key2", "value2");
                return null;
            }
        });
        return "ok";
    }

    @GetMapping("/stringAndHash")
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");

        stringRedisTemplate.opsForValue().increment("int", 1);
        logger.info(stringRedisTemplate.opsForValue().get("int"));
        stringRedisTemplate.opsForValue().decrement("int", 1);
        logger.info(stringRedisTemplate.opsForValue().get("int"));

        Map<String, String> hash = new HashMap<>();

        hash.put("field1", "value1");
        hash.put("field2", "value2");

        stringRedisTemplate.opsForHash().putAll("hash", hash);

        logger.info(stringRedisTemplate.opsForHash().get("hash", "field1").toString());
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        stringRedisTemplate.opsForHash().entries("hash").entrySet().forEach(f -> logger.info(f.getKey() + "|" + f.getValue()));
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("field2");
        hashOperations.put("filed4", "value4");

        return hashOperations.entries();
    }
}
