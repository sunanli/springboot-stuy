package com.tangwh.controller;

import com.tangwh.pojo.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name){

        return "Hello"+name;


    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){

        return book;
    }
}
