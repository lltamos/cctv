package cn.leadeon.comm.log.statis;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.leadeon.comm.log.Log;
import cn.leadeon.constant.ComVariable;

/**
 * 统计日志工具类
 * @author lixuming
 *
 */
public class LogStatisticTool {

	private static final String LSPACE = ComVariable.SPACE;
	
	private static final Log businesslogger = new Log("BUSINESS_RESPONSE_DTL");
	
	//private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	/**
	 * 业务处理量明细 - 统计日志
	 * @param busCode 接口编码
	 * @param respCode	响应码
	 * @param respDesc	响应描述
	 * @param params <desc>channel请求渠道；requestTime请求时间,格式yyyy-MM-dd HH:mm:ss.SSS</desc>
	 */
	public static void printBusResponse(String busCode ,String respCode, String respDesc, Map<String,String> params){
		StringBuilder s = new StringBuilder();
		//接口编码
		if(StringUtils.isNotEmpty(busCode)){
			s.append(busCode);
		}
		s.append(LSPACE);

		//请求渠道
		if (params.containsKey("channel")) {
			String channel = params.get("channel");
			if (StringUtils.isNotEmpty(channel)) {
				s.append(channel);
			}
		}
		s.append(LSPACE);
		
		//请求时间
		if (params.containsKey("requestTime")) {
			String requestTime = params.get("requestTime");
			if (StringUtils.isNotEmpty(requestTime)) {
				s.append(requestTime);
			}
		}
		s.append(LSPACE);
		
		//响应码
		if (StringUtils.isNotEmpty(respCode)) {
			s.append(respCode);
		}
		s.append(LSPACE);
		
		//响应描述
		if (StringUtils.isNotEmpty(respDesc)) {
			s.append(respDesc);
		}

		businesslogger.staticInfo(s.toString());
	}

}
