package com.tangwh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 19:50
 */
// 配置多个HttpSecurity
@Configuration
// 方法安全注解
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MultiHttpSecutiryConfig {

//    @Bean  这样写 密码就可以写 123 不然要加密
//    PasswordEncoder passwordEncoder(){
//
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置用户名和密码
     * $2a$10$Ftz0zQqqYKZMDLNcJ7C.Y.VD9DkuTzgAqS.2dw6eRPVgs1XBfF1mu =  123
     * $2a$10$3m895IUnCUjN6XMo/gljR.Mi5SIn.pe9x/6cfhF7eRoV9ipvArwvu = 123
     * $2a$10$gfnKxtNbLj7GROnfz27h2u.z3e50ZHbGNxCiHdTDcSjjfUMfvqhJW = 123
     * $2a$10$JKE.GsrNMa8OTApVA0yiT.DoqZI6l0.tmznaQ5cCELU/yBoYZonNW = 123
     * $2a$10$7/Gcna3p/cvg0Gm4RH/rxO1a/Qq0p5GR6Gds4wSCvuf08fVtt.UEi = 123
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存的验证
        auth.inMemoryAuthentication()
                .withUser("howe")
                .password("$2a$10$Ftz0zQqqYKZMDLNcJ7C.Y.VD9DkuTzgAqS.2dw6eRPVgs1XBfF1mu").roles("admin")
                .and()
                .withUser("tang")
                .password("$2a$10$Ftz0zQqqYKZMDLNcJ7C.Y.VD9DkuTzgAqS.2dw6eRPVgs1XBfF1mu")
                .roles("user");
    }

    @Configuration
    @Order(1) //优先级的问题 多个
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 这种格式的请求统统吃能用admin访问
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasAnyRole("admin");

        }

        @Configuration
        public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter {

            @Override
            protected void configure(HttpSecurity http) throws Exception {


                // 先匹配上面的 上面的匹配不上再来找下面的
                http.authorizeRequests().anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginProcessingUrl("/doLogin")
                        .permitAll()
                        .and()
                        .csrf().disable();
            }


        }
    }
}
