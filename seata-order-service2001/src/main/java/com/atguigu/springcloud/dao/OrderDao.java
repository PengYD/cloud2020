package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-19 23:25
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Mapper
public interface OrderDao
{
    /**
     * 新建订单
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态，从零改为1
     * @param userId
     * @param status
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
