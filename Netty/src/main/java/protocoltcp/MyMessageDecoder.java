package protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("---------------MyMessageDecoder.decode");

        // 将得到的二进制字节码 转为 MessageProtocol
        int i = in.readInt();
        byte[] bytes = new byte[i];
        in.readBytes(bytes);

        // 封装成 MessageProtocol
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(i);
        messageProtocol.setContent(bytes);
        out.add(messageProtocol);
    }
}
