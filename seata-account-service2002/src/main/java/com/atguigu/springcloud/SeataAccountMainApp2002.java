package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-27 23:09
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class SeataAccountMainApp2002
{
    public static void main(String[] args)
    {
        SpringApplication.run(SeataAccountMainApp2002.class, args);
    }
}
