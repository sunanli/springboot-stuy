package com.tangwh.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="t_book") //给数据库创建表 如果不指定name的话表名就是类名  每个表必须都有id
public class Book {

    @Id //主键id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column()//指定创建时的约束
    private String name;

    private String author;
}
