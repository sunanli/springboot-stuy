package com.tangwh.daoTwo;

import com.tangwh.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDaoTwo extends JpaRepository<Book,Integer> {
}
