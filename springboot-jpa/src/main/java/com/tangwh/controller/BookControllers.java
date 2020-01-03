package com.tangwh.controller;

import com.tangwh.pojo.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 自定义的查寻方法
 */
@RestController
public class BookControllers {


//    @Autowired
//    BookDao bookDao;

    /**
     * 自定义的查询方法1
     */
    @GetMapping("/customByid")
    public void customByid(){

//        Book bookById = bookDao.findBookById(1);
//        System.out.println(bookById);

    }
//// 大于2 的值
//    @GetMapping("/da")
//    public void findBookByIdGreaterThan(Integer id){
//        List<Book> bookByIdGreaterThan = bookDao.findBookByIdGreaterThan(2);
//        bookByIdGreaterThan.forEach(book ->{
//            System.out.println("id大于2的值"+book);
//        });
//
//        List<Book> book = bookDao.findBookByIdLessThanOrNameContaining(4, "吴");
//        book.forEach(k->{
//            System.out.println(k);
//        });
//    }

//    /**
//     * 自定义sql
//     */
//    @GetMapping("/max")
//    public void queryMaxid(){
//        Book id = bookDao.getMaxId();
//        System.out.println(id);
//
//    }


//    // 自定义数据修改sql
//    @GetMapping("/query")
//    public void query(){
//        Integer book = bookDao.addBook("造化", "asd");
//       Integer book1 = bookDao.addBook2("造化1", "asd");
//        System.out.println(book);
//          System.out.println(book1);
//    }
}
