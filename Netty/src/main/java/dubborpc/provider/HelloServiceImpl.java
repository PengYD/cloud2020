package dubborpc.provider;

import dubborpc.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String mes) {
        System.out.println("收到消息 ： " + mes);

        if (mes != null) {
            return "收到消息 ： " + mes;
        } else {
            return "收到消息 ： 未收到消息";
        }
    }
}
