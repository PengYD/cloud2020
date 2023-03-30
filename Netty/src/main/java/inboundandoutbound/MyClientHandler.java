package inboundandoutbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler.channelActive ");
//        ctx.writeAndFlush(123456L);

        // 传入非 LONG型 不会执行Long的编码器
        ctx.writeAndFlush(Unpooled.copiedBuffer("asdfsadasdffaseqf", CharsetUtil.UTF_8));

    }
}
