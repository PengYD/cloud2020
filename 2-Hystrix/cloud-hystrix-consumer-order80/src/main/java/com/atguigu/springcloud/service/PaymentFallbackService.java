package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


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
        return "**********************服务降级 getAllPayment";
    }

    @Override
    public String paymentInfo_OK() {
        return "**********************服务降级 paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut() {
        return "**********************服务降级 paymentInfo_TimeOut";
    }

    @Override
    public String paymentInfo_Error() {
        return "**********************服务降级 paymentInfo_Error";
    }

    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        return "**********************服务/熔断降级 paymentCircuitBreaker";
    }
}
