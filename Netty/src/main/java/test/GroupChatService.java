package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author pengyd
 */
public class GroupChatService {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    public GroupChatService() {
        try {

            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();

            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println("---------" + sc.getRemoteAddress() + ": 上线");

                        }

                        if(key.isReadable()) {
                            readData(key);
                        }

                        iterator.remove();
                    }
                } else {
                    System.out.println("________等待连接......");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 异常处理
        }
    }

    private void readData(SelectionKey key) throws IOException {

        SocketChannel channel = null;
        try {
            //发生 OP_READ
            //通过key 反向获取到对应channel
            channel = (SocketChannel)key.channel();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            int read = channel.read(allocate);

            if (read > 0) {
                String msg = new String(allocate.array());
                System.out.println("------ form 客户端：" + msg);

                sendInfoToClients(msg, channel);
            }

        } catch (Exception e) {

            try {
                System.out.println(channel.getRemoteAddress() + "离线");
                key.cancel();
                channel.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        //获取到该channel关联的buffer
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        channel.read(buffer);
        System.out.println("form 客户端 " + new String(buffer.array()));
    }

    private void sendInfoToClients(String msg, SocketChannel channel) throws IOException {
        System.out.println("消息转发....");
        for (SelectionKey key : selector.keys()) {
            SelectableChannel targetChannel = key.channel();

            if (targetChannel instanceof SocketChannel && targetChannel != channel) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatService groupChatService = new GroupChatService();
        groupChatService.listen();
    }
}
