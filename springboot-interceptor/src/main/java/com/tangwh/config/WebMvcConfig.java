package com.tangwh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置我们的拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
                          //                       要拦截的路径
        registry.addInterceptor(myIniterceptor()).addPathPatterns("/**");
    }

    @Bean
    MyIniterceptor myIniterceptor() {
        return new MyIniterceptor();
    }
}
