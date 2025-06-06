package com.buka.service.impl;

import com.alipay.api.AlipayApiException;
import com.buka.api.GoodsApi;
import com.buka.domain.ItbukaOrder;
import com.buka.domain.dto.CreateOrderDTO;
import com.buka.entity.MqConstant;
import com.buka.entity.R;
import com.buka.service.AliPayService;
import com.buka.service.ItbukaOrderService;
import com.buka.service.OrderService;
import com.buka.util.TokenDecode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private AliPayService aliPayService;
	@Autowired
	private GoodsApi goodsApi;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private TokenDecode tokenDecode;
	@Autowired
	private ItbukaOrderService orderService;
	@Autowired
	private RedisTemplate redisTemplate;
	private final static String CAR_KEY="car_key";

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
		rabbitTemplate.convertAndSend("", MqConstant.PAY_PROVIDER_DEATHQUEUE,order.getId());
		return R.ok();
	}

	@Override
	public String query(HttpServletRequest request) throws AlipayApiException {
		return aliPayService.AlipayTradeQuery(request);

	}

	@Override
	public CreateOrderDTO getAddcar() {
		Map<String, String> userInfo = tokenDecode.getUserInfo();
		String userid=userInfo.get("user_name");
		HashOperations hashOperations = redisTemplate.opsForHash();
		Set keys = hashOperations.keys(CAR_KEY+userid);
		Iterator<Long> iterator = keys.iterator();
		List<Long> productdetails=new ArrayList<>();
		while(iterator.hasNext()){
			productdetails.add(iterator.next());
		}
		List<CreateOrderDTO> createOrderDTOS=new ArrayList<>();
		CreateOrderDTO createOrderDTO = new CreateOrderDTO();
		for (Long productdetailsid:productdetails){
			Object countObj = redisTemplate.opsForHash().get(CAR_KEY + userid, productdetailsid);
			Integer count = Integer.valueOf(countObj + "");
			createOrderDTO.setProductDetailId(productdetailsid);
			createOrderDTO.setCount(count);
			createOrderDTOS.add(createOrderDTO);
			//暂时不做多个商品同时锁单
		}
		createorder(createOrderDTO);
		return createOrderDTO;

	}
}
