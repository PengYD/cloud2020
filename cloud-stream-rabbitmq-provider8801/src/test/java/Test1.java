import java.util.concurrent.TimeUnit;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-22 17:49
 * <p>
 * Copyright 2020 All rights reserved.
 **/
public class Test1 {
    public static void main(String[] args) {
        long l = System.nanoTime();
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long l1 = System.nanoTime();
        long l2 = l1 - l;
        System.out.println(l);
        System.out.println(l1);
        System.out.println(l2);

    }
}
