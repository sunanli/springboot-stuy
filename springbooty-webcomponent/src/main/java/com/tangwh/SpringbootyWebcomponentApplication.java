package com.tangwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.tangwh.config")
public class SpringbootyWebcomponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootyWebcomponentApplication.class, args);
    }

}
