package com.tangwh.controller;

import com.tangwh.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 15:55
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Security";
    }

    @GetMapping("/admin/hello")
    public String admin(){

        return "Hello admin";
    }


    @GetMapping("/user/hello")
    public String user(){

        return "Hello user";
    }

    @GetMapping("/login")
    public String login() {
        return "请登录";
    }


    @Autowired
    MethodService methodService;

    @GetMapping("/hello1")
    public String hello1(){

        return methodService.admin();
    }

    @GetMapping("/hello2")
    public String hello2(){

        return methodService.user();
    }


    @GetMapping("/hello3")
    public String hello3(){

        return methodService.hello();
    }
}
