package com.tangwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching //启用缓存
public class SpringbootCacheRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheRedisApplication.class, args);
    }

}
