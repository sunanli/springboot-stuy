package com.tangwh.globa;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobaData {

    /**
     * 预设全局数据
     */
    @ModelAttribute(value = "info") // --- 对应的Key  map--对应的value
    public Map<String,Object> mydata(){
     Map<String,Object> map = new HashMap<>();

     map.put("name","tang");
     map.put("address","陕西西安");


     return map;
    }

    /**
     * 请求参数预处理  和Controller中的参数进行绑定
     * @param binder
     */

    @InitBinder("a")
    public void initA(WebDataBinder binder){

        binder.setFieldDefaultPrefix("a.");
    }
    @InitBinder("b")
    public void initB(WebDataBinder binder){

        binder.setFieldDefaultPrefix("b.");
    }
}
