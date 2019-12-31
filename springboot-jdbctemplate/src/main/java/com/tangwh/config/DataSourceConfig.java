package com.tangwh.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 多数据源配置 指定各自的数据源在配置文件中
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "DataSourceOne")
    @Primary
    @Qualifier("DataSourceOne")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource dsOne() {

        return DataSourceBuilder.create().build();
    }



    @Bean(name = "DataSourceTwo")
    @Qualifier("DataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource dsTwo() {

        return DruidDataSourceBuilder.create().build();
    }






}
