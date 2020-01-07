package com.tangwh.controller;

import com.tangwh.dao.BookDao;
import com.tangwh.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/7 9:17
 */
@RestController
public class BookController {


    @Autowired
    BookDao bookDao;

    @GetMapping("/book")
    public void getBook(){

        Book book = new Book();
        book.setId(1);
        book.setName("三国");
        book.setAuthor("罗贯中");

        bookDao.insert(book);
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/book")
    public void getBook1(){

        Book book = new Book();
        book.setId(1);
        book.setName("三国");
        book.setAuthor("罗贯中");

        mongoTemplate.insert(book);
    }

}
