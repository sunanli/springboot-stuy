package com.tangwh.mapper;

import com.tangwh.pojo.Menu;

import java.util.List;

/**
 * @author  Tangweihao
 * @date  2020/1/8 23:54
 * @version 1.0
 */
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询所有的Menu
     * @return
     */
    List<Menu> getAllMenus();
}