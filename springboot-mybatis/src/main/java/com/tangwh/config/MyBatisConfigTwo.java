package com.tangwh.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;
@MapperScan(basePackages = "com.tangwh.mapperTwo",sqlSessionTemplateRef = "sqlSessionTemplate2",sqlSessionFactoryRef = "sqlSessionFactory2")
public class MyBatisConfigTwo {



    @Resource(name = "DataSourceTwo")
    DataSource dsTwo;

    @Bean
    SqlSessionFactory sqlSessionFactory2(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        try {
            bean.setDataSource(dsTwo);
            return bean.getObject();
        } catch (Exception e) {
            System.out.println("注入出错");

        }

        return null;
    }



    @Bean
    SqlSessionTemplate sqlSessionTemplate2(){
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
}
