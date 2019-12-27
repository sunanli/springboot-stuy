package com.tangwh.controller;

import com.tangwh.SayHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/hello")
    // 跨域可以请求 愿意接受哪里的请求 可以在类上
    //@CrossOrigin(origins = "http://localhost:8081")
    public String hello(){


        return "Hello Cors";
    }

    @PutMapping("/doput")
    public String doPut(){
        System.out.println("请求进来了");
        return "doput";
    }

    @Autowired
    SayHello sayHello;


    /**
     * 测试SayHello类是否注入进来
     */
    @GetMapping("/")
    public void testSayHello(){
        System.out.println(sayHello.sayHello1());
    }
}
