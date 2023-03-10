package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * @author : PengYanDong
 * @description : feign日志控制
 * @create : 2020-07-19 13:40
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
