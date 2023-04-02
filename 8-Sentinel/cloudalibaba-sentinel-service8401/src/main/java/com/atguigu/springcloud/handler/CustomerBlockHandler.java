package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-09-21 18:54
 * <p>
 * Copyright 2020 All rights reserved.
 **/
public class CustomerBlockHandler {

    public static CommonResult ResourceHandler1(String p1,String p2,BlockException e){
        return new CommonResult(500,"ResourceHandler1   服务不可用1");
    }

    public static CommonResult ResourceHandler2(BlockException e){
        return new CommonResult(500,"ResourceHandler1    服务不可用2");
    }
}
