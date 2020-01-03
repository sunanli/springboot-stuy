package com.tangwh.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.tangwh.daoTwo", entityManagerFactoryRef = "localConnectionFactoryBean2", transactionManagerRef = "platformTransactionManager2")
public class JpaTwoConfig {


    @Autowired
    @Qualifier("DatasourceTwo")
    DataSource dsTwo;


    @Autowired
    JpaProperties jpaProperties;

    @Bean
    LocalContainerEntityManagerFactoryBean localConnectionFactoryBean2(EntityManagerFactoryBuilder builder) {

        return builder.dataSource(dsTwo)
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu2")
                .packages("com.tangwh.pojo")
                .build();
    }

    // 事务
    @Bean
    PlatformTransactionManager platformTransactionManager2(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localConnectionFactoryBean2(builder).getObject());
    }
}
