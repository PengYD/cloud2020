package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-15 16:15
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Service
@DefaultProperties(defaultFallback = "global_fallbackMethod")
public class PaymentService implements PaymentServiceImp {

    public String paymentInfo_OK() {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_OK";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut() {
        System.out.println("----------------线程池： "+Thread.currentThread().getName()+
                "         -paymentInfo_TimeOut:");

        //暂停3秒
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOut";
    }

    @Override
    @HystrixCommand
    public String paymentInfo_Error() {

        System.out.println("----------------线程池： "+Thread.currentThread().getName()+
                "         -paymentInfo_Error:");
        int age = 10/0;

        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOut";
    }

    public String paymentInfo_TimeOutHandler() {
        System.out.println("----------------线程池： "+Thread.currentThread().getName()+
                "         -paymentInfo_TimeOutHandler:");
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOutHandler:备用方案";
    }

    public String global_fallbackMethod() {
        System.out.println("----------------线程池： "+Thread.currentThread().getName()+
                "         -global_fallbackMethod:");
        return "线程池"+Thread.currentThread().getName()+"global_fallbackMethod:通用备用方案";
    }

    //=====服务熔断 状态 open 半开 熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
