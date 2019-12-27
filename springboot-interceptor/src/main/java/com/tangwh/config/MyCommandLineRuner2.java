package com.tangwh.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**方法1
 * 系统启动任务 2
 */
@Component
@Order(98)//多个系统启动任务 要设置优先级
public class MyCommandLineRuner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRuner2>>>"+ Arrays.toString(args));
    }
}
