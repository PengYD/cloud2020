package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-18 16:55
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@SpringBootApplication
@EnableFeignClients
public class OrderMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain81.class,args);
    }

}
