package com.atguigu.springcloud.contorller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-16 10:31
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String servicePort;

    @GetMapping(value = "/discovery")
    public Object discovery(){
        return servicePort;
    }

    @GetMapping(value = "/getAllData")
    public CommonResult<String> getAllPayment(){
        return new CommonResult<>(200, servicePort+"成功", servicePort);
    }
}
