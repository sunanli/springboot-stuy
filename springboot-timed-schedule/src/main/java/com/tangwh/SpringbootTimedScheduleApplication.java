package com.tangwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 开启启动定时任务
public class SpringbootTimedScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTimedScheduleApplication.class, args);
    }

}
