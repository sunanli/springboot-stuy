package com.tangwh.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author  Tangweihao
 * @date  2020/1/8 23:54
 * @version 1.0
 */
@Data
public class Menu {
    /**
    * 
    */
    private Integer id;

    /**
    * 
    */
    private String pattern;

    // 需要那些角色访问这个路径

    private List<Role> roles;
}