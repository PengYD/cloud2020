package echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;


@ChannelHandler.Sharable
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);

    private int count;

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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("channelRead 线程 " + Thread.currentThread().getName());

        group.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                ByteBuf buf = (ByteBuf)msg;
                byte[] bytes = new byte[buf.readableBytes()];
                buf.readBytes(bytes);
                Thread.sleep(10 * 1000);
                System.out.println("call 线程 " + Thread.currentThread().getName());
                System.out.println("-----------客户" + new String(bytes, StandardCharsets.UTF_8));

                ctx.writeAndFlush(Unpooled.copiedBuffer("你好", CharsetUtil.UTF_8));
                return null;
            }
        });

    }
}
