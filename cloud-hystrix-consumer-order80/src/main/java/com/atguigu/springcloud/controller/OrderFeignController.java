package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-18 17:02
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class OrderFeignController {


    @Resource
    private PaymentFeignServiceImp paymentFeignService;

    @Value("${server.port}")
    private String servicePort;

    @GetMapping(value = "/paymentInfo")
    public String paymentInfo_OK(){
        return paymentFeignService.paymentInfo_OK();
    }

    @GetMapping(value = "/payment")
    public String paymentInfo_TimeOut(){
        return paymentFeignService.paymentInfo_TimeOut();
    }

    @GetMapping(value = "/payment/getAllData")
    public String getAllPayment(){
        return paymentFeignService.getAllPayment();
    }
}
