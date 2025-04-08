package com.buka.service;

import com.alipay.api.AlipayApiException;
import com.buka.entity.R;

import javax.servlet.http.HttpServletRequest;

public interface AliPayService {
	public R pay(String orderID,String money,String title) throws AlipayApiException;
	public String AlipayTradeQuery(HttpServletRequest request) throws AlipayApiException;
}
