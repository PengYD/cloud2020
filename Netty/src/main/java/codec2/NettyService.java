package codec2;

import codec.StudentPOJO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyService {

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
//                    .handler(null) 对应bossGroup childHandler对应workerGroup
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 向pipeline 设置处理器
                            ChannelPipeline pipeline = ch.pipeline();
                            // 需要指定对那种对象解码
                            pipeline.addLast("decoder", new ProtobufDecoder
                                    (StudentPOJO.Student.getDefaultInstance()));
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("---------------服务器 is ready");

            // 绑定一个端口并同步， 生成一个channelFuture 对象
            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("监听端口6668成功");
                    } else {
                        System.out.println("监听端口6668失败");
                    }
                }
            });
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
