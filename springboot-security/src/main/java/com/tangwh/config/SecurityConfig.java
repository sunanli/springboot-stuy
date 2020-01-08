package com.tangwh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 15:55
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 密码加密
    @Bean
    PasswordEncoder passwordEncoder(){
        // 告诉用户 密码不用加密
        return NoOpPasswordEncoder.getInstance();
    }


    /**
     * 配置用户名和密码
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
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {



    }
}
