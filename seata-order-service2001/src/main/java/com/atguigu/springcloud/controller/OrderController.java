package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-19 23:38
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class OrderController{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    @GlobalTransactional(name = "seata-test", rollbackFor = Exception.class)
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }

    @GetMapping("/test1")
    public CommonResult test1(){

        return new CommonResult(200,"服务启动成功");
    }
}
