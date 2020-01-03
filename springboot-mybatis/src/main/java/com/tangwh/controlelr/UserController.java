package com.tangwh.controlelr;

import com.tangwh.mapper.UserMapper;
import com.tangwh.mapperOne.UserMapperOne;
import com.tangwh.mapperTwo.UserMapperTwo;
import com.tangwh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

//
//    @Autowired
//    UserMapper userMapper;

    @Autowired
    UserMapperOne userMapperOne;

    @Autowired
    UserMapperTwo mapperTwo;

    @GetMapping("/getAllUser")
    public void getAllUser() {


        List<User> allUser = userMapperOne.getAllUsers();
        System.out.println("Lamdba00:表达式遍历集合:");
        allUser.forEach(System.out::println);

        System.out.println("Lamdba00:表达式遍历集合:");
        allUser.forEach(all -> {
            System.out.println(all);
        });

//        List<User> allUser1 = userMapperOne.getAllUser();
//        System.out.println("Lamdba01数据源:");
//        allUser1.forEach(System.out::println);
//
//        System.out.println("lamdba02表达式");
//        allUser1.forEach(al -> {
//            System.out.println(al);
//        });


    }
    @GetMapping("/getAll")
    public void getAllUsers() {

        List<User> allUser1 = mapperTwo.getAllUser();
        System.out.println("数据源二:Lamdba表达式");
        allUser1.forEach(System.out::println);

        System.out.println("数据源二:Lamdba表达式二");
        allUser1.forEach(all->{
            System.out.println(all);
        });

    }
}
