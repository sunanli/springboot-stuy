package com.tangwh.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/** 方法2
 * 系统启动任务 2
 */
@Component
@Order(98)
public class MyApplicationRunner02 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取启动所有参数
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("MyApplicationRunner02>>>" + Arrays.toString(sourceArgs));

        // 没有key形式的参数
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("nonOptionArgs" + nonOptionArgs);


        //key-value形式的参数
        Set<String> optionNames = args.getOptionNames();
        System.out.println("》》》》》》》");
        for (String optionName : optionNames) {
            System.out.println("optionName" + args.getOptionValues(optionName));
        }

        System.out.println(".........结束2。。。。");

    }
}
