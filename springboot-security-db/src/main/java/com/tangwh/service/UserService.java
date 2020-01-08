package com.tangwh.service;

import com.tangwh.mapper.UserMappers;
import com.tangwh.pojo.Role;
import com.tangwh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 21:45
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMappers userMappers;

    /**
     * 根据用户名去查询用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("输入的name"+username);
        User user = userMappers.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        System.out.println("查询出来的user"+user.toString());
        // 根据用户id查询用户的角色
      user.setRoles(userMappers.getUserRoleById(user.getId()));
        return user;
    }
}
