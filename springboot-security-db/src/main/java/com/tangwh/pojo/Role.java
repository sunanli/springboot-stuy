package com.tangwh.pojo;

import lombok.Data;

/**
 * @author  Tangweihao
 * @date  2020/1/8 21:26
 * @version 1.0
 */
@Data
public class Role {
    /**
    * 
    */
    private Integer id;

    /**
    *  英文名
    */
    private String name;

    /**
    *  中文名
    */
    private String nameZh;
}