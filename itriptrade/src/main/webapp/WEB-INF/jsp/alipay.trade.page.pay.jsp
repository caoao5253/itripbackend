<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>付款</title>
</head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="cn.itrip.trade.config.*" %>
<%@ page import="com.alipay.api.*" %>
<%@ page import="com.alipay.api.request.*" %>
<%@ page import="com.alipay.api.domain.AlipayTradeWapPayModel" %>
<%
    //获得初始化的AlipayClient
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

    //设置请求参数
    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
    alipayRequest.setReturnUrl(AlipayConfig.return_url);
    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

    //商户订单号，商户网站订单系统中唯一订单号，必填
    String out_trade_no = new String((String.valueOf(request.getAttribute("WIDout_trade_no"))).getBytes("ISO-8859-1"), "UTF-8");
    //付款金额，必填
    String total_amount = new String((String.valueOf(request.getAttribute("WIDtotal_amount"))).getBytes("ISO-8859-1"), "UTF-8");
    //订单名称，必填
    String subject = new String((String.valueOf(request.getAttribute("WIDsubject"))).getBytes("UTF-8"), "UTF-8");
    //商品描述，可空
    String body = new String((String.valueOf(request.getAttribute("WIDbody"))).getBytes("ISO-8859-1"), "UTF-8");
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天
     * （1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     */
    String timeout_express = "1m";
    /**
     * 销售产品码 商家和支付宝签约的产品码
     */
    String product_code = "QUICK_WAP_PAY";
    AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

    alipayRequest.setBizContent(
            "{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"timeExpire\":\"" + timeout_express + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

    //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
    //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
    //		+ "\"total_amount\":\""+ total_amount +"\","
    //		+ "\"subject\":\""+ subject +"\","
    //		+ "\"body\":\""+ body +"\","
    //		+ "\"timeout_express\":\"10m\","
    //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
    //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

    //请求
    String result = alipayClient.pageExecute(alipayRequest).getBody();

    //输出
    out.println(result);
%>
<body>
</body>
</html>