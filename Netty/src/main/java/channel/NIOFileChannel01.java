package channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2021-04-11 23:25
 * <p>
 * Copyright 2021 All rights reserved.
 **/
public class NIOFileChannel01 {

    public static void main(String[] args) throws Exception{
        String str = "hello,彭堰东";

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.text");

        // 通道
        FileChannel channel = fileOutputStream.getChannel();

        // 缓冲区
        ByteBuffer byteBuffers = ByteBuffer.allocate(1024);

        byteBuffers.put(str.getBytes());

        // 反转
        byteBuffers.flip();

        // 将buffer写入通道
        channel.write(byteBuffers);

        fileOutputStream.close();
    }
}
