package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy")
                .password("69f16bb4-fc6b-4765-bdfb-1de36fdb38ac")
                .roles("admin")
                .and()
                .withUser("tang")
                .password("69f16bb4-fc6b-4765-bdfb-1de36fdb38ac")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.antMatcher("/oauth/**")
              .authorizeRequests()
              .antMatchers("/oauth/**")
              .permitAll()
              .and().csrf().disable();
    }
}
