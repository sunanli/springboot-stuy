package com.tangwh.controller;

import com.tangwh.daoOne.BookDaoOne;
import com.tangwh.daoTwo.BookDaoTwo;
import com.tangwh.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookOneController {

    @Autowired
    BookDaoOne bookDaoOne;

    @Autowired
    BookDaoTwo bookDaoTwo;

    @GetMapping("/findAll")
    public void findAll(){
        List<Book> all = bookDaoOne.findAll();
        all.forEach(System.out::println);

        List<Book> all1 = bookDaoTwo.findAll();
        all1.forEach(a->{
            System.out.println("数据源二的"+a);
        });
    }



}
