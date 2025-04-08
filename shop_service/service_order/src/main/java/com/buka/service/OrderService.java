package com.buka.service;

import com.alipay.api.AlipayApiException;
import com.buka.domain.dto.CreateOrderDTO;
import com.buka.entity.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
	public R createorder(CreateOrderDTO createOrderDTO);
	public String query(HttpServletRequest request) throws AlipayApiException;
}
