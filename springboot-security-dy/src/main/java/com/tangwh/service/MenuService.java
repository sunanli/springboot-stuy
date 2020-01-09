package com.tangwh.service;

import com.tangwh.mapper.MenuMapper;
import com.tangwh.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/9 0:21
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    /**
     * 获取所有的Manu
     *  优化建议 给这个方法加缓存
     * @return
     */
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}
