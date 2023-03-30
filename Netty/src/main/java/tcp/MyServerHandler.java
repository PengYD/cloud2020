package tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写到缓存 刷新
        // 对需要发送的数据进行编码
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端-汪汪", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        String s = new String(buffer, Charset.forName("utf-8"));

        System.out.println("接收数据：\r\n" + s);
        System.out.println("接收消息量：" + (++this.count));

        // 回送数据
        ByteBuf byteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString() + " \r\n", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }
}
