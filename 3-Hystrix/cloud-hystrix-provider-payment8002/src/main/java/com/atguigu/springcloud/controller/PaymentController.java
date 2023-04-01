package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-16 10:31
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class PaymentController {

    @Resource
    private PaymentServiceImp paymentServiceImp;


    private static final Logger logger = LoggerFactory.getLogger(Slf4j.class.getName());

    @Value("${server.port}")
    private String servicePort;

    @GetMapping(value = "/paymentInfo")
    public String paymentInfo_OK(){
        logger.info("********************进入paymentInfo_OK方法，端口"+servicePort);
        return paymentServiceImp.paymentInfo_OK();
    }

    @GetMapping(value = "/payment")
    public String paymentInfo_TimeOut(){
        logger.info("********************进入paymentInfo_TimeOut方法，端口"+servicePort);
        return paymentServiceImp.paymentInfo_TimeOut();
    }

    @GetMapping(value = "/paymentError")
    public String paymentInfo_Error(){
        logger.info("********************进入paymentInfo_TimeOut方法，端口"+servicePort);
        return paymentServiceImp.paymentInfo_Error();
    }
    @GetMapping(value = "/payment/paymentBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return paymentServiceImp.paymentCircuitBreaker(id);
    }
}
