package io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  MappedByteBuffer 可以直接在内存（堆外内存）修改文件，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        /**
         *  参数1： 读写模式
         *   参数2： 修改起的始位置
         *    参数3： 映射的内存大小
         */
        MappedByteBuffer mappedByteBuffer = channel.map(
                FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte)'S');
        mappedByteBuffer.put(2, (byte)'S');

        randomAccessFile.close();
        System.out.println("修改成功");


    }
}
