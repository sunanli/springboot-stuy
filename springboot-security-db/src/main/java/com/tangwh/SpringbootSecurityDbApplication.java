package com.tangwh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tangwh.mapper")
public class SpringbootSecurityDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityDbApplication.class, args);
    }

}
