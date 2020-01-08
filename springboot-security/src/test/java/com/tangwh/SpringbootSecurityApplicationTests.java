package com.tangwh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public  class SpringbootSecurityApplicationTests {

    // 密码加密

    @Test
    public  void contextLoads() {

        for (int i =0; i<10;i++){

   // 密码处理工具
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println(passwordEncoder.encode("123"));
        }
    }

}
