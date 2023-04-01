package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-18 16:59
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentFeignServiceImp {

    @GetMapping(value = "/payment/getAllData")
    String getAllPayment();

    @GetMapping(value = "/payment/paymentInfo")
    String paymentInfo_OK();

    @GetMapping(value = "/payment/payment")
    String paymentInfo_TimeOut();

    @GetMapping(value = "/payment/paymentError")
    String paymentInfo_Error();

    @GetMapping(value = "/payment/paymentBreaker")
    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
