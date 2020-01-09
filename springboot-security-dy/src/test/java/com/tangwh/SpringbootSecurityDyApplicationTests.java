package com.tangwh;

import com.tangwh.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSecurityDyApplicationTests {

    @Autowired
    MenuService menuService;

    @Test
    public void contextLoads() {

        System.out.println(menuService.getAllMenus());
    }

}
