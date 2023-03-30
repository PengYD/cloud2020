package dubborpc.provider;

import dubborpc.netty.NettyService;

public class ServerBootstrap {

    public static void main(String[] args) throws InterruptedException {
        NettyService.startServer("127.0.0.1", 7000);
    }
}
