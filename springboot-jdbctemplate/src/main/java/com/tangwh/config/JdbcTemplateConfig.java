package com.tangwh.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean(name = "jdbcTemplateOne")
    @Primary
    JdbcTemplate jdbcTemplateOne(@Qualifier("DataSourceOne") DataSource dsOne){
        return new JdbcTemplate(dsOne);
    }

    @Bean(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo(@Qualifier("DataSourceTwo") DataSource dsTwo){
        return new JdbcTemplate(dsTwo);
    }
}
