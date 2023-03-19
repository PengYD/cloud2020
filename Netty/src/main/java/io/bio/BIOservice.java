//package io.bio;
//
//import java.io.InputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author : PengYanDong
// * @description : ${description}
// * @create : 2021-04-11 22:09
// * <p>
// * Copyright 2021 All rights reserved.
// **/
//public class BIOservice {
//
//    public static void main(String[] args) throws Exception{
//
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//
//        ServerSocket serverSocket = new ServerSocket(6666);
//
//        System.out.println("服务启动.....");
//
//        while (true){
//            Socket socket = serverSocket.accept();
//            System.out.println("连接到一个客户端");
//
//            newCachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    handler(socket);
//                }
//            });
//        }
//    }
//
//    private static void handler(Socket socket) {
//
//        try{
//            System.out.println("当前线程id" + Thread.currentThread().getId()
//                    + "名称"  + Thread.currentThread().getName());
//            byte[] bytes = new byte[1024];
//            InputStream inputStream = socket.getInputStream();
//
//            while (true){
//                int read = inputStream.read(bytes);
//                if (read != -1){
//                    System.out.println(new String(bytes,0,read));
//                }else {
//                    break;
//                }
//            }
//        }catch (Exception e){
//            System.err.println(e);
//        }finally {
//            try{
//                socket.close();
//            }catch (Exception e){
//                System.err.println(e);
//            }
//        }
//    }
//}
