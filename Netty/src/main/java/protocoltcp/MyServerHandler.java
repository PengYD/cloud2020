package protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

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
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        int len = msg.getLen();
        byte[] content = msg.getContent();
        String message = new String(content, CharsetUtil.UTF_8);

        System.out.println("接收数据：" + message + "\r\n + 长度" + len);
        System.out.println("接收消息量：" + (++this.count));

        // 回送数据
        String msg1 = UUID.randomUUID() + " \r\n";

        int len1 = msg1.getBytes(StandardCharsets.UTF_8).length;
        byte[] content1 = msg1.getBytes();

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len1);
        messageProtocol.setContent(content1);

        ctx.writeAndFlush(messageProtocol);
    }
}
