package com.tangwh.controller;

import com.tangwh.pojo.Author;
import com.tangwh.pojo.Book;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @PostMapping("/book")
    public void addBook(@ModelAttribute("b") Book book,@ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);

    }

}
