package com.tangwh.controller;

import com.tangwh.pojo.User;
import com.tangwh.service.UserOneJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserOneJdbcController {


    @Autowired
    UserOneJdbcService userOneJdbcService;

    @GetMapping("/query")
    public void query(){

        List<User> userOne = userOneJdbcService.getUserDataSourceOne();
        for (User user1 : userOne) {
            System.out.println("数据源One的数据"+user1);
        }

        List<User> user = userOneJdbcService.getUserDataSourceTwo();
        for (User user1 : user) {
            System.out.println("数据源Two的数据"+user1);
        }
    }

}
