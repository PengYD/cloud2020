package io.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2021-04-11 22:50
 * <p>
 * Copyright 2021 All rights reserved.
 **/
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);

        for (int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put(i*2);
        }

        // 读写切换
//        public final Buffer flip() {
//            limit = position;
//            position = 0;
//            mark = -1;
//            return this;
//        }
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            // intBuffer.get() 存在索引  每次调用加1
            System.out.println(intBuffer.get());
        }
    }
}
