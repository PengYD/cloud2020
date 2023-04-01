package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-18 15:05
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serviceURL;

    @GetMapping(value = "/discovery")
    public Object create(){
        return restTemplate.getForObject(serviceURL+"/payment/discovery",String.class);
    }

    @GetMapping("/getAllData")
    public CommonResult<String> getAllData(){
        return restTemplate.getForObject(serviceURL+"/payment/getAllData", CommonResult.class);
    }
}
