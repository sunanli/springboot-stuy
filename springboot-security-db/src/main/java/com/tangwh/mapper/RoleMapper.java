package com.tangwh.mapper;

import com.tangwh.pojo.Role;

/**
 * @author  Tangweihao
 * @date  2020/1/8 21:26
 * @version 1.0
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}