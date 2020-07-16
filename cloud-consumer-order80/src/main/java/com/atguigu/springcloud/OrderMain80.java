package com.atguigu.springcloud;

import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-16 10:27
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }

}
