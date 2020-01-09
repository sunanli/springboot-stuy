package com.tangwh.service;

import com.tangwh.mapper.UserMapperq;
import com.tangwh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 23:57
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapperq userMapperq;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapperq.selectByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");

        }

        user.setRoles(userMapperq.getRoleById(user.getId()));
        System.err.println("登录的user"+user);
        return user;
    }
}
