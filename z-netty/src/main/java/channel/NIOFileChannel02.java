package channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2021-04-11 23:37
 * <p>
 * Copyright 2021 All rights reserved.
 **/
public class NIOFileChannel02 {

    public static void main(String[] args) throws Exception{

        File file = new File("d:\\file01.text");

        FileInputStream fileInputStream = new FileInputStream(file);

        // 通道
        FileChannel channel = fileInputStream.getChannel();

        // 缓冲区
        ByteBuffer byteBuffers = ByteBuffer.allocate((int)file.length());

        // 读取
        channel.read(byteBuffers);

        // 转换

        System.out.println(new String(byteBuffers.array()));

        fileInputStream.close();
    }
}
