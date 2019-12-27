package com.tangwh.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/** 方法1
 * 系统启动任务 1
 */
@Component
@Order(99) //多个系统启动任务 要设置优先级
public class MyCommandLineRuner1 implements CommandLineRunner {

    /**
     * 系统启动的时候要做的东西
     * @param args 启动类的参数  需要给启动类args 参数配置 或者在java -jar **** 参数1 参数2
     *
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        System.out.println("MyCommandLineRuner1>>>"+Arrays.toString(args));
    }
}
