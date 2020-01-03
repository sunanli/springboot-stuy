//package com.tangwh.controller;
//
//import com.tangwh.dao.BookDao;
//import com.tangwh.pojo.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sun.reflect.generics.tree.VoidDescriptor;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class UserController {
//
//    @Autowired(required = false)
//    BookDao bookDao;
//
//    /***
//     * 添加
//     */
//    @GetMapping("/save")
//    public void  saveBooke(){
//        Book book= new Book();
//        book.setName("三国演义");
//        book.setAuthor("罗贯中");
//        bookDao.save(book);
//
//    }
//
//    /**
//     * 修改
//     */
//    @GetMapping("/update")
//    public void update(){
//        //根据主键去修改 如果没有就是添加
//        Book entity = new Book();
//        entity.setAuthor("luoguanzong");
//        entity.setName("sanguoyanyui");
//        entity.setId(1);
//        bookDao.saveAndFlush(entity);
//    }
//
//
//    /**
//     * 删除
//     */
//    @GetMapping("/delete")
//    public void delete(){
//        bookDao.deleteById(1);
//    }
//
//    /**
//     * 查询
//     */
//    @GetMapping("/find")
//    public void query(){
//
//        Optional<Book> byId = bookDao.findById(2);
//        System.out.println(byId);
//
//        List<Book> all = bookDao.findAll();
//        all.forEach(a->{
//            System.out.println(a);
//        });
//    }
//
//    //倒叙查询
//    @GetMapping("/find2")
//    public void  query2(){
//
//        //倒叙
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//
//        List<Book> all = bookDao.findAll(sort);
//        all.forEach(System.out::println);
//
//    }
//
//    /**
//     * 分页查询
//     */
//    @GetMapping("/find3")
//    public void  query3(){
//         //                                当前第几页,每页两条数据
//        Pageable pageable = PageRequest.of(0,2);
//
//        Page<Book> page = bookDao.findAll(pageable);
//
//        System.out.println("总条数:"+page.getTotalElements());
//        System.out.println("当前页记录数:"+page.getNumberOfElements());
//        System.out.println("每页记录数:"+page.getSize());
//        System.out.println("获取总页数"+page.getTotalPages());
//        System.out.println("查询结果"+page.getContent());
//        System.out.println("当前页(从0开始计算)"+page.getNumber());
//        System.out.println("当前是否为首页"+page.isFirst());
//        System.out.println("当前是否是末页"+page.isLast());
//
//
//    }
//
//
//}
