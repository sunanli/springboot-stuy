package com.tangwh.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class GlobaDataController {
    @GetMapping("/hello")
    public String hello(Model model){
     // 转化成Map
        Map<String,Object> map = model.asMap();
        //map.keySet
        Set<String> keySet = map.keySet();
        for (String key :keySet){
            System.out.println(key+":"+map.get(key));
        }
        return "hello";
    }

}
