package com.atgugui.springcloud.service.imp;

import com.atgugui.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-22 15:18
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Slf4j
@EnableBinding(Source.class)//定义消息推送的管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*****************serial:"+serial);
        return null;
    }
}
