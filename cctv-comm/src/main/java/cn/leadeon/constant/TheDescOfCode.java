package cn.leadeon.constant;
/**    
 * @Title: TheDescOfCode.java  
 * @Package cn.leadeon.third.common  
 * @Description: TODO(映射给定的返回码对应的提示信息)  
 * @author hexingcheng    
 * @date 2016年8月17日 上午9:46:30  
 * @version V1.0    
 */
public class TheDescOfCode {
	//支付请求接口错误码对应描述
	private static final String BP01 = "请求参数为空";
	private static final String BP02 = "msgVer校验失败";
	private static final String BP03 = "channelId校验失败";
	private static final String BP04 = "payWay校验失败";
	private static final String BP05 = "callbackUrl校验失败";
	private static final String BP06 = "notifyUrl校验失败";
	private static final String BP07 = "requestId校验失败";
	private static final String BP08 = "userId校验失败";
	private static final String BP09 = "shRule校验失败";
	private static final String BP10 = "orderId校验失败";
	private static final String BP11 = "amount校验失败";
	private static final String BP12 = "orderDate校验失败";
	private static final String BP13 = "merAcDate校验失败";
	private static final String BP14 = "period校验失败";
	private static final String BP15 = "periodUnit校验失败";
	private static final String BP16 = "merchantAbbr校验失败";
	private static final String BP17 = "productDesc校验失败";
	private static final String BP18 = "productId校验失败";
	private static final String BP19 = "productName校验失败";
	private static final String BP20 = "productNum校验失败";
	private static final String BP21 = "reserved1校验失败";
	private static final String BP22 = "reserved2校验失败";
	private static final String BP23 = "showUrl校验失败";
	private static final String BP24 = "Hmac校验失败";
	private static final String BP40 = "订单已支付或超时";
	
	//退款请求错误码对应描述
	private static final String RR01 = "msgVer校验失败";
	private static final String RR02 = "channelId校验失败";
	private static final String RR03 = "requestId校验失败";
	private static final String RR04 = "userId校验失败";
	private static final String RR05 = "orderId校验失败";
	private static final String RR06 = "amount校验失败";
	private static final String RR07 = "shRefundRule校验失败";
	private static final String RR08 = "notifyUrl校验失败";
	private static final String RR09 = "Hmac校验失败";
	private static final String RR99 = "校验参数过程发生异常";
	private static final String RF00 = "上次退款交易已完成但还未对账或对账失败，驳回此次请求";
	private static final String RF01 = "订单待退款，驳回请求";
	private static final String RR10 = "其他错误";
	
	//根据返回码获取对应的提示信息
	/**
	 * 支付请求提示信息映射
	 * @param paymentCode
	 * @return
	 */
	public static String getPaymentReturnDesc(String paymentCode){
		switch(paymentCode){
		case "BP01": return BP01;
		case "BP02":return BP02;
		case "BP03":return BP03;
		case "BP04":return BP04;
		case "BP05":return BP05;
		case "BP06":return BP06;
		case "BP07":return BP07;
		case "BP08":return BP08;
		case "BP09":return BP09;
		case "BP10":return BP10;
		case "BP11":return BP11;
		case "BP12":return BP12;
		case "BP13":return BP13;
		case "BP14":return BP14;
		case "BP15":return BP15;
		case "BP16":return BP16;
		case "BP17":return BP17;
		case "BP18":return BP18;
		case "BP19":return BP19;
		case "BP20":return BP20;
		case "BP21":return BP21;
		case "BP22":return BP22;
		case "BP23":return BP23;
		case "BP24":return BP24;
		case "BP40":return BP40;
		default: return "未知错误";
		}
	}
	
	/**
	 * 退款请求提示信息映射
	 * @param paymentCode
	 * @return
	 */
	public static String getRefundReturnDesc(String refundCode){
		switch(refundCode){
		case "RR01": return RR01;
		case "RR02": return RR02;
		case "RR03": return RR03;
		case "RR04": return RR04;
		case "RR05": return RR05;
		case "RR06": return RR06;
		case "RR07": return RR07;
		case "RR08": return RR08;
		case "RR09": return RR09;
		case "RR99": return RR99;
		case "RF00": return RF00;
		case "RF01": return RF01;
		case "RR10": return RR10;
		default: return "未知错误";
		}
	}
}
