package com.atguigu.springcloud.entities;

public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(){
    }

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }

    public CommonResult(Integer code, String message, T o) {
        this.code = code;
        this.message = message;
        this.data = o;
    }
}
