package com.buka.controller;

import com.alipay.api.AlipayApiException;
import com.buka.domain.dto.CreateOrderDTO;
import com.buka.entity.R;
import com.buka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@RequestMapping("createOrder")
	public R createOrder(CreateOrderDTO createOrderDTO){
		orderService.createorder(createOrderDTO);
		return R.ok();
	}
	@RequestMapping("notifUrl")
	public String payResult(HttpServletRequest request) throws AlipayApiException {
		String query = orderService.query(request);
		if(query.contains("\"msg\":\"Success\"")){
			return "支付成功";
		}
		return "支付失败";

	}
	@RequestMapping("getAddcar")
	public R getAddcar(){
		CreateOrderDTO createOrderDTO=orderService.getAddcar();
		return R.ok();
	}
}
