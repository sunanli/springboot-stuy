package com.tangwh.service;

import com.tangwh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多数据源操作
 */
@Service
public class UserOneJdbcService {

    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;


    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;


    /**
     * 数据源One
     * 数据库名和实体一一对应时
     *
     * @return
     */
    public List<User> getUserDataSourceOne() {
        return jdbcTemplateTwo.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }


    /**
     * 数据源Two
     * 数据库名和实体一一对应时
     *
     * @return
     */
    public List<User> getUserDataSourceTwo() {
        return jdbcTemplateTwo.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }
}
