package com.tangwh.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * 配置拦截器
 */
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
