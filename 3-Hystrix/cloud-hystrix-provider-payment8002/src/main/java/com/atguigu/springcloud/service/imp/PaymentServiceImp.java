package com.atguigu.springcloud.service.imp;

import org.springframework.web.bind.annotation.PathVariable;


public interface PaymentServiceImp {

    String paymentInfo_OK();

    String paymentInfo_TimeOut();

    String paymentInfo_Error();

    String paymentCircuitBreaker(Integer id);
}
