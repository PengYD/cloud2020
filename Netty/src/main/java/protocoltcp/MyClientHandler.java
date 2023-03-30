package protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

public class MyClientHandler extends
        SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("循环发送");

        for (int i = 0; i < 5; i++) {
            String msg = "hello server :" + i;
            byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
            int length = msg.getBytes(StandardCharsets.UTF_8).length;

            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(bytes);

            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        int len = msg.getLen();
        byte[] content = msg.getContent();
        String message = new String(content, CharsetUtil.UTF_8);

        System.out.println("客户端接收到：" + message + "\r\n + 长度" + len);
        System.out.println("接收消息量：" + (++this.count));
    }
}
