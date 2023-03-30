package dubborpc.customer;

import dubborpc.netty.NettyClient;
import dubborpc.publicinterface.HelloService;

public class ClientBootstrap {

    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        NettyClient customer = new NettyClient();

        HelloService service = (HelloService)customer.getBean(HelloService.class, providerName);

        // 通过代理对象 调用
        String hello = service.hello("你好啊，测试一号。");
        String hello1 = service.hello(null);
        System.out.println("调用结果：" + hello);
    }
}
