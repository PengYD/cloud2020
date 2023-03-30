package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-16 10:27
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class TestOrderMain7776 {
    public static void main(String[] args) {
        SpringApplication.run(TestOrderMain7776.class,args);
    }
}
