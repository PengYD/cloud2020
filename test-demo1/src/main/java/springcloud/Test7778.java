package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-18 14:27
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Test7778 {

    public static void main(String[] args) {
        SpringApplication.run(Test7778.class,args);
    }
}
