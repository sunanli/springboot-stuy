package com.tangwh.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置跨域 并制定使用XML文件
 */
@Configuration
@ImportResource(locations = "classpath:bean.xml")
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("设置生效");
        registry.addMapping("/**")
                // 允许的来源
                .allowedOrigins("http://localhost:8081")
                //允许经过的请求头
                .allowedHeaders("*")
                // 允许的方法
                .allowedMethods("*")
                // 有效时间
                .maxAge(30*1000);

    }
}
