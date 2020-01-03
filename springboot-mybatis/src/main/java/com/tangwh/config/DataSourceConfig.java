package com.tangwh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean(name = "DataSourceOne")
    @Primary
    @Qualifier("DataSourceOne")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource dataOne() {

        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "DataSourceTwo")
    @Qualifier("DataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource dataTwo() {

        return DruidDataSourceBuilder.create().build();
    }
}
