package com.tangwh.config;

//import com.fasterxml.jackson.databind.ObjectMapper;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentNavigableMap;

@Configuration
public class WebConfig {
//   配置JSON
//    @Bean
//    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//        ObjectMapper om = new ObjectMapper();
//        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        converter.setObjectMapper(om);
//
//        return converter;
//    }
//
//    @Bean
//    ObjectMapper objectMapper(){
//
//        ObjectMapper om = new ObjectMapper();
//        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        return om;
//    }


//  // 配置Gson
//    @Bean
//GsonHttpMessageConverter gsonHttpMessageConverter(){
//
//    GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//    converter.setGson(new GsonBuilder().setDateFormat("yyyy-MM-dd").create());
//
//
//    return converter;
//}

//    @Bean
//    Gson gson(){
//        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//    }


    //配置FastJSON
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");

        converter.setFastJsonConfig(config);
        return converter;
    }
}
