package com.tangwh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 22:08
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Security";
    }


    @GetMapping("/dba/hello")
    public String Dba() {
        return "Hello DBA";

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
