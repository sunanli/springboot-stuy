package com.tangwh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/25 11:26
 */
@Configuration //配置改注解 表示:此类为配置类 代替xml文件
//除了Controller其他都扫
@ComponentScan(basePackages = "com.tangwh",useDefaultFilters = true,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
public class SpringConfig {
}
