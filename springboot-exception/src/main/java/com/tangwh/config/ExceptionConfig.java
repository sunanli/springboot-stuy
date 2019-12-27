package com.tangwh.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class ExceptionConfig extends DefaultErrorAttributes {

    //获取异常数据


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        // 自己定义的异常key
        map.put("myerror","这是我自定义的一个异常信息");


        return map;
    }
}
