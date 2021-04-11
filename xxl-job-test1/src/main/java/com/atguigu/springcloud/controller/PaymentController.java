package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(Slf4j.class.getName());

    @Resource
    private PaymentServiceImp paymentServiceImp;

    @GetMapping(value = "/getAllData")
    public CommonResult<List> getAllPayment(){

        List<Payment> paymentList = paymentServiceImp.getAllPayment();
        if (paymentList != null){
            return new CommonResult<>(200, "成功", paymentList);
        }else {
            return new CommonResult<>(500, "失败", null);
        }
    }
}
