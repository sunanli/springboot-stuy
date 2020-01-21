package com.tangwh.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloService {

    @Scheduled(fixedDelay = 2000) //第一次的定时任务完成后 延迟2s进行下一次(前面任务结束时间和后面任务的时间之间间隔2s)
    public void fixedDelay(){
        System.out.println("fixedDelay>>>"+new Date());
    }

    @Scheduled(fixedRate = 2000) //两次定时任务之间开始的间隔时间为2s  (启动新的执行任务 不管前面的定时任务有没有执行完成 )旧的定时任务开启和新的执行任务开的时间的间隔是2s
    public void fixedRate(){
        System.out.println("fixedRate>>>"+new Date());
    }


    // 每个五秒打印
    @Scheduled(cron = "0/5 * * * * ?")
    public void cron(){

        System.out.println("cron>>"+new Date());
    }
}
