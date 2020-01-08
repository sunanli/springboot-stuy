package com.tangwh.service;

import com.tangwh.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 13:21
 */
@Service
//@CacheConfig(cacheNames = "c1") // 统一的c1
public class UserService {
    // key 虽然参数有多个 但是主要用的是key 就一起缓存 也就是redis 存的key 当然这个key也可以自动生成
    @Cacheable(cacheNames = "c1")//,keyGenerator = "myKeyGenerator") //启用缓存 name就是起的名字在配置文件
    public User getUserById(Integer id) {
        System.out.println("getUserById传进来的Id" + id);

        User user = new User();
        user.setId(1);
        user.setUsername("唐维豪");
        user.setAddress("西安");


        return user;
    }

    // 如果删除了 那么请把缓存也清除 不然会出现藏的数据
    @CacheEvict(cacheNames = "c1")
    public void deleteUserById(Integer id) {

        System.out.println("deleteUserById传进来的id" + id);


    }


    // 更新操作
    @CachePut(cacheNames = "c1")
    public User updateUserById(Integer id) {

        System.out.println("updateUserById传进来的Id" + id);
        User user = new User();
        user.setId(id);
        user.setUsername("Howe");
        user.setAddress("上海");

        return user;

    }

    //更新操作二
    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        return user;
    }
}
