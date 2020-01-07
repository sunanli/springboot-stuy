package com.tangwh.dao;

import com.tangwh.pojo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/7 9:15
 */
public interface BookDao extends MongoRepository<Book,Integer> {
}
