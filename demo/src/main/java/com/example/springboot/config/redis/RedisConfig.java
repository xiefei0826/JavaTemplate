package com.example.springboot.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    private final RedisConnectionFactory connectionFactory;

    public RedisConfig(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    //        @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.connectionFactory != null)
            return this.connectionFactory;
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        poolConfig.setMaxIdle(30);

        poolConfig.setMaxTotal(50);

        poolConfig.setMaxWaitMillis(2000);

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);

        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
//        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        RedisSerializer<String> stringRedisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }
}
