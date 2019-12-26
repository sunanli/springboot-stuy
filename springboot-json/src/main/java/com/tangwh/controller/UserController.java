package com.tangwh.controller;

import com.tangwh.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController //= @ResponseBody+@Controller
public class UserController {


    //@ResponseBody:返回JSON数据
    @GetMapping("/user")
    public List<User> getAllUser(){

        List<User> users = new ArrayList<>();
        for (int i =0;i<10;i++) {
            User user = new User();
            user.setId(i);
            user.setName("小明"+i);
            user.setAddress("上海"+i);
            users.add(user);
        }
        return users;
    }


}



