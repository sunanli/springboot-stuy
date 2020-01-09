package com.tangwh.mapper;

import com.tangwh.pojo.Role;
import com.tangwh.pojo.User;

import java.util.List;

/**
 * @author  Tangweihao
 * @date  2020/1/8 21:27
 * @version 1.0
 */
public interface UserMappers {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    List<Role> getUserRoleById(Integer id);
}