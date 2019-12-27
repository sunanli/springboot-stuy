package com.tangwh.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Component
public class MyErrorViewResolver extends DefaultErrorViewResolver {

    public MyErrorViewResolver(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
        super(applicationContext, resourceProperties);
    }

    /**
     * 自定义异常页面
     * @param request
     * @param status
     * @param model
     * @return
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

        ModelAndView view = new ModelAndView();
 // 自己定义的异常页面
        view.setViewName("javaboy");
        view.addAllObjects(model);
        return view;
    }
}
