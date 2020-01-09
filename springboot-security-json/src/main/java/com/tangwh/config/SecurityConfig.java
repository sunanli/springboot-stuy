package com.tangwh.config;

import com.tangwh.filter.MyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests().anyRequest().authenticated()
        .and().formLogin().permitAll().and().csrf().disable();

http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {

        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
