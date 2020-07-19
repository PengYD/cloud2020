package myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-18 16:03
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){

        return new RandomRule();
    }
}
