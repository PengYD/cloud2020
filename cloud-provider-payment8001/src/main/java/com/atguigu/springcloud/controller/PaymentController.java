package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private DiscoveryClient discoveryClient;

    private static final Logger logger = LoggerFactory.getLogger(Slf4j.class.getName());

    @Value("${server.port}")
    private String servicePort;

    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int i = paymentServiceImp.create(payment);
        if (i > 0 ){
            return new CommonResult<>(200, servicePort+"成功", i);
        }else {
            return new CommonResult<>(500, servicePort+"失败", null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable String id){
        Payment payment = paymentServiceImp.getPaymentById(id);
        if (payment != null){
            return new CommonResult<>(200, servicePort+"成功", payment);
        }else {
            return new CommonResult<>(500, servicePort+"失败", null);
        }
    }

    @GetMapping(value = "/getAllData")
    public CommonResult<List> getAllPayment(){

//        //暂停3秒
//        try{
//            TimeUnit.SECONDS.sleep(3);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        List<Payment> paymentList = paymentServiceImp.getAllPayment();
        if (paymentList != null){
            return new CommonResult<>(200, servicePort+"成功", paymentList);
        }else {
            return new CommonResult<>(500, servicePort+"失败", null);
        }
    }

    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services){
            logger.info("*********_____"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance service : instances){
            logger.info("*********_____"+service);
        }
        return instances;
    }
}
