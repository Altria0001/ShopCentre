package com.buka.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
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
		model.setSubject(title);

		model.setProductCode("FAST_INSTANT_TRADE_PAY");

		request.setBizModel(model);
		request.setReturnUrl(payConfigClass.getReturnUrl());  //设置支付完成之后跳转的地址
		request.setNotifyUrl(payConfigClass.getNotifyUrl());  // 支付状态发生改变之后  回调的地址

		AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "POST");
		String pageRedirectionData = response.getBody();
		System.out.println(pageRedirectionData);

		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
			// sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
			// String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
			// System.out.println(diagnosisUrl);
		}
		return R.ok();

	}

	@Override
	public String AlipayTradeQuery(HttpServletRequest QueryRequest) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());
		// 构造请求参数以调用接口
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();

		// 设置订单支付时传入的商户订单号
		model.setOutTradeNo(QueryRequest.getParameter("out_trade_no"));

		// 设置支付宝交易号
		model.setTradeNo(QueryRequest.getParameter("trade_no"));

		// 设置查询选项
		List<String> queryOptions = new ArrayList<String>();
		queryOptions.add("trade_settle_info");
		model.setQueryOptions(queryOptions);

		request.setBizModel(model);
		// 第三方代调用模式下请设置app_auth_token
		// request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

		AlipayTradeQueryResponse response = alipayClient.execute(request);
		System.out.println(response.getBody());

		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
			// sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
			// String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
			// System.out.println(diagnosisUrl);
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
