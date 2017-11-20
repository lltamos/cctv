/*
 * 文 件 名:  logger.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-4-17,  All rights reserved  
 */
package cn.leadeon.comm.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <日志打印>
 * 
 * @author fei.dong
 * @version [1.0, 2014-4-17]
 * @since [中国移动手机营业厅BSS系统/common模块]
 */
public class Log {
	// 业务分类打印标记定义
	public final static String USERS_SIGN = "[UERS-GATEWAY]";
	public final static String CACHE_SIGN = "[CACHE]";
	public static String CACHE_RESPONSE ="Resp-cache";
	public static String CACHE_REQUEST="Req-cache";
	
	public final static String SERVER_USERS_SIGN = "[USERS-SERVER]";


	// 流程打印标记定义
	// 客户端交互
	public final static String CLIENT_REQUEST = "Req-client";
	public final static String CLIENT_RESPONSE = "Resp-client";
	
	public final static String DB_REQUEST = "Req-db";
	public final static String DB_RESPONSE = "Resp-db";
    
	private Logger logger;

	@SuppressWarnings("rawtypes")
	public Log(Class clazz) {
		logger = LogManager.getLogger(clazz);
	}

	public Log(String name) {
		logger = LogManager.getLogger(name);
	}

	public Log(Logger logger) {
		this.logger = logger;
	}

	
	// 请求信息打印
	public void reqPrint(String busSign, String tital, String trace,
			String busCode, String data) {
		if ((data != null) && (tital != null)) {
			logger.info(getLineNum() + busSign + " " + tital + " busCode:" + busCode
					+ " trace:" + trace + " data="
					+ data.replaceAll("\\s{1,}", ""));
		}
	}
	
	
	// 请求信息打印
	public void reqPrint(String busSign, String tital, String trace,String identCode,
			String busCode, String data) {
		if ((data != null) && (tital != null)) {
			logger.info(getLineNum() + busSign + " " + tital + " busCode:" + busCode
					+ " trace:" + trace + " identCode:" + identCode + " data="
					+ data.replaceAll("\\s{1,}", ""));
		}
	}

	// 响应信息打印
	public void respPrint(String busSign, String tital, String trace,
			String busCode, Long timeUsed, String data) {
		if (data != null) {
			logger.info(getLineNum() + busSign + " " + tital + " busCode:"
					+ busCode + " trace:" + trace + " timeUsed=" + timeUsed
					+ " data=" + data);
		}
	}
	
	
	
	
	// 请求信息打印
	public void reqPrint(String busSign, String tital, String trace,String identCode,String clientIp,String sign,
			String busCode, String data) {
		if ((data != null) && (tital != null)) {
			logger.info(getLineNum() + busSign + " " + tital + " busCode:" + busCode
					+ " trace:" + trace + " identCode:" + identCode + " clientIp:" + clientIp + " sign:" + sign + " data="
					+ data.replaceAll("\\s{1,}", ""));
		}
	}


	public void info(String msg) {
		logger.info(getLineNum() + msg);
	}

	public void staticInfo(String msg) {
		logger.info(msg);
	}
	
	public void warn(String msg) {
		logger.warn(getLineNum() + msg);
	}

	public void error(String trace, String busCode, String msg, Exception e) {
		logger.error(getLineNum() + "trace:" + trace + " busCode:" + busCode + " msg:" + msg,
				e);
	}

	public void error(String trace, String busCode, String msg) {
		logger.error(getLineNum() + "trace:" + trace + " busCode:" + busCode + " msg:" + msg);
	}

	public void error(Exception e) {
		logger.error(getLineNum() + e);
	}
	
	public void error(String trace, Exception e) {
		logger.error(getLineNum() + "trace:" + trace, e);
	}

	public void error(String msg, Throwable t) {
		logger.error(getLineNum() + msg, t);
	}
	
/*	public void error(String trace, String msg, Exception e) {
		logger.error("trace:" + trace + " msg:" + msg, e);
	}*/
	
	public void error(String trace, String busCode, Exception e) {
		logger.error(getLineNum() + "trace:" + trace + " busCode:" + busCode, e);
	}

	public void error(String trace, String msg) {
		logger.error(getLineNum() + "trace:" + trace + " msg:" + msg);
	}

	public void error(String msg) {
		logger.error(getLineNum() + msg);
	}

	public void debug(String msg) {
		logger.debug(getLineNum() + msg);
	}
	
	public String getLineNum(){
		// 读取栈信息获取真正行号
		Throwable throwable = new Throwable();
		StackTraceElement[] ste = throwable.getStackTrace();
		return "[" + ste[2].getLineNumber() + "] - ";
	}
}
