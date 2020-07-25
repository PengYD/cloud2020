package com.atgugui.springcloud.controller;

import com.atgugui.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-22 15:30
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@RestController
public class SendMessageProvider {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage()
    {
        return messageProvider.send();
    }
}
