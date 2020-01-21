package com.tangwh.job;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyFirstJob {

    public void sayHello(){

        System.out.println("first job say hello"+new Date());
    }
}
