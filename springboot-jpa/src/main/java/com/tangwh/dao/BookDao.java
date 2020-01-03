//package com.tangwh.dao;
//
//import com.tangwh.pojo.Book;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
////
//// @Repository
//public interface BookDao extends JpaRepository<Book, Integer> {
//
//    //自定义查询方法
//    Book findBookById(Integer id);
//
//    // 根据方法名不同 自然就知道需要做设么sql语句
//    // 大于id的值的列
//    List<Book> findBookByIdGreaterThan(Integer id);
//
//
////    // 小于di的值的列 或者模糊查询 name
//   List<Book> findBookByIdLessThanOrNameContaining(Integer id,String name);
//
//
//   //自定义sql
//    @Query(value = "SELECT * FROM t_book  WHERE  id=(SELECT MAX(id) from t_book )",nativeQuery = true)
//    Book getMaxId();
//
//
//    // 自定义数据修改sql
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT into t_book(name,author)  values(?1,?2) ",nativeQuery = true)
//    Integer addBook(String name,String author);
//
//
//    // 自定义数据修改sql
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT into t_book(name,author)  values(:name,:author)",nativeQuery = true)
//    Integer addBook2(@Param("name") String name, @Param("author")  String author);
//}
