package com.tangwh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/7 11:24
 */
@RestController
public class HelloController {


    @PostMapping("/hello")
    public String getHello(@RequestParam("name") String info){
        System.out.println(info);

        return info;
    }
}
