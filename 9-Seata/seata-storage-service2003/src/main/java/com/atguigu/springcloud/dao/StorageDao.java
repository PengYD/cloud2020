package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-27 21:50
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Mapper
public interface StorageDao {

    //扣减库存信息
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}
