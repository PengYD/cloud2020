package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
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
    private PaymentFeignService paymentFeignService;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        return paymentFeignService.create(payment);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/getAllData")
    public CommonResult<List> getAllData(){
        return paymentFeignService.getAllPayment();
    }
}
