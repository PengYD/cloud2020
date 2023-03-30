package code;

import java.io.IOException;
import java.util.List;

import bean.UserAddress;
import code.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApplication {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
		
		OrderService orderService = applicationContext.getBean(OrderService.class);

		List<UserAddress> userAddresses = orderService.initOrder("1");

		for (UserAddress userAddress : userAddresses) {

			System.out.println(userAddress);
		}
	
		System.out.println("调用完成....");
		System.in.read();
	}

}
