package channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2021-04-11 23:56
 * <p>
 * Copyright 2021 All rights reserved.
 **/
public class NIOFileChannel04 {

    public static void main(String[] args) throws Exception{

        File file1 = new File("d:\\1.png");
        File file2 = new File("d:\\2.png");

        FileInputStream fileInputStream = new FileInputStream(file1);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        FileChannel channel1 = fileInputStream.getChannel();
        FileChannel channel2 = fileOutputStream.getChannel();

        channel2.transferFrom(channel1,0,channel1.size());
//        channel2.transferTo(0, 100, channel1);

        fileInputStream.close();
        fileOutputStream.close();
    }
}
