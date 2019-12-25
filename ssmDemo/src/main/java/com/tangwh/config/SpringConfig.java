package com.tangwh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/25 11:26
 */
@Configuration //配置改注解 表示:此类为配置类 代替xml文件
@ComponentScan(basePackages = "com.tangwh")
public class SpringConfig {
}
