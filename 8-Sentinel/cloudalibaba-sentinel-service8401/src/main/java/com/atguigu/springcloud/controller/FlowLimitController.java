package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    @SentinelResource(value = "testA", defaultFallback = "testAHandler")
    public String testA(@RequestParam(value = "p1",required = false)String p1,
                        @RequestParam(value = "p2",required = false)String p2){

        return "************testA:"+p1;
    }

    public String testAHandler(String p1, String p2, BlockException e){
        return "************testADefault:"+p2;
    }

    /**
     *  测试线程限制
     * @return
     */
    @GetMapping("/testB")
    @SentinelResource(value = "testB", defaultFallback = "testAHandler")
    public String testB(){
        try {
            Thread.sleep(3000);
        } catch (Exception e1) {
            return "************testB";
        }
        return "************testB";
    }

    @GetMapping("/testC")
    @SentinelResource(value = "testC", defaultFallback = "testAHandler")
    public String testC(){
        return "************testC";
    }

    /**
     *  慢调用降级测试
     * @return
     */
    @GetMapping("/testD")
    @SentinelResource(value = "testD", defaultFallback = "testAHandler")
    public String testD(){
        try {
            Thread.sleep(1000);
        } catch (Exception e1) {
            return "************************testD";
        }
        return "************testD";
    }    /**


    /**
     *  异常比例测试
     * @return
     */
    @GetMapping("/testE")
    @SentinelResource(value = "testE", defaultFallback = "testAHandler")
    public String testE(){
        double random = Math.random();
        if (random > 0.5) {
            throw new RuntimeException();
        }
        return "************testE";
    }
}
