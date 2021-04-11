package channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2021-04-11 23:43
 * <p>
 * Copyright 2021 All rights reserved.
 **/
public class NIOFileChannel03 {

    public static void main(String[] args) throws Exception{
        File file1 = new File("d:\\file01.text");

        File file2 = new File("d:\\file02.text");

        FileInputStream fileInputStream = new FileInputStream(file1);

        // 通道
        FileChannel channel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        FileChannel channel2 = fileOutputStream.getChannel();

        // 缓冲区
        ByteBuffer byteBuffers = ByteBuffer.allocate(1024);

        while (true){

            // 重置
            byteBuffers.clear();

            // 读取
            int read = channel1.read(byteBuffers);
            if (read == -1){
                break;
            }
            byteBuffers.flip();

            channel2.write(byteBuffers);

        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
