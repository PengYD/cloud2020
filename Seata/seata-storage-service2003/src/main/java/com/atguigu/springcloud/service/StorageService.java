package com.atguigu.springcloud.service;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-27 21:54
 * <p>
 * Copyright 2020 All rights reserved.
 **/
public interface StorageService {

    // 扣减库存
    void decrease(Long productId, Integer count);

}
