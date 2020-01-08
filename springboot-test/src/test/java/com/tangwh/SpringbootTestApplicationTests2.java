package com.tangwh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
/**
 * 真实的Servlet 环境
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class SpringbootTestApplicationTests2 {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void contextLoads() {

        String javaBoy = testRestTemplate.getForObject("/hello?name{1}", String.class, "javaBoy");

        System.out.println(javaBoy);

    }
}
