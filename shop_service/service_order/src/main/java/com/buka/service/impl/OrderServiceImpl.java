package com.buka.service.impl;

import com.alipay.api.AlipayApiException;
import com.buka.api.GoodsApi;
import com.buka.domain.ItbukaOrder;
import com.buka.domain.dto.CreateOrderDTO;
import com.buka.entity.R;
import com.buka.service.AliPayService;
import com.buka.service.ItbukaOrderService;
import com.buka.service.OrderService;
import com.buka.util.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private AliPayService aliPayService;
	@Autowired
	private GoodsApi goodsApi;
	@Autowired
	private TokenDecode tokenDecode;
	@Autowired
	private ItbukaOrderService orderService;
	@Override
	public R createorder(CreateOrderDTO createOrderDTO) {
		Integer productNum = goodsApi.getinventory(createOrderDTO.getProductDetailId());
		if (productNum<createOrderDTO.getCount()){
			return R.fail("库存数量不足！请稍后重试，或者选择其他商品！");
		}
		Map<String, String> userInfo = tokenDecode.getUserInfo();
		String userId = userInfo.get("user_name");  //用户名
		BigDecimal productPrice = goodsApi.getPrice(createOrderDTO.getProductDetailId());
		BigDecimal amount = productPrice.multiply(new BigDecimal(createOrderDTO.getCount()));
		ItbukaOrder order = new ItbukaOrder();
		order.setType(0);
		order.setBuyerName(userId);
		order.setMoney(amount);
		order.setStatus(0);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setIsDelete(0);
		order.setProductId(createOrderDTO.getProductDetailId());
		order.setNum(createOrderDTO.getCount());
		orderService.save(order);
		try {
			aliPayService.pay(order.getId()+"",amount.toString(),"商品");
		} catch (AlipayApiException e) {
			throw new RuntimeException(e);
		}
		return R.ok();
	}

	@Override
	public String query(HttpServletRequest request) throws AlipayApiException {
		return aliPayService.AlipayTradeQuery(request);

	}
}
