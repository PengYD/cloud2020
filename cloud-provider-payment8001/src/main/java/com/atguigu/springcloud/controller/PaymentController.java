package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentServiceImp paymentServiceImp;

    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int i = paymentServiceImp.create(payment);
        if (i > 0 ){
            return new CommonResult<>(200, "成功", i);
        }else {
            return new CommonResult<>(500, "失败", null);
        }
    }

    @GetMapping(value = "/get")
    public CommonResult<Payment> getPaymentById(@RequestBody String id){
        Payment paymentById = paymentServiceImp.getPaymentById(id);

        if (paymentById != null){
            return new CommonResult<>(200, "成功", paymentById);
        }else {
            return new CommonResult<>(500, "失败", null);
        }
    }
}
