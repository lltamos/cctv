package cn.leadeon.comm.responsecode;

/**   
* @Title: ResponseCode.java 
*
* @Package cn.leadeon.common 
*
* @author gavin   
*
* @date 2015-1-12 下午3:55:15 
*
* @Description: 后台业务返回码
*
* @version 1.0-SNAPSHOT   
*/ 
public class ResponseCode {
	public static final String REQUEST_NULL_ERROR = "001"; //无响应报文，非超时情况
	
	public static final String RESULT_INVALID_FORMATE = "002"; //响应报文格式无效
	
	public static final String TIME_OUT_ERROR = "003"; //第三方调用超时
	
	public static final String PAY_FAILURE_CODE = "004"; //支付请求失败
	
	public static final String CACHE_OPERATION_ERROR = "005"; //缓存操作失败
	
	public static final String DB_OPERATION_ERROR = "006"; //操作数据库失败

	public static final String PAY_NO_RESULT = "007"; //退款请求对方未受理
	
	public static final String PAY_OUT_OF_RANGE = "008"; //退款请求结果不在规定范围内
	
	public static final String SYSTEM_ERROR = "009"; //系统错误
	
	public static final String BOSS_FAIL = "007"; //boss返回失败
	
	//======================================================
	//======系统成功响应类========start
	public static final String REQUEST_SUCCESS = "0000"; //成功
	
	public static final String NULL_VALUE = "0001";	//无响应数据
	
	public static final String NOT_STOCK = "0002";	//无库存
	//======系统成功响应类========end

	
	
	//======系统异常类========start
	public static final String EXCEPTION = "1001";	//系统异常
	public static final String EXCEPTION_DESC = "服务异常，请稍后重试";	//系统异常
	
	public static final String QUERY_FAIL = "1003";	//查询失败
	public static final String QUERY_FAIL_DESC = "数据查询失败，请稍后重试";	//查询失败
	
	public static final String ORDER_DISABLED = "1002";	//订单服务不可用
	
	
	//======系统异常类========end
	
	
	
	//======boss类异常（统一接口）========start
	public static final String BOSS_RESPONSE_NULL = "2001";	//boss返回空数据
	
	public static final String BOSS_RESPONSE_ERROR = "2002";	//boss响应数据格式异常
	
	public static final String BOSS_RESPONSE_TMOUT = "2003";	//boss响应超时
	
	public static final String BOSS_RESPONSE_FAIL = "2004";	//boss响应失败
	
	public static final String BOSS_DISABLED = "2005";	//boss已签退
	//======boss类异常（统一接口）========end
	
	
	
	//======支付网关类异常========start
	public static final String RECHARGE_RESPONSE_NULL = "4001";	//支付网关返回空数据
	
	public static final String RECHARGE_RESPONSE_ERROR = "4002";	//支付网关响应数据格式异常
	
	public static final String RECHARGE_RESPONSE_TMOUT = "4003";	//支付网关响应超时
	
	public static final String RECHARGE_RESPONSE_FAIL = "4004";	//支付网关响应失败
	//======支付网关类异常========end
	
	//=======积分网关类异常=============start
	public final static String RESULT_NULL_ERROR_THIRDPART = "5001";//"积分网关无响应报文";
	    
	public final static String RESULT_INVALID_FORMATE_THIRDPART = "5002"; //"积分网关响应报文无效";
	 
	public final static String RESULT_TIME_OUT_OF_ERROR = "5003";//"积分网关调用超时";
	
	public final static String RESULT_POINT_OF_FAIL = "5004";//"积分扣减失败";
	
	public final static String RESULT_POINT_RETURN_FAIL = "5005";//"积分支付回退失败";
	
	public final static String RESULT_POINT_TRADE_FAIL = "5006";//"积分交易查询失败";
	
	public final static String POINT_QUERY_OF_FAIL = "5007";//"积分查询失败";
	
	//=======积分网关类异常=============end
	
	//======请求参数非法类========start
	public static final String REQUEST_ERROR="3100";  //请求异常 请求参数不合法或不合理
	
	public static final String NOTNULL_ERROR = "3101"; //参数为空
	
	public static final String LENGTH_ERROR = "3102"; //参数长度错误
	
	public static final String ISNUMBER_ERROR = "3103"; //参数不是数字
	
	public static final String ISNOTILLEGAL_ERROR = "3104"; //参数有非法字符
	
	public static final String ENUM_ERROR = "3105"; //参数不在枚举类型内
	
	public static final String IDDOUBLE_ERROR = "3106"; //参数不是double类型
	
	public static final String IDBIGSTR_ERROR = "3107"; //检验字符串中含有特殊字符，比如["","",""...]
	
	public static final String PHONENUM_ERROR = "3108"; //电话号码非法
}
