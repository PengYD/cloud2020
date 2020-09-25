package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-09-21 17:05
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class RateLimitController {

    @GetMapping("/byResource1")
    @SentinelResource(value = "byResource1",blockHandler = "byResourceHandler")
    public CommonResult byResource(@RequestParam(value = "p1",required = false)String p1,
                        @RequestParam(value = "p2",required = false)String p2){
        return new CommonResult(200,"按资源名称测试", new Payment(2020L,"serial001"));
    }

    public CommonResult byResourceHandler(String p1,String p2,BlockException e){
        return new CommonResult(500,e.getClass().getCanonicalName()+"\t 服务不可用s");
    }

    @GetMapping("/customer")
    @SentinelResource(value = "customer",blockHandlerClass = CustomerBlockHandler.class, blockHandler = "ResourceHandler1")
    public CommonResult customer(@RequestParam(value = "p1",required = false)String p1,
                                   @RequestParam(value = "p2",required = false)String p2){
        return new CommonResult(200,"按资源名称测试", new Payment(2020L,"serial001"));
    }
}
