/*
 * 文 件 名:  LogCommonTool.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-1-9,  All rights reserved  
 */
package cn.leadeon.comm.log;

import java.util.Map;

import cn.leadeon.constant.ComVariable;

/**
 * <日志统计公共类>
 * <功能详细描述>
 * 
 * @author  hexingcheng
 * @version  [版本号, 2016-08-09]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LogCommonTool {
	//分隔符
	private static String lSpace = ComVariable.SPACE;

	/**
	 * 支付结果通知统计方法
	 * @param requestMap
	 * @param statisticLog
	 * @param useTime
	 */
	public static void statisticOfPayResultNotifyDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("orderId")){
			String telNo = requestMap.get("orderId");
			returnBS.append(telNo);
		}else{
			returnBS.append("#");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("requestId")){
			String requestId = requestMap.get("requestId");
			returnBS.append(requestId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("amount")){
			String amount = requestMap.get("amount");
			returnBS.append(amount);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("fee")){
			String fee = requestMap.get("fee");
			returnBS.append(fee);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("gatewayId")){
			String gatewayId = requestMap.get("gatewayId");
			returnBS.append(gatewayId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("bankAbbr")){
			String bankAbbr = requestMap.get("bankAbbr");
			returnBS.append(bankAbbr);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("mobile")){
			String mobile = requestMap.get("mobile");
			returnBS.append(mobile);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("payDate")){
			String payDate = requestMap.get("payDate");
			returnBS.append(payDate);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("status")){
			String status = requestMap.get("status");
			returnBS.append(status);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("orderDate")){
			String orderDate = requestMap.get("orderDate");
			returnBS.append(orderDate);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("payType")){
			String payType = requestMap.get("payType");
			returnBS.append(payType);
		}else{
			returnBS.append("null");
		}
		
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("payOrderId")){
			String payOrderId = requestMap.get("payOrderId");
			returnBS.append(payOrderId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("province_code")){
			String province_code = requestMap.get("province_code");
			returnBS.append(province_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("city_code")){
			String city_code = requestMap.get("city_code");
			returnBS.append(city_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("returnCode")){
			String returnCode = requestMap.get("returnCode");
			returnBS.append(returnCode);
		}else{
			returnBS.append("0");
		}
		
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}
		statisticLog.staticInfo(returnBS.toString());
		return;
	}

	/**
	 * 支付请求统计日志明细
	 * @param requestMap
	 * @param statisticLog
	 */
	public static void statisticOfPayRequestDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("gatewayId")){
			String gatewayId = requestMap.get("gatewayId");
			returnBS.append(gatewayId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("payWay")){
			String payWay = requestMap.get("payWay");
			returnBS.append(payWay);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("requestId")){
			String requestId = requestMap.get("requestId");
			returnBS.append(requestId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("orderId")){
			String orderId = requestMap.get("orderId");
			returnBS.append(orderId);
		}else{
			returnBS.append("#");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("userId")){
			String userId = requestMap.get("userId");
			returnBS.append(userId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("userToken")){
			String userToken = requestMap.get("userToken");
			returnBS.append(userToken);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("amount")){
			String amount = requestMap.get("amount");
			returnBS.append(amount);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("orderDate")){
			String orderDate = requestMap.get("orderDate");
			returnBS.append(orderDate);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("productId")){
			String productId = requestMap.get("productId");
			returnBS.append(productId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("productNum")){
			String productNum = requestMap.get("productNum");
			returnBS.append(productNum);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("clientIp")){
			String clientIp = requestMap.get("clientIp");
			returnBS.append(clientIp);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("province_code")){
			String province_code = requestMap.get("province_code");
			returnBS.append(province_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("city_code")){
			String city_code = requestMap.get("city_code");
			returnBS.append(city_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("returnCode")){
			String returnCode = requestMap.get("returnCode");
			returnBS.append(returnCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("returnMsg")){
			String returnMsg = requestMap.get("returnMsg");
			returnBS.append(returnMsg);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}
		
		statisticLog.staticInfo(returnBS.toString());
		return;
	}
	
	
	/**
	 * 退款结果通知统计方法
	 * @param requestMap
	 * @param statisticLog
	 * @param useTime
	 */
	public static void statisticOfRefundResultNotifyDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("orderId")){
			String ngSerialnumber = requestMap.get("orderId");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("#");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("requestId")){
			String requestId = requestMap.get("requestId");
			returnBS.append(requestId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("amount")){
			String amount = requestMap.get("amount");
			returnBS.append(amount);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("fee")){
			String fee = requestMap.get("fee");
			returnBS.append(fee);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("refund_time")){
			String refund_time = requestMap.get("refund_time");
			returnBS.append(refund_time);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("status")){
			String status = requestMap.get("status");
			returnBS.append(status);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("province_code")){
			String province_code = requestMap.get("province_code");
			returnBS.append(province_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("city_code")){
			String city_code = requestMap.get("city_code");
			returnBS.append(city_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("userId")){
			String userId = requestMap.get("userId");
			returnBS.append(userId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("returnCode")){
			String returnCode = requestMap.get("returnCode");
			returnBS.append(returnCode);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}

		statisticLog.staticInfo(returnBS.toString());
		return;
	}
	
	/**
	 * 流量直充结果查询统计方法
	 */
	public static void statisticOfFlowPayStateDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("OriActionDate")){
			String OriActionDate = requestMap.get("OriActionDate");
			returnBS.append(OriActionDate);
		}else{
			returnBS.append("0000-00-00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("OriTransactionID")){
			String OriTransactionID = requestMap.get("OriTransactionID");
			returnBS.append(OriTransactionID);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("IDType")){
			String IDType = requestMap.get("IDType");
			returnBS.append(IDType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("IDValue")){
			String IDValue = requestMap.get("IDValue");
			returnBS.append(IDValue);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RegionCode")){
			String RegionCode = requestMap.get("RegionCode");
			returnBS.append(RegionCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("TransIDO")){
			String TransIDO = requestMap.get("TransIDO");
			returnBS.append(TransIDO);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RspCode")){
			String RspCode = requestMap.get("RspCode");
			returnBS.append(RspCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RspDesc")){
			String RspDesc = requestMap.get("RspDesc");
			returnBS.append(RspDesc);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}

		statisticLog.staticInfo(returnBS.toString());
		return;
	}
	
	/**
	 * 流量直充资格校验统计方法
	 */
	public static void statisticOfFlowAuthDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("IDType")){
			String IDType = requestMap.get("IDType");
			returnBS.append(IDType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("IDValue")){
			String IDValue = requestMap.get("IDValue");
			returnBS.append(IDValue);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RegionCode")){
			String RegionCode = requestMap.get("RegionCode");
			returnBS.append(RegionCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ProductNo")){
			String ProductNo = requestMap.get("ProductNo");
			returnBS.append(ProductNo);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("TransIDO")){
			String TransIDO = requestMap.get("TransIDO");
			returnBS.append(TransIDO);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RspCode")){
			String RspCode = requestMap.get("RspCode");
			returnBS.append(RspCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RspInfo")){
			String RspInfo = requestMap.get("RspInfo");
			returnBS.append(RspInfo);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("OprTime")){
			String OprTime = requestMap.get("OprTime");
			returnBS.append(OprTime);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ValidFlag")){
			String ValidFlag = requestMap.get("ValidFlag");
			returnBS.append(ValidFlag);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("Reason")){
			String Reason = requestMap.get("Reason");
			returnBS.append(Reason);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}
		
		statisticLog.staticInfo(returnBS.toString());
		return;
	}
	
	/**
	 * 流量直充统计方法
	 */
	public static void statisticOfFlowPaymentDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("IDType")){
			String IDType = requestMap.get("IDType");
			returnBS.append(IDType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("IDValue")){
			String IDValue = requestMap.get("IDValue");
			returnBS.append(IDValue);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RegionCode")){
			String RegionCode = requestMap.get("RegionCode");
			returnBS.append(RegionCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("city_code")){
			String city_code = requestMap.get("city_code");
			returnBS.append(city_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("TransactionID")){
			String TransactionID = requestMap.get("TransactionID");
			returnBS.append(TransactionID);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("PayTransID")){
			String PayTransID = requestMap.get("PayTransID");
			returnBS.append(PayTransID);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ActionTime")){
			String ActionTime = requestMap.get("ActionTime");
			returnBS.append(ActionTime);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ChargeMoney")){
			String ChargeMoney = requestMap.get("ChargeMoney");
			returnBS.append(ChargeMoney);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("OrganID")){
			String OrganID = requestMap.get("OrganID");
			returnBS.append(OrganID);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("PayedType")){
			String PayedType = requestMap.get("PayedType");
			returnBS.append(PayedType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("SettleDate")){
			String SettleDate = requestMap.get("SettleDate");
			returnBS.append(SettleDate);
		}else{
			returnBS.append("0000-00-00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("OrderNo")){
			String OrderNo = requestMap.get("OrderNo");
			returnBS.append(OrderNo);
		}else{
			returnBS.append("#");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ProductNo")){
			String ProductNo = requestMap.get("ProductNo");
			returnBS.append(ProductNo);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("Payment")){
			String Payment = requestMap.get("Payment");
			returnBS.append(Payment);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("OrderCnt")){
			String OrderCnt = requestMap.get("OrderCnt");
			returnBS.append(OrderCnt);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("Commision")){
			String Commision = requestMap.get("Commision");
			returnBS.append(Commision);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RebateFee")){
			String RebateFee = requestMap.get("RebateFee");
			returnBS.append(RebateFee);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ProdDiscount")){
			String ProdDiscount = requestMap.get("ProdDiscount");
			returnBS.append(ProdDiscount);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ProductShelfNo")){
			String ProductShelfNo = requestMap.get("ProductShelfNo");
			returnBS.append(ProductShelfNo);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("PayOrganID")){
			String PayOrganID = requestMap.get("PayOrganID");
			returnBS.append(PayOrganID);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ProdDiscountRate")){
			String ProdDiscountRate = requestMap.get("ProdDiscountRate");
			returnBS.append(ProdDiscountRate);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("TransIDO")){
			String TransIDO = requestMap.get("TransIDO");
			returnBS.append(TransIDO);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("OprTime")){
			String OprTime = requestMap.get("OprTime");
			returnBS.append(OprTime);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RspCode")){
			String RspCode = requestMap.get("RspCode");
			returnBS.append(RspCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("RspDesc")){
			String RspDesc = requestMap.get("RspDesc");
			returnBS.append(RspDesc);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("0");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}
		
		statisticLog.staticInfo(returnBS.toString());
		return;
	}
	
	/**
	 * 退款请求统计方法
	 */
	public static void statisticOfRefundDtl(Map<String, String> requestMap, Log statisticLog) {
		// 统计明细
		StringBuffer returnBS = new StringBuffer();
		if(requestMap.containsKey("requestId")){
			String requestId = requestMap.get("requestId");
			returnBS.append(requestId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("userId")){
			String userId = requestMap.get("userId");
			returnBS.append(userId);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("orderId")){
			String orderId = requestMap.get("orderId");
			returnBS.append(orderId);
		}else{
			returnBS.append("#");
		}
		
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("amount")){
			String amount = requestMap.get("amount");
			returnBS.append(amount);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("refund_time")){
			String refund_time = requestMap.get("refund_time");
			returnBS.append(refund_time);
		}else{
			returnBS.append("0000-00-00 00:00:00");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("province_code")){
			String province_code = requestMap.get("province_code");
			returnBS.append(province_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("city_code")){
			String city_code = requestMap.get("city_code");
			returnBS.append(city_code);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("returnCode")){
			String returnCode = requestMap.get("returnCode");
			returnBS.append(returnCode);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("returnMsg")){
			String returnMsg = requestMap.get("returnMsg");
			returnBS.append(returnMsg);
		}else{
			returnBS.append("null");
		}
		 
		returnBS.append(lSpace);
		if(requestMap.containsKey("BizType")){
			String BizType = requestMap.get("BizType");
			returnBS.append(BizType);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("responseTime")){
			String responseTime = requestMap.get("responseTime");
			returnBS.append(responseTime);
		}else{
			returnBS.append("null");
		}
		
		returnBS.append(lSpace);
		if(requestMap.containsKey("ngSerialnumber")){
			String ngSerialnumber = requestMap.get("ngSerialnumber");
			returnBS.append(ngSerialnumber);
		}else{
			returnBS.append("null");
		}
		statisticLog.staticInfo(returnBS.toString());
		return;
	}
	
}
