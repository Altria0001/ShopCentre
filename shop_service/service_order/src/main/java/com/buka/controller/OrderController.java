package com.buka.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.buka.domain.dto.CreateOrderDTO;
import com.buka.entity.R;
import com.buka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

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

	private static int seed;

	public static void main(String[] args) {
		int Q = 8;
		seed = 234;
		long ans = 0;

		TreeMap<Integer, Integer> numbers = new TreeMap<>();

		for (int i = 0; i < Q; i++) {
			// 更新seed
			seed = seed ^ (seed << 15);
			seed = seed ^ (seed >>> 5); // 使用无符号右移
			seed = seed ^ (seed << 7);
			seed = seed & 0x7FFF; // 等价于 seed % (1 << 15)

			int op = (seed ^ (seed << 7) ^ (seed >>> 5)) % 3 + 1;
			int m = (seed ^ (seed << 6) ^ (seed >>> 10)) % Q + 1;
			int x = (seed ^ (seed << 5) ^ (seed << 9) ^ (seed >>> 6)) % 10 + 1;

			if (op == 1) {
				numbers.put(x, numbers.getOrDefault(x, 0) + 1);
			} else if (op == 2) {
				if (numbers.containsKey(x)) {
					int count = numbers.get(x);
					if (count <= m) {
						numbers.remove(x);
					} else {
						numbers.put(x, count - m);
					}
				}
			} else if (op == 3) {
				if (!numbers.isEmpty()) {
					ans += numbers.lastKey() - numbers.firstKey();
				}
			}
		}

		System.out.println(ans);
	}
}
