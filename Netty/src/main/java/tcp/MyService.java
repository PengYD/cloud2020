package tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyService {

    public static void main(String[] args) throws InterruptedException {
        // 创建BossGroup 和workerGroup

        // 创建两个线程组创建 BossGroup 和 workerGroup
        // BossGroup只处理请求 workerGroup处理业务
        // 都是无限循环
        // 线程组 含有的子线程（NioEventLoop）的个数，默认为cpu的核数*2
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务端启动对象。配置中参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用链式编程进行设置
            // 1.设置两个线程组
            // 2.使用NioSocketChannel作为服务器的通道实现
            // 3.设置线程队列得到连接个数
            // 4.设置保持活动连接状态
            // 5.创建一个通道测试对象
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .childHandler(new MyServerInitializer());

            System.out.println("---------------服务器 is ready");

            // 绑定一个端口并同步， 生成一个channelFuture 对象
            ChannelFuture cf = bootstrap.bind(6668).sync();
            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();

        } catch (Exception e) {
            System.err.println(e);
        }finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
        }


    }
}
