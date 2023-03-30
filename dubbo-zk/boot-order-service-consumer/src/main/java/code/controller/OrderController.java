package code.controller;

import java.util.List;

import bean.UserAddress;
import code.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@GetMapping("/initOrder")
	public List<UserAddress> initOrder(@RequestParam("uid")String userId) throws InterruptedException {

		return orderService.initOrder(userId);

	}

}
