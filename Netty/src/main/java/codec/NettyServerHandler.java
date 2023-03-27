package codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

/**
 * 1、自顶一个handler 需要继承netty 规定好继承某个handlerAdapter（规范）、
 */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {

    /**
     * 读取数据实际 （读取客户端发送的消息）
     * 1、channelHandlerContext ctx:上下文对象，含有管道pipeline 通道channel 地址
     * 2、Object msg 客户端发送的数据
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        // 读取从客户端发送的 StudentPojo.Student
//        StudentPOJO.Student student = (StudentPOJO.Student) msg;
//
//        System.out.println("客户端发送的数据： id = " + student.getId() + "名称 = " + student.getName() );
//
//        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(student.toString(), CharsetUtil.UTF_8));
//
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student student) throws Exception {

        // 读取从客户端发送的 StudentPojo.Student
        System.out.println("客户端发送的数据： id = " + student.getId() + "名称 = " + student.getName() );

        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(student.toString(), CharsetUtil.UTF_8));

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
