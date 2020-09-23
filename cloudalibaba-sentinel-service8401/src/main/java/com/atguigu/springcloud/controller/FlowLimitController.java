package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @SentinelResource(value = "testA",blockHandler = "testAHandler")
    public String testA(@RequestParam(value = "p1",required = false)String p1,
                        @RequestParam(value = "p2",required = false)String p2){

        return "************testA:"+p1;
    }

    public String testAHandler(String p1, String p2, BlockException e){
        return "************testADefault:"+p2;
    }

    @GetMapping("/testB")
    public String testB(){
        return "************testB";
    }
}
