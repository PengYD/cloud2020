package dubborpc.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Callable;


public class NettyClientHandler
        extends ChannelInboundHandlerAdapter
        implements Callable {

    private ChannelHandlerContext context;
    private String result;
    private String para;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler.channelActive");
        context = ctx;
        super.channelActive(ctx);
    }

    // 通道有读取事件 触发
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" channelRead 被调用  ");
        result = msg.toString();
        ctx.writeAndFlush(Unpooled.copiedBuffer("asdfsadasdffaseqf", CharsetUtil.UTF_8));
        ctx.writeAndFlush("para");
        notify(); // 唤醒等待的线程
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // 被代理对象调用 发送数据给服务器。-> wait 等待被唤醒
    @Override
    public synchronized Object call() throws Exception {

        System.out.println("NettyClientHandler.call");

        context.writeAndFlush(para);
        wait(); // 等待 channelRead 方法获取到服务器的数据
        System.out.println(" call2 被调用  ");
        return result;
    }

    public void setPara(String para) {
        System.out.println(" setPara " + para);
        this.para = para;
    }
}
