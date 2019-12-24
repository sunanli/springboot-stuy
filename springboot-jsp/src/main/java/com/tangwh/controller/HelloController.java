package com.tangwh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/24 16:39
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model,String name){
        System.out.println("请求进来了"+name);
        model.addAttribute("name", name);
        return "hello";
    }
}
