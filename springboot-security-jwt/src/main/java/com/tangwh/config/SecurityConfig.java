package com.tangwh.config;

import com.tangwh.filter.JwtFilter;
import com.tangwh.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 密码加密比较
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("javaboy")
               // 明文密码 123
               .password("$2a$10$8tHK1DckxmChR0ywsmnBneNuVhSf0X9eIp2.SZz/FwnLC1KnOXGji")
               .roles("user")
               .and()
               .withUser("admin")
               //   明文密码 123
               .password("$2a$10$aoiywhrsQX9W7BDv3Q8bCuDNpj5TW45tXXIS0rBta9jfN0NZ07hQ.")
               .roles("admin");
    }

    /**
     * 加入俩的 过滤器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/hello")
               .hasRole("User")
               .antMatchers("/admin")
               .hasRole("admin")
               // 如果你的请求时POST请求 并且还是login
               .antMatchers(HttpMethod.POST,"/login")
               // 直接放行
               .permitAll()
               // 剩下的请求
               .anyRequest().authenticated()
               .and()
               //  加入过滤器  参数一:登陆地址
               .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()),
                       UsernamePasswordAuthenticationFilter.class)
               // 加入校验过滤器
               .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
               .csrf().disable();

    }
}
