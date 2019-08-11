package com.hzj.provider.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;


/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */

@Configuration
//@EnableConfigurationProperties(DataSourceProperties.class)
@ImportResource(locations = {"classpath:spring/config-mybatis.xml"})
public class DataSourceConfig {

//    @Resource
//    DataSourceProperties properties;
//
//    @Bean
//    public DataSource dataSource() {
//        //可以在此处调用相关接口获取数据库的配置信息进行 DataSource 的配置
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(properties.getUrl());
//        dataSource.setUsername(properties.getUsername());
//        dataSource.setPassword(properties.getPassword());
//        dataSource.setDriverClassName(properties.getDriverClassName());
//        return dataSource;
//    }
}

