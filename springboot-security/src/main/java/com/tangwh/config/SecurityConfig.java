package com.tangwh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 15:55
 */
// @Configuration  为了配置多个HttpSectury
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 密码加密
    @Bean
    PasswordEncoder passwordEncoder() {
        // 告诉用户 密码不用加密
        return NoOpPasswordEncoder.getInstance();
    }


    /**
     * 配置用户名和密码
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存的验证
        auth.inMemoryAuthentication()
                .withUser("howe")
                .password("123456").roles("admin")
                .and()
                .withUser("tang")
                .password("123")
                .roles("user");
    }


    /**
     * 配置拦截规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 开启这个配置
        http.authorizeRequests()
                // 如果这个路径是这样规则 可以具备哪些角色(admin)
                .antMatchers("/admin/**").hasRole("admin")
                // 包含其中的一个 才能访问user
                // .antMatchers("/user/**").hasAnyRole("admin","user")
                // 这个页面需要哪些角色去访问
                .antMatchers("/user/**").access("hasAnyRole('user','admin')")
                // 剩下的其他的请求 登录之后就能访问
                .anyRequest().authenticated()
                .and()
                // 表单登录
                .formLogin()
                // 处理登录请求的地址
                .loginProcessingUrl("/doLogin")
                // 登录页面
                .loginPage("/login")
                //设置请求路径上的参数名字
                .usernameParameter("uname")
                .passwordParameter("passwd")
                // 前后端部分里的情况下 需要登陆成功后需要台跳转的那个页面
                .successForwardUrl("/login")
                // 前后端分离的情况下 需要返回JSON  登录成功的处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
                            , Authentication authentication) throws IOException, ServletException {
                        //authentication用户信息 返回json
                        response.setContentType("application/json;charset=utf-8");
                        //PrintWriter是java中很常见的一个类，该类可用来创建一个文件并向文本文件写入数据。可以理解为java中的文件输出
                        PrintWriter out = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        // 返回用户信息 对象
                        map.put("msg", authentication.getPrincipal());
                        out.write(new ObjectMapper().writeValueAsString(map));

                        out.flush();
                        out.close();
                    }
                })
                //前后端分离 登录失败
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
                            , AuthenticationException e) throws IOException, ServletException {
                        //AuthenticationException 返回失败原因  根据e的类型去判断失败的原因
                        response.setContentType("application/json;charset=utf-8");
                        //PrintWriter是java中很常见的一个类，该类可用来创建一个文件并向文本文件写入数据。可以理解为java中的文件输出
                        PrintWriter out = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);

                        if (e instanceof LockedException) {
                            map.put("msg", "账户被锁定,登录失败");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "用户名或密码输入错误,登录失败");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账户禁用,登录失败");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账户过期登录失败");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "密码过期登录失败");
                        } else {
                            map.put("msg", "登录失败");
                        }


                        // 返回用户信息 对象
                        out.write(new ObjectMapper().writeValueAsString(map));

                        out.flush();
                        out.close();
                    }
                })
                // 跟登录请求的所有接口都允许访问
                .permitAll()
                .and()
                // 注销登录
                .logout()
                //注销的地址
                .logoutUrl("/logout")
                // 注销成功的回调
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                        //authentication用户信息 返回json
                        response.setContentType("application/json;charset=utf-8");
                        //PrintWriter是java中很常见的一个类，该类可用来创建一个文件并向文本文件写入数据。可以理解为java中的文件输出
                        PrintWriter out = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "注销登录成功");

                        out.write(new ObjectMapper().writeValueAsString(map));

                        out.flush();
                        out.close();


                    }
                })
                .and()
                // 使用postMan测试 关闭csrf 攻击
                .csrf().disable();


    }
}
