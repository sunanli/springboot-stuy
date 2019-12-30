package com.tangwh.service;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUsernameById(Integer id){
        System.out.println("getUsernameById");

        return "javaBoy";
    }

    public void deleteUserById(Integer id){

        System.out.println("deleteUserById");
    }

}
