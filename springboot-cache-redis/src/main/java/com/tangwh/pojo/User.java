package com.tangwh.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 13:18
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String username;


    private String address;
}
