package com.tangwh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/9 0:07
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/db/hello")
    public String db() {
        return "Hello db";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "Hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "Hello user";
    }
}
