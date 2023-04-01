package com.atguigu.springcloud.service.imp;

public interface PaymentServiceImp {

    String paymentInfo_OK();

    String paymentInfo_TimeOut();

    String paymentInfo_Error();

    String paymentCircuitBreaker(Integer id);
}
