package com.tangwh.service;

import com.tangwh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 此类的数据源已经被注释掉
 */
@Service
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 删除操作
     * @param user
     * @return
     */
    public Integer addUser(User user){
        System.out.println(user);
        System.out.println(user.getUsername());
        // 增删改 都是update方法
         return jdbcTemplate.update("insert into user (username,address) values (?,?);", user.getUsername(), user.getAddress());
    }


    /**
     * 更新操作
     * @param user
     * @return
     */
    public Integer updateUsernameById(User user){


        return jdbcTemplate.update("update user set username=? where id =?;",user.getUsername(),user.getId());
    }


    /**
     * 删除操作
     * @param id
     * @return
     */
    public Integer deleteUserById(Integer id){

        return jdbcTemplate.update("delete from user where id =?;",id);
    }


    /**
     * 查询所有  属性名和数据库名不对应时候 必须一一对应起来
     * @return
     */
    public List<User> getUsers(){

        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");

                user.setId(id);
                user.setUsername(username);
                user.setAddress(address);

                return user;


            }
        });
    }


    /**
     * 数据库名和实体一一对应时
     * @return
     */
    public List<User> getUser(){
        return jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<>(User.class));
    }
}
