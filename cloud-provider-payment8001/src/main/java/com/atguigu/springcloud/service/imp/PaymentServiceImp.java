package com.atguigu.springcloud.service.imp;

import com.atguigu.springcloud.entities.Payment;

import java.util.List;

public interface PaymentServiceImp {

    int create(Payment payment);

    Payment getPaymentById(String id);

    List<Payment> getAllPayment();
}
