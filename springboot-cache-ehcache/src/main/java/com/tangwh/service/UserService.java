package com.tangwh.service;

import com.tangwh.pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 15:12
 */
@Service
public class UserService {



    //cacheNames 指定的是你配置文件 ehcache的文件的名
    @Cacheable(cacheNames = "mycache")
    public User getUser(Integer id){

        User user = new User();
        user.setId(id);
        System.out.println("传进来的ID"+id);
        return user;

    }
}
