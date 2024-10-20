package http;

import com.sun.jndi.toolkit.url.Uri;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter 的子类
 * HttpObject 表示客户端 服务器端 相互通讯的数据封装成 HttpObject
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        // 判断msg 是不是httpRequest 请求
        if (msg instanceof HttpRequest) {

            System.out.println("msg类型" + msg.getClass());
            System.out.println("客户端地址 " + ctx.channel().remoteAddress());

            // 过滤指定请求
            HttpRequest httpRequest = (HttpRequest) msg;
            Uri uri = new Uri(httpRequest.getUri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("无响应....图片");
            }

            // 回复信息给浏览器【http协议】
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务端", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse
                    (HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好的response返回
            ctx.writeAndFlush(response);

        }
    }
}
