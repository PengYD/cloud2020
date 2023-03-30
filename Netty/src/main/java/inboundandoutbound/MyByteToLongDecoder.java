package inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * @param ctx
     * @param byteBuf 入栈的ByteBuf
     * @param list 将解码后的数据传给下个字符
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {

        if (byteBuf.readableBytes() >= 8) {
            list.add(byteBuf.readLong());
        }
        System.out.println("服务端解码 MyByteToLongDecoder.decode");
    }
}
