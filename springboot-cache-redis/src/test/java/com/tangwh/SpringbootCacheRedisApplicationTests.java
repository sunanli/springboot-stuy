package com.tangwh;

import com.tangwh.pojo.User;
import com.tangwh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SpringbootCacheRedisApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {


        User u = userService.getUserById(1);



        userService.updateUserById(1);
        User u1 = userService.getUserById(1);
        System.out.println(u);
        System.out.println(u1);
    }

}
