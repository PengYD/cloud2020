package com.atguigu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-20 00:14
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Configuration
@MapperScan({"com.atguigu.springcloud.dao"})
public class MyBatisConfig {

}
