package com.tangwh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Hellocontroller {

    @GetMapping("/getData")
    public List<String> getData() {

        List<String> lists = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            lists.add("sad" + i);
        }

        return lists;

    }
}
