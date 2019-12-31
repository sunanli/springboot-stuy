package com.tangwh.controller;

import com.tangwh.pojo.User;
import com.tangwh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 此类的数据源已经被 替换掉
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 添加接口
     * @param user
     */
    @GetMapping("/add")
    public void addUser(User user){
        System.out.println("请求进来后"+user.toString());

        Integer integer = userService.addUser(user);
        System.out.println(integer);

    }


    @GetMapping("/update")
    public void update(User user){

        System.out.println("请求进来后:"+user.toString());

        Integer integer = userService.updateUsernameById(user);

        System.out.println(integer);
    }


    @GetMapping("/delete")
    public void delet(Integer id){

        System.out.println("请求进来后:"+id);

        Integer integer = userService.deleteUserById(id);

        System.out.println(integer);
    }

    /**
     * 查询全部
     */
    @GetMapping("/")
    public void getUser(){

        // 属性不对应: List<User> users = userService.getUsers();
        List<User> users = userService.getUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
