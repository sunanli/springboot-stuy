package com.tangwh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 日期转换
 */
@RestController
public class Paramconverter {


    @GetMapping("/data")
    public void dataConverter(Date birth){

        System.out.println("请求进来了");
        System.out.println(birth);
    }

}
