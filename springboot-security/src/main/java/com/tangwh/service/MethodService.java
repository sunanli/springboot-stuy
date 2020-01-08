package com.tangwh.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 20:48
 */
@Service
public class MethodService {

    // 只有admin 才能访问这个方法
    @PreAuthorize("hasRole('admin')")
    public String admin() {

        return "Hello admin";
    }
    @Secured("ROLB_user")
    public String user() {

        return "Hello user";
    }

    @PreAuthorize("hasAnyRole('admin','user')")
    public String hello() {

        return "Hello Hello";
    }
}
