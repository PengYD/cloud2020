package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-22 15:57
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@EnableBinding(Sink.class)
@Component
@Slf4j
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("消费者2号，接受："+message.getPayload()+"\t port:"+serverPort);
    }
}
