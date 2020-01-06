package com.tangwh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/6 12:28
 */
@RestController
public class Controller {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public void setRedis() {
        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
        value.set("name", "javaboy");

    }
    @GetMapping("/get")
    public void getRedis() {
        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
        System.out.println(value.get("name"));

    }
}
