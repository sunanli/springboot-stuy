package com.tangwh.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tangwh.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/25 11:27
 */
@Configuration
//只扫Controller 还包括@Configuration 注解(其实就是Spring)
@ComponentScan(basePackages = "com.tangwh", useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class), @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)})
public class SpringMvcConfig extends WebMvcConfigurationSupport {


    /**
     * 配置静态资源路径
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置在static 文件下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
    }

    @Bean
        //拦截器注入到Bean中
    MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    /**
     * 消息转换器
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();


        converters.add(converter);
    }
}
