package com.example.demo.api;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ranges.Range;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    @GetMapping("/list")
    public Map<String, Object> testSList() {

        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");

        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
        var size = listOps.size();
        logger.info("size:" + size);
        Object result1 = listOps.rightPop();
        Object result2 = listOps.index(1);
        size = listOps.size();
        logger.info("size:" + size);
        listOps.leftPush("v0");
        size = listOps.size();
        logger.info("size:" + size);
        size = listOps.size();
        logger.info("size:" + size);
        var elements = listOps.range(0, size - 2);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/set")
    public Map<String, Object> testSet() {

        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");


        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v6", "v7");
        setOps.remove("v1", "v7");

        Set set1 = setOps.members();

        var size = setOps.size();

        var inter = setOps.intersect("set2");

        setOps.intersectAndStore("set2", "inter");

        var diff = setOps.diff("set2");
        setOps.diffAndStore("set2", "diff");

        var union = setOps.union("set2");

        setOps.unionAndStore("set2", "union");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/zset")
    public Map<String, Object> testZSet() {

        Set<ZSetOperations.TypedTuple<String>> typeTupleSet = new HashSet<>();
        for (int i = 0; i <= 9; i++) {
            double score = i * 0.1;

            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value" + i, score);
            typeTupleSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1", typeTupleSet);
        BoundZSetOperations zsetOps=stringRedisTemplate.boundZSetOps("zset1");

        zsetOps.add("value10",0.26);
        Set<String> setRange=zsetOps.range(1,6);

        setRange.forEach(p->logger.info("setRange:"+p));
        Set<String> setScore=zsetOps.rangeByScore(0.2,0.6);

        RedisZSetCommands.Range range=  new RedisZSetCommands.Range();

        range.gt("value3");
        range.lte("value8");

        Set<String> setLex=zsetOps.rangeByLex(range);

        setLex.forEach(p->logger.info("setLex:"+p));
        zsetOps.remove("value9","value2");

        double score=zsetOps.score("value8");

        Set<ZSetOperations.TypedTuple<String>> rangeSet=zsetOps.rangeWithScores(1,6);
        rangeSet.forEach(p->logger.info("rangeSet:"+p.getValue()+"  score:"+p.getScore()));
        Set<ZSetOperations.TypedTuple<String>> scoreSet=zsetOps.rangeByScoreWithScores(1,6);
        scoreSet.forEach(p->logger.info("scoreSet:"+p));
        Set<String> reverseSet=zsetOps.reverseRange(2,8);
        reverseSet.forEach(p->logger.info("reverseSet:"+p));



        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}
