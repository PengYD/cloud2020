package com.atguigu.springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-21 19:22
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
