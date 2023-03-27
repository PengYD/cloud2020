package groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个channel组 管理所有的channel。 GlobalEventExecutor是全局事件执行器，是一个单例。
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户加入的消息推送给所有人。遍历并发送

        channels.add(channel);
        channels.writeAndFlush("客户端[" + channel.remoteAddress() +
                "]加入聊天【" + sdf.format(new Date()) + "】\n");

    }

    // 表示channel处于活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    // 表示channel 处于不活动 离线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("------------离线了" + ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }

    // 断开连接，该方法会将channel从channels移除
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("客户端 离线了：" + channel.remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        // 遍历channels 根据不同情况处理， 转发排除自身

        channels.forEach(e-> {
            if (channel != e) {
                e.writeAndFlush("客户[" + channel.remoteAddress() +
                        "]发送消息【 " + sdf.format(new Date()) + "】： " + msg + "\n");
            } else {
                e.writeAndFlush("自己发送消息： " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
