/*
 * 文 件 名:  TimeRegistUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.leadeon.utils.convert.DateUtil;


/**
 * 注册参数 构造 和检查工具类 使用时，必须初始化timerHost属性值，可以用Spring注入
 * @author liupengfei@weke.com
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class TimeRegistUtil {

	private static Log logger = LogFactory.getLog(TimeRegistUtil.class);
	/**
	 * 定时器的地址，后面不带斜杠，如：http://notify.weke.com 可以通过Spring注入
	 */
	public static String timerHost = "http://notify.weke.com";

	private static final String PRE_REGIST = "/regist.action";
	private static final String PRE_UNREGIST = "/unregist.action";
	/**
	 * 参数分割符
	 */
	public static final String STRING_SEPERATE1 = "-_-";
	public static final String STRING_SEPERATE2 = "=";

	/**
	 * 构造一个不循环的任务注册命令
	 * 
	 * @param businessType
	 *            业务类型 必填,如：task/pay/my
	 * @param businessId
	 *            业务ID 必填 如：getUpSms/task_610010/payOneHourJob
	 * @param executeURL
	 *            回调的URL 必填
	 * @param executeTime
	 *            第一次执行时间 必填
	 * @param email
	 *            如果发生异常，邮件发送的目标，选填，可以为null
	 * @param mobile
	 *            如果发生异常，短信发送的目标，选填，可以为null
	 * @return
	 * @throws Exception
	 */
	public static String registUrlNotLoop(String businessType,
			String businessId, String executeURL, Date executeTime,
			String email, String mobile) throws Exception {
		String registUrl = createNotLoopRegistUrl(businessType, businessId,
				executeURL, executeTime, email, mobile);
		String checkUrl = checkRegistUrl(registUrl);
		if (checkUrl != null) {
			throw new Exception(checkUrl);// "构造注册URL异常："+
		}
		String url = timerHost + PRE_REGIST + "?registUrl=" + registUrl;
		return sendRequest(url);
	}

	/**
	 * 注销一个不循环的任务
	 * 
	 * @param executeURL
	 * @param executeTime
	 * @param businessType
	 * @param email
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public static String unRegistUrlNotLoop(String businessType,
			String businessId, String executeURL) throws Exception {
		String registUrl = createNotLoopRegistUrl(businessType, businessId,
				executeURL, null, null, null);
		String checkUrl = checkUnRegistUrl(registUrl);
		if (checkUrl != null) {
			throw new Exception("构造注册URL异常：" + checkUrl);
		}
		String url = timerHost + PRE_UNREGIST + "?registUrl=" + registUrl;
		return sendRequest(url);
	}

	// 构造一个 不循环任务的registUrl
	private static String createNotLoopRegistUrl(String businessType,
			String businessId, String executeURL, Date executeTime,
			String email, String mobile) {
		StringBuilder sb = new StringBuilder("");
		// sb.append("executeURL="+executeURL);
		sb.append("businessType=" + businessType);
		sb.append(STRING_SEPERATE1 + "businessId=" + businessId);
		if (executeURL != null) {
			sb.append(STRING_SEPERATE1 + "executeURL=" + executeURL);
		}
		if (executeTime != null) {
			sb
					.append(STRING_SEPERATE1 + "executeTime="
							+ executeTime.getTime());
		}
		sb.append(STRING_SEPERATE1 + "isloop=0");
		if (StringUtils.isNotEmpty(email)) {
			sb.append(STRING_SEPERATE1 + "email=" + email);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			sb.append(STRING_SEPERATE1 + "mobile=" + mobile);
		}
		return sb.toString();
	}

	/**
	 * 构造一个循环的任务注册命令
	 * 
	 * @param businessType
	 *            业务类型 选填，可以为null
	 * @param businessId
	 *            业务ID 必填 如：getUpSms/task_610010/payOneHourJob
	 * @param executeURL
	 *            回调的URL 必填
	 * @param executeTime
	 *            第一次执行时间 必填
	 * @param loopInterval
	 *            循环相隔的时间单位：4-年,3-月,1-日,5-时,6-分,7-秒、2-星期 必填
	 * @param loopTime
	 *            循环相隔的单位时间个数 必填，例如： loopInterval是5，loopTime是2，则代表这个任务没两小时执行一次
	 * @param email
	 *            如果发生异常，邮件发送的目标，选填，可以为null
	 * @param mobile
	 *            如果发生异常，短信发送的目标，选填，可以为null
	 * @return
	 */
	public static String registUrlLoop(String businessType, String businessId,
			String executeURL, Date executeTime, Integer loopInterval,
			Integer loopTime, String email, String mobile) throws Exception {
		String registUrl = createLoopRegistUrl(businessType, businessId,
				executeURL, executeTime, loopInterval, loopTime, email, mobile);
		String checkUrl = checkRegistUrl(registUrl);
		if (checkUrl != null) {
			throw new Exception(checkUrl);// "构造注册URL异常："+
		}
		String url = timerHost + PRE_REGIST + "?registUrl=" + registUrl;
		return sendRequest(url);
	}

	/**
	 * 注销一个循环的任务
	 * 
	 * @param businessType
	 *            非空 业务类型
	 * @param businessId
	 *            非空 业务ID
	 * @param executeURL
	 *            可空 回调URL
	 * @return
	 * @throws Exception
	 */
	public static String unRegistUrlLoop(String businessType,
			String businessId, String executeURL) throws Exception {
		String registUrl = createLoopRegistUrl(businessType, businessId,
				executeURL, null, null, null, null, null);
		String checkUrl = checkUnRegistUrl(registUrl);
		if (checkUrl != null) {
			throw new Exception("构造注册URL异常：" + checkUrl);
		}
		String url = timerHost + PRE_UNREGIST + "?registUrl=" + registUrl;
		return sendRequest(url);
	}

	// 构造一个 不循环任务的registUrl
	private static String createLoopRegistUrl(String businessType,
			String businessId, String executeURL, Date executeTime,
			Integer loopInterval, Integer loopTime, String email, String mobile) {
		StringBuilder sb = new StringBuilder("");
		sb.append("businessType=" + businessType);
		sb.append(STRING_SEPERATE1 + "businessId=" + businessId);
		if (executeURL != null) {
			sb.append(STRING_SEPERATE1 + "executeURL=" + executeURL);
		}
		if (executeTime != null) {
			sb
					.append(STRING_SEPERATE1 + "executeTime="
							+ executeTime.getTime());
		}
		sb.append(STRING_SEPERATE1 + "isloop=1");
		if (loopInterval != null) {
			sb.append(STRING_SEPERATE1 + "loopInterval=" + loopInterval);
		}
		if (loopTime != null) {
			sb.append(STRING_SEPERATE1 + "loopTime=" + loopTime);
		}
		if (StringUtils.isNotEmpty(email)) {
			sb.append(STRING_SEPERATE1 + "email=" + email);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			sb.append(STRING_SEPERATE1 + "mobile=" + mobile);
		}
		return sb.toString();
	}

	/**
	 * 检查一个registUrl是否合法
	 * 
	 * @param registUrl
	 * @return 返回null则合法，否则返回不合法提示
	 */
	private static String checkRegistUrl(String registUrl) {
		try {
			if (StringUtils.isEmpty(registUrl)) {
				return new ErrorHelper("1", "缺少参数").toString();
			}
			String[] params = registUrl.split(STRING_SEPERATE1);
			if (params == null || params.length < 3) {
				return new ErrorHelper("1", "缺少参数").toString();
			}
			Map<String, String> paraMap =new HashMap<String, String>();
			for (String param : params) {
				String[] keyValue = param.split(STRING_SEPERATE2);
				if (keyValue.length != 2) {
					return new ErrorHelper("2", "参数解析异常:" + param).toString();
				}
				paraMap.put(keyValue[0], keyValue[1]);
			}
			/*-------------------解析完毕，开始检查-----------------------*/
			// 检查businessType
			if (paraMap.get("businessType") == null) {
				return new ErrorHelper("3", "不能为空:businessType").toString();
			}
			if (paraMap.get("businessId") == null) {
				return new ErrorHelper("3", "不能为空:businessId").toString();
			}
			// 检查executeTime是否合法
			if (paraMap.get("executeTime") == null) {
				return new ErrorHelper("3", "不能为空:executeTime").toString();
			} else {
				String value = paraMap.get("executeTime");
				if (StringUtils.isNumeric(value)) {
					Long time = Long.parseLong(value);
					Date excuteTime = new Date(time);
					if (new Date().after(excuteTime)) {
						return new ErrorHelper("4", "参数违法:executeTime必须是以后的时间:"
								+ DateUtil.dateFormat(excuteTime)).toString();
					}
				} else {
					return new ErrorHelper("4", "参数违法:executeTime不是数字:" + value)
							.toString();
				}
			}
			// 检查executeURL
			if (paraMap.get("executeURL") == null) {
				return new ErrorHelper("3", "不能为空:executeURL").toString();
			} else {
				String value = paraMap.get("executeURL");
				// if(value.indexOf("http://")<0){
				if (!isUrl(value)) {
					return new ErrorHelper("4", "参数违法:executeURL不是合法的http请求地址:"
							+ value).toString();
				}
			}
			// 检查loop
			if (paraMap.get("isloop") == null) {
				return new ErrorHelper("3", "不能为空:isloop").toString();
			} else {
				String value = paraMap.get("isloop");
				// 检查循环参数
				if ("1".equals(value)) {
					String loopInterval = paraMap.get("loopInterval");
					String loopTime = paraMap.get("loopTime");
					if (StringUtils.isEmpty(loopInterval)) {
						return new ErrorHelper("3", "不能为空:loopInterval")
								.toString();
					}
					if (StringUtils.isEmpty(loopTime)) {
						return new ErrorHelper("3", "不能为空:loopTime").toString();
					}
					if (StringUtils.isNumeric(loopInterval)) {
						int loopInter = Integer.parseInt(loopInterval);
						if (loopInter < 1 || loopInter > 7) {
							return new ErrorHelper("4",
									"参数违法:loopInterval必须在1-7之间:" + loopInterval)
									.toString();
						}
					} else {
						return new ErrorHelper("4",
								"参数违法:loopInterval必须是1-7之间的数字:" + loopInterval)
								.toString();
					}
					if (!StringUtils.isNumeric(loopTime)) {
						return new ErrorHelper("4", "参数违法:loopTime必须是数字:"
								+ loopTime).toString();
					}
				} else if ("0".equals(value)) {
				} else {
					return new ErrorHelper("4", "参数违法:isloop必须是0或者1:" + value)
							.toString();
				}
			}
		} catch (Exception ex) {
			return new ErrorHelper("0", "解析[" + registUrl + "]异常:"
					+ ex.toString()).toString();
		}
		return null;
	}

	/**
	 * URL检查<br>
	 */
	private static boolean isUrl(String value) {
		return value.matches("[a-zA-z]+://[^\\s]*");
	}

	/**
	 * 检查一个unregistUrl是否合法
	 * 
	 * @param unregistUrl
	 * @return 返回null则合法，否则返回不合法提示
	 */
	private static String checkUnRegistUrl(String unRegistUrl) {
		try {
			if (StringUtils.isEmpty(unRegistUrl)) {
				return new ErrorHelper("1", "缺少参数").toString();
			}
			String[] params = unRegistUrl.split(STRING_SEPERATE1);
			if (params == null || params.length < 3) {
				return new ErrorHelper("1", "缺少参数").toString();
			}
			Map<String, String> paraMap =new HashMap<String, String>();
			for (String param : params) {
				String[] keyValue = param.split(STRING_SEPERATE2);
				if (keyValue.length != 2) {
					return new ErrorHelper("2", "参数解析异常:" + param).toString();
				}
				paraMap.put(keyValue[0], keyValue[1]);
			}
			/*-------------------解析完毕，开始检查-----------------------*/
			// 检查businessType
			if (paraMap.get("businessType") == null) {
				return new ErrorHelper("3", "不能为空:businessType").toString();
			}
			if (paraMap.get("businessId") == null) {
				return new ErrorHelper("3", "不能为空:businessId").toString();
			}
		} catch (Exception ex) {
			return new ErrorHelper("0", "解析[" + unRegistUrl + "]异常:"
					+ ex.toString()).toString();
		}
		return null;
	}

	/**
	 * 取得指定URL的Web内容，加入取内容超时处理
	 * 
	 * @author zhangyaomin
	 * @param theURL
	 * @param connTimeout
	 *            连接超时时间(毫秒)
	 * @param requTimeout
	 *            获取超时时间(毫秒)
	 * @return
	 */
	private static String sendRequest(String theURL) {
		String sTotalString = "";
		URL l_url = null;
		HttpURLConnection l_connection = null;
		java.io.InputStream l_urlStream = null;
		BufferedReader l_reader = null;
		try {
			l_url = new URL(theURL);
			l_connection = (HttpURLConnection) l_url.openConnection();
			// 加入取内容超时处理
			/*
			 * l_connection.setConnectTimeout(connTimeout);
			 * l_connection.setReadTimeout(requTimeout);
			 */
			l_connection.connect();
			l_urlStream = l_connection.getInputStream();
			l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			int buffer_size = 1024;
			char[] buffer = new char[buffer_size];
			StringBuffer sb = new StringBuffer();
			int readcount = 0;
			while ((readcount = l_reader.read(buffer, 0, buffer_size)) > 0) {
				sb.append(buffer, 0, readcount);
			}
			sTotalString = sb.toString();
			l_reader.close();
			l_urlStream.close();
			l_connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("error: exception in WebUtil: " + e.toString() + ":"
					+ theURL);
		} finally {
			if (l_reader != null) {
				try {
					l_reader.close();
				} catch (Exception e) {
				}
			}
			if (l_urlStream != null) {
				try {
					l_urlStream.close();
				} catch (Exception e) {
				}
			}
			if (l_connection != null) {
				try {
					l_connection.disconnect();
				} catch (Exception e) {
				}
			}
		}
		return sTotalString;
	}

	/**
	 * 输出一个JSON字符串
	 * 
	 * @param key
	 * @param message
	 * @return
	 */
	private static String makeJsonMessage(String key, String message) {
		return new ErrorHelper(key, message).toString();
	}

	/**
	 * 内部错误工具类
	 * 
	 * @author liupengfei@weke.com
	 */
	private static class ErrorHelper {
		private String key;
		private String message;

		public ErrorHelper(String key, String message) {
			this.key = key;
			this.message = message;
		}

		@Override
		public String toString() {
			return "{\"key\":\"" + key + "\",\"message\":\"" + message + "\"}";
		}
	}

}
