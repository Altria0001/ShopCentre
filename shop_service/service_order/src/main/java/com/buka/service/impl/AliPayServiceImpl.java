package com.buka.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayTradeOrderPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeOrderPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.buka.domain.AliPayConfigClass;
import com.buka.entity.R;
import com.buka.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class AliPayServiceImpl implements AliPayService {
	@Autowired
	private AliPayConfigClass payConfigClass;

	@Override
	public R pay(String orderID, String money, String title) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		AlipayTradePagePayModel model = new AlipayTradePagePayModel();
		// 设置商户订单号
		model.setOutTradeNo(orderID);
		// 设置订单总金额
		model.setTotalAmount(money);
		// 设置订单标题
		model.setSubject("Iphone6 16G");
		// 设置PC扫码支付的方式
		model.setQrPayMode("1");


		// 设置订单包含的商品列表信息
		List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
		GoodsDetail goodsDetail0 = new GoodsDetail();
		goodsDetail0.setGoodsName("name");
		goodsDetail0.setAlipayGoodsId("20010001");
		goodsDetail0.setQuantity(1L);
		goodsDetail0.setPrice("30");
		goodsDetail0.setGoodsId("apple-01");
		goodsDetail0.setGoodsCategory("34543238");
		goodsDetail0.setCategoriesTree("124868003|126232002|126252004");
		goodsDetail0.setShowUrl("http://www.alipay.com/xxx.jpg");
		goodsDetail.add(goodsDetail0);
		model.setGoodsDetail(goodsDetail);

		// 设置商户门店编号


		request.setBizModel(model);

		AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "POST");
		String pageRedirectionData = response.getBody();
		System.out.println(pageRedirectionData);
		if (response.isSuccess()) {
			return  R.ok("调用成功");
		} else {
			return R.fail("调用失败");

		}

	}

	@Override
	public String AlipayTradeQuery(HttpServletRequest QueryRequest) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());

		// 构造请求参数以调用接口
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();

		// 设置订单支付时传入的商户订单号
		model.setOutTradeNo(QueryRequest.getParameter("OutTradeNo"));

		// 设置查询选项
		List<String> queryOptions = new ArrayList<String>();
		queryOptions.add("trade_settle_info");
		model.setQueryOptions(queryOptions);

		request.setBizModel(model);

		AlipayTradeQueryResponse response = alipayClient.execute(request);
		System.out.println(response.getBody());

		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
		return response.getBody();
	}

	private AlipayConfig getAlipayConfig(){
		String privateKey  = payConfigClass.getPrivateKey();
		String alipayPublicKey = payConfigClass.getAlipayPublicKey();
		AlipayConfig alipayConfig = new AlipayConfig();
		alipayConfig.setServerUrl(payConfigClass.getServerUrl());
		alipayConfig.setAppId(payConfigClass.getAppId());
		alipayConfig.setPrivateKey(privateKey);
		alipayConfig.setFormat("json");
		alipayConfig.setAlipayPublicKey(alipayPublicKey);
		alipayConfig.setCharset("UTF-8");
		alipayConfig.setSignType("RSA2");
		return alipayConfig;
	}
}
