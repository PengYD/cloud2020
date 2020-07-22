package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-21 17:48
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344 .class,args);
    }
}
