package com.tangwh.mapperTwo;

import com.tangwh.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapperTwo {


    List<User> getAllUser();
}
