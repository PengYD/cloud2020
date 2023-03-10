package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-09-24 11:08
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class NacosOrder84 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrder84.class,args);
    }
}
