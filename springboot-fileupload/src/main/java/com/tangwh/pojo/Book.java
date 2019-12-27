package com.tangwh.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书籍类
 * 请求参数预处理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {


    private String name;

    private Double price;
}
