package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.imp.PaymentServiceImp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-15 16:15
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Service
public class PaymentService implements PaymentServiceImp {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public List<Payment> getAllPayment() {
        return paymentDao.getAllPayment();
    }
}
