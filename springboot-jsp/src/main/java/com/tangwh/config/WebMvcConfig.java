package com.tangwh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/24 16:42
 */

/**
 * 实现视图解析器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    // 配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        System.out.println("进入到视图解析器");
        registry.jsp("/jsp/",".jsp");
    }
}
