package com.atguigu.springcloud.service.imp;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentServiceImp {

    int create(Payment payment);

    Payment getPaymentById(String id);
}
