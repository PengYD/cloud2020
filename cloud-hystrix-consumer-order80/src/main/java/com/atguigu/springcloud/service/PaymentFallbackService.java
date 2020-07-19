package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-19 18:35
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Component
public class PaymentFallbackService implements PaymentFeignServiceImp{

    @Override
    public String getAllPayment() {
        return "**********************服务降级getAllPayment";
    }

    @Override
    public String paymentInfo_OK() {
        return "**********************服务降级paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut() {
        return "**********************服务降级paymentInfo_TimeOut";
    }
}
