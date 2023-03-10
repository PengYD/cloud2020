package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
public class PaymentController {

    @Resource
    private PaymentServiceImp paymentServiceImp;

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

    @GetMapping(value = "/lb/{id}")
    public CommonResult<Payment> getLb(@PathVariable String id){
        Payment payment = paymentServiceImp.getPaymentById(id);
        if (payment != null){
            return new CommonResult<>(200, servicePort+"getLb成功", payment);
        }else {
            return new CommonResult<>(500, servicePort+"getLb失败", null);
        }
    }

    @GetMapping(value = "/getAllData")
    public CommonResult<List> getAllPayment(){
        List<Payment> paymentList = paymentServiceImp.getAllPayment();
        if (paymentList != null){
            return new CommonResult<>(200, servicePort+"成功", paymentList);
        }else {
            return new CommonResult<>(500, servicePort+"失败", null);
        }
    }
}
