package com.tangwh.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/24 14:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private Integer id;

    private String name;

    private String author;

    private Double price;
}
