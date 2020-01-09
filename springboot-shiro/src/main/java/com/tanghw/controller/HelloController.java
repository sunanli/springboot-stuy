package com.tanghw.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
public class HelloController {

    @GetMapping("/login")
    public String login(){

        return "Hello login";
    }

    @PostMapping("/doLogin")
    public void doLogin(String username,String password){
        Subject subject = SecurityUtils.getSubject();

            subject.login(new UsernamePasswordToken(username, password));

    }

    @GetMapping("/hello")
    public String hello(){

        return "Hello shiro";
    }
}
