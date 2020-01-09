package com.tangwh.mapper;

import com.tangwh.pojo.Role;
import com.tangwh.pojo.User;

import java.util.List;

/**
 * @author  Tangweihao
 * @date  2020/1/8 23:44
 * @version 1.0
 */
public interface UserMapperq {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 通过通过用户名查询
     * @param username
     * @return
     */
    User selectByName(String username);

    /**
     *
     * @param id
     * @return
     */
    List<Role> getRoleById(Integer id);
}