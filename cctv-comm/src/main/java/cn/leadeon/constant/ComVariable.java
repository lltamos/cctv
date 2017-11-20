package cn.leadeon.constant;

/**
 * 公共变量
 * @author lixuming
 *
 */
public class ComVariable {
	
	/*=========================内部提示信息start=================*/
	public final static String RESULT_SUCCESS = "SUCCESS";
	
	public final static String SYSTEM_ERROR_DES = "系统错误";
	
    public final static String RESULT_NULL_ERROR_BOSS = "统一接口响应无响应报文";
	
    public final static String RESULT_INVALID_FORMATE_BOSS = "统一接口返回报文无效";
    
    public final static String RESULT_TIME_OUT_ERROR_BOSS = "统一接口平台调用超时";
    
    public final static String RESULT_TIME_OUT_ERROR = "支付网关调用超时";
    
    public final static String RESULT_NULL_ERROR_THIRD = "支付网关无响应报文";
    
    public final static String RESULT_INVALID_FORMATE_THIRD = "支付网关响应报文无效";
    
    public final static String RESULT_NULL_ERROR_THIRDPART = "积分网关无响应报文";
    
    public final static String RESULT_INVALID_FORMATE_THIRDPART = "积分网关响应报文无效";
 
    public final static String RESULT_TIME_OUT_OF_ERROR = "积分网关调用超时";
    
    public final static String RESULT_POINT_OF_FAIL = "积分支付失败";
    
    public final static String RESULT_POINT_RETURN_FAIL = "积分支付回退失败";

    public final static String REFRESH_REDIS_ERROR = "缓存操作失败";
    
    public final static String SING_SUCCESS = "签退成功";
    
    public final static String SIGN_SUCCESS = "签到成功";
    
    public final static String SING_ERROR = "签退失败";
    
    public final static String SIGN_ERROR = "签到失败";
    
    public final static String SIGN_1 = "该省签到";
    
    public final static String SIGN_2 = "该省签退";
    
    public final static String SIGN_FLAG_ERROR = "签到/签退标错误";
    
    public final static String REFUND_BACK_SUCCESS = "退款结果通知成功"; 
    
    public final static String REFUND_BACK_ERROR = "未知退款结果";
    
    public final static String REFUND_BACK_NO = "退款请求未受理";
    
    public final static String REFUND_BACK_FALT = "退款通知失败";
    
    public final static String PAY_RESULT_NOTIFY_SUCCESS = "充值结果通知成功";
    
    public final static String PAY_RESULT_NOTIFY_ERROR = "未知支付结果";
    
    public final static String PAY_RESULT_NOTIFY_FALT = "充值接口通知失败";
    
    public final static String DB_OPERATION_ERROR = "数据库操作失败";
    
    public final static String PAY_REQUEST_ERROR = "支付请求失败";
    
    public final static String PAY_REQUEST_SUCCESS = "支付请求成功";
   
    public final static String DB_ORDER_ERROR = "订单状态非法";
    
    public final static String RESULT_NULL_ERROR_CARD = "卡券中心无响应报文";
    
    public final static String RESULT_INVALID_FORMATE_CARD = "卡券中心响应报文无效";
 
    public final static String RESULT_TIME_OUT_OF_CARD = "卡券中心调用超时";
	/*=========================内部提示信息end=================*/
	
    //时间格式化字符
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; 

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    // http响应ok
    public final static int HTTP_RESPONSE_STATUS_OK = 200;
    
    /* 单位换算类变量定义 */ 
    public final static int KILO = 1024;
    
    //token令牌
    public final static String TOKEN = "token"; //
    
    public final static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss:SSS"; //日期格式
    
    public final static String DEF_PROVINCE_CODE = "9999";
    public final static String SPACE = "|#$";
    public final static int PHONE_LEN = 11;//TEL_NO位数
    
    /**
     * 1小时3600秒
     */
	public static long HOUR_SEC_NUMBER = 3600L;

    /**
     * 1 天 24 * 3600 秒
     */
	public static long DAY_SEC_NUMBER = HOUR_SEC_NUMBER * 24;
	
    // 正确编码返回提示信息
    public final static String RSP_DESC_SUCCESS = "SUCCES";
    
    // 默认错误编码返回提示信息
    public final static String RSP_DESC_DEFAULT_ERR = "对不起，您的请求暂时无法受理";
    
    //Trace名称, 唯一标志名称，即流水号
    public final static String TRACE = "Trace";
    
    // 编码格式
    public final static String ENCODING_UTF8 = "UTF-8";
    public final static String CONTENTTYPE_JSON = "application/json;charset=UTF-8";
}

