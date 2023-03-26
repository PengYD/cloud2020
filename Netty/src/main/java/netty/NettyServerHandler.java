package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 1、自顶一个handler 需要继承netty 规定好继承某个handlerAdapter（规范）、
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据实际 （读取客户端发送的消息）
     * 1、channelHandlerContext ctx:上下文对象，含有管道pipeline 通道channel 地址
     * 2、Object msg 客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 异步流程 -> 例如耗时较长的任务，需要异步执行
        // NioEventLoop 的 taskQueue中

        // 解决方案2 用户自定义定时任务 -> 该任务提交到scheduleTaskQueue
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端 喵~", CharsetUtil.UTF_8));
                    System.out.println("go on---");
                }catch (Exception e) {

                }
            }
        }, 5, TimeUnit.SECONDS);

        // 解决方案1 用户程序自定义的普通任务 多个均在同一线程
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端 喵~", CharsetUtil.UTF_8));
                    System.out.println("go on---");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }finally {

                }
             }
        });
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端 喵~", CharsetUtil.UTF_8));
                    System.out.println("go on---");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }finally {

                }
            }
        });


        // 阻塞情况
//        Thread.sleep(10 * 1000);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端 喵~", CharsetUtil.UTF_8));
//        System.out.println("go on---");

        // 正常流程
//        System.out.println("服务器读取的线程： " + Thread.currentThread().getName());
//        System.out.println("Server ctx = " + ctx);
//
//        Channel channel = ctx.channel();
//        ChannelPipeline pipeline = ctx.pipeline();
//
//        // 将msg转换为byteBuf （netty提供的）
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端msg：" + buf.toString(StandardCharsets.UTF_8));
//        System.out.println("客户端地址：" + channel.remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写到缓存 刷新
        // 对需要发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端-汪汪", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}
