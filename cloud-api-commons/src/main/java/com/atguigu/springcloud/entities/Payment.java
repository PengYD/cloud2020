package com.atguigu.springcloud.entities;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-16 10:28
 * <p>
 * Copyright 2020 All rights reserved.
 **/
public class Payment {

    private Long id;
    private String serial;

    public Payment(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
