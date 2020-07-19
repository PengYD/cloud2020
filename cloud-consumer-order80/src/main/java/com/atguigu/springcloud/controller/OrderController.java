package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-16 10:31
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class OrderController {

//    public static final String PAYMENT_URL ="http://localhost:8001";

    public static final String PAYMENT_URL ="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable String id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/payment/getAllData")
    public CommonResult<List> getAllData(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getAllData", CommonResult.class);
    }
}
