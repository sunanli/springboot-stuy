package com.tangwh.dao;

import com.tangwh.pojo.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
// 跨域问题解决
@CrossOrigin
public interface BookDao extends JpaRepository<Book,Integer> {

    /**
     * 书名包含那个字 查询 模糊查询
     * @param name
     * @return   http://localhost:8080/books/search 查询所有可用的接口
     */
    // path 是重起的接口名 rel实现的名字
    @RestResource(path = "byName",rel = "findByName")
    List<Book> findBookByNameContaining(@Param("name")  String name);
}
