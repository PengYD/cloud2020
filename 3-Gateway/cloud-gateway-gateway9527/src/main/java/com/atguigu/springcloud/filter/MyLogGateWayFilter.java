//package com.atguigu.springcloud.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Date;
//
///**
// * @author : PengYanDong
// * @description : ${description}
// * @create : 2020-07-21 16:09
// * <p>
// * Copyright 2020 All rights reserved.
// **/
//@Component
//@Slf4j
//public class MyLogGateWayFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("****** come in MyLogGateWayFilter: " + new Date());
//
//        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
//        if(uname == null) {
//            log.info("*****用户名为null，非法用户，o(╥﹏╥)o");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
