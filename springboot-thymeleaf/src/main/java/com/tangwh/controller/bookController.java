package com.tangwh.controller;

import com.tangwh.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/24 15:07
 */
@Controller
public class bookController {

    @GetMapping("/boo")
    public String book(Model model){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Book book = new Book();
            book.setId(i);
            book.setName("西游记"+i);
            book.setAuthor("施耐庵"+i);
            book.setPrice(38.0);
            books.add(book);
        }
        model.addAttribute("books", books);

        return "book";
    }
}
