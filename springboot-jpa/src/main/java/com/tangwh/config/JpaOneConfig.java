package com.tangwh.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.tangwh.daoOne",entityManagerFactoryRef = "localConnectionFactoryBean",transactionManagerRef = "platformTransactionManager")
public class JpaOneConfig {


    @Autowired
    @Qualifier("DatasourceOne")
    DataSource dsOne;


    @Autowired
    JpaProperties jpaProperties;

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean localConnectionFactoryBean(EntityManagerFactoryBuilder builder) {
        System.out.println("出错了");
        return builder.dataSource(dsOne)
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu1")
                .packages("com.tangwh.pojo")
                .build();
    }

    // 事务
    @Bean
    PlatformTransactionManager platformTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localConnectionFactoryBean(builder).getObject());
    }
}
