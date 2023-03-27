package codec2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;


public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    // 通道就绪 触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 随机发送
        int i = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;

        if (i == 0) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.studentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(1).setName("NIHAO").build())
                    .build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.WorkerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setAge(18).setName("NIHAO").build())
                    .build();
        }

        System.out.println("myMessage : " + myMessage);
        ctx.writeAndFlush(myMessage);
    }

    // 通道有读取事件 触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        System.out.println("服务器回复的消息： " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
