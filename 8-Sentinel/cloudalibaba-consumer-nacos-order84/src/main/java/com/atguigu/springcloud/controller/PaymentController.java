package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-09-24 11:19
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${service-url.nacos-user-service}")
    private String URL;

    @Resource
    private RestTemplate restTemplate;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static{
        hashMap.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183"));
    }


    @GetMapping(value = "/Test/{id}")
    @SentinelResource(value = "Test" , blockHandler = "blockHandler")
    public CommonResult<Payment> Test(@PathVariable("id") Long id){
        if (id == 1) {
            throw new RuntimeException("非法参数");
        }
        CommonResult<Payment>  forObject = restTemplate.getForObject(URL + "/paymentSQL/" + id, CommonResult.class);
        return forObject;
    }


    @GetMapping(value = "/paymentSQL1/{id}")
    //@SentinelResource(value = "paymentSQL") //没有配置
    //@SentinelResource(value = "paymentSQL",fallback = "handlerFallback") //fallback只负责业务异常
//    @SentinelResource(value = "paymentSQL",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "paymentSQL",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> paymentSQL1(@PathVariable("id") Long id) {
        if (id == 99) {
            throw new RuntimeException("非法参数");
        }
        CommonResult<Payment> commonResult = new CommonResult<>();
        commonResult.setCode(Math.toIntExact(id));
        commonResult.setMessage(null);
        commonResult.setData(hashMap.get(1));
        return commonResult;
    }

    public CommonResult<Payment> handlerFallback(@PathVariable("id")Long id, Throwable e){

        CommonResult<Payment> commonResult = new CommonResult<>();
        commonResult.setCode(Math.toIntExact(id));
        commonResult.setMessage(e.getMessage());
        commonResult.setData(null);
        return commonResult;
    }

    //本例是blockHandler
    public CommonResult blockHandler(BlockException blockException) {
        Payment payment = new Payment(1l,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }

    /**
     *  测试使用openFeign
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
        return paymentService.paymentSQL(id);
    }
}
