package com.tangwh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/6 14:48
 */
@RestController
public class Controller {

    @Value("${server.port}")
   Integer port;

    @GetMapping("/set")
    public String set(HttpSession session){

        session.setAttribute("name", "javaboy");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session){


        return ((String) session.getAttribute("name"))+port;
    }

}
