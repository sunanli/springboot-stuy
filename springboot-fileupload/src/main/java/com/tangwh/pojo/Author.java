package com.tangwh.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者类
 * 请求参数预处理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {


    private String name;

    private Integer age;
}
