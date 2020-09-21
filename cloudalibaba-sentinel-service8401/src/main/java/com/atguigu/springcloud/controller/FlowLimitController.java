package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-09-17 16:52
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "************testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "************testB";
    }
}
