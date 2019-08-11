package com.hzj.ribbon.consumer.ribbonconsumer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hzj.ribbon.consumer.ribbonconsumer.mapper")
public class MybatisPlusConfig {
    /*@Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }*/
}
