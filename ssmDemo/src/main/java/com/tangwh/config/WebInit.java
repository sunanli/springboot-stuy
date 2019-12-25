package com.tangwh.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 代替Web.xml
 */
public class WebInit implements WebApplicationInitializer {


    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        // 加载配置文件
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        context.setServletContext(servletContext);

        //注册bean 配置Springmvc配置
        context.register(SpringMvcConfig.class);

      //配置Servlet
        ServletRegistration.Dynamic springmvc = servletContext.addServlet("springmvc", new DispatcherServlet(context));
        springmvc.addMapping("/");
        springmvc.setLoadOnStartup(1);

    }
}
