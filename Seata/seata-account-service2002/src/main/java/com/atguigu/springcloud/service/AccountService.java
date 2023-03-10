package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-27 23:06
 * <p>
 * Copyright 2020 All rights reserved.
 **/

public interface AccountService {


    /**
     * 扣减账户余额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
