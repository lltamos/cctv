/*
 * 文 件 名:  SzMonitorLogTool.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-11-18,  All rights reserved  
 */
package cn.leadeon.comm.log.consumelog;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.leadeon.comm.log.Log;
import cn.leadeon.comm.log.SzMonitorDomain;

/**
 * <深圳监控中心日志记录 biz日志>
 * <功能详细描述>
 * 
 * @author  liujie
 * @version  [版本号, 2015-11-18]
 * @see  [相关类/方法]
 * @since  [产品/中国移动2.2模块版本]
 */
public class SzMonitorLogTool {

	private static Log logger_3 = new Log("APPID_3");
//	private static Log logger_4 = new Log("APPID_4");
//	private static Log logger_5 = new Log("APPID_5");
//	private static Log logger_6 = new Log("APPID_6");
	private static String SEPRATOR = "|";
	private static final String MACHINEIP;
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final String FOUR = "4";
	private static final String FIVE = "5";
	private static final String SIX = "6";
	private static final String SUCCESS = "000000";
	private static final String APPID_DEVICE = "dubbo";
	private static final String APPID_GL = "<";
	private static final String APPID_LT = ">";
	static{
		// 获取机器IP最后一段定义为机器ID
		String ip[] = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress().split("\\.");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		MACHINEIP = ip[3];
		if(MACHINEIP == null)
			System.exit(0);
	}
	
	/**
	 * <交易请求接收>
	 * <功能详细描述>
	 * @author  liujie
	 * @param requestMap
	 * @see [类、类#方法、类#成员] 
	 */
	public static void businessReqReciveLog(SzMonitorDomain domain){
		StringBuffer buf = new StringBuffer(APPID_GL);
		/*日志记录类别*/
		buf.append(FOUR).append(SEPRATOR);
		/*日志时间*/
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateStr = sdf.format(date);
		buf.append(dateStr).append(SEPRATOR);
		/* 交易标识 */
		String trace = domain.getTrace();
		buf.append(trace).append(SEPRATOR);   
		/*应用标识*/
		String serviceName = domain.getServiceName();
		buf.append(MACHINEIP).append(APPID_DEVICE).append(domain.getPort()).append(SEPRATOR);
		/*进程标识*/
		String name = ManagementFactory.getRuntimeMXBean().getName();    
		/*get pid */   
		String pid = name.split("@")[0]; 
		buf.append(pid).append(SEPRATOR);
		/*消息标识*/
		buf.append(domain.getStep()).append(SEPRATOR);   
		/*请求代码分类*/
		buf.append(TWO).append(SEPRATOR);
		/*请求代码*/
		buf.append(serviceName+"-biz").append(SEPRATOR);
		/*服务类型*/
		buf.append(ONE).append(SEPRATOR);   
		/*账号*/
		buf.append(domain.getCellNum()).append(SEPRATOR);   
		/*受理标识*/
		buf.append(trace).append(domain.getProv()).append(APPID_LT);   
		logger_3.staticInfo(buf.toString());
	}
	
	/**
	 * <交易应答发送>
	 * <功能详细描述>
	 * @author  liujie
	 * @param requestMap
	 * @see [类、类#方法、类#成员]
	 */
	public static void businessSendLog(SzMonitorDomain domain){
		StringBuffer buf = new StringBuffer(APPID_GL);
		/*日志记录类别*/
		buf.append(FIVE).append(SEPRATOR);
		/*日志时间*/
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateStr = sdf.format(date);
		buf.append(dateStr).append(SEPRATOR);
		/* 交易标识 */
		buf.append(domain.getTrace()).append(SEPRATOR);   
		/*应用标识*/
		buf.append(MACHINEIP).append(APPID_DEVICE).append(domain.getPort()).append(SEPRATOR);
		/*进程标识*/
		String name = ManagementFactory.getRuntimeMXBean().getName();    
		/*get pid */   
		String pid = name.split("@")[0]; 
		buf.append(pid).append(SEPRATOR);
		/*消息标识*/
		buf.append(domain.getStep()).append(SEPRATOR);   
		/*处理结果*/
		String isSuc = domain.getRetCode();
		String flag = ZERO;
		if (!SUCCESS.equals(isSuc)) {
			flag = ONE;
		}
		buf.append(flag).append(SEPRATOR);   
		/*错误代码*/
		buf.append(isSuc).append(SEPRATOR);
		/*嵌套发起记录个数*/
		buf.append(ONE).append(SEPRATOR);   
		/*系统明细日志个数*/
		buf.append(ZERO).append(SEPRATOR);
		/*报文明细日志个数*/
		buf.append(ZERO).append(APPID_LT);
		logger_3.staticInfo(buf.toString());
	}
	
	/**
	 * <交易请求发送>
	 * <功能详细描述>
	 * @author  liujie
	 * @param requestMap
	 * @see [类、类#方法、类#成员]
	 */
	public static void requestSendLog(SzMonitorDomain domain) {
		StringBuffer buf_1 = new StringBuffer(APPID_GL);
		/*日志记录类别*/
		buf_1.append(THREE).append(SEPRATOR);
		/*日志时间*/
		Date date_1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateStr_1 = sdf.format(date_1);
		buf_1.append(dateStr_1).append(SEPRATOR);
		/* 交易标识 */
		buf_1.append(domain.getTrace()).append(SEPRATOR);   
		/*应用标识*/
		String serviceName = domain.getServiceName();
		buf_1.append(MACHINEIP).append(APPID_DEVICE).append(domain.getPort()).append(SEPRATOR);
		/*进程标识*/
		String name = ManagementFactory.getRuntimeMXBean().getName();    
		/*get pid */   
		String pid = name.split("@")[0]; 
		buf_1.append(pid).append(SEPRATOR);
		/*功能标识分类*/
		buf_1.append(ONE).append(SEPRATOR);
		/*功能标识*/
		buf_1.append(domain.getName()).append(SEPRATOR);
		/*消息标识*/
		buf_1.append(domain.getStep()).append(".").append(ONE).append(SEPRATOR);
		/*请求代码分类*/
		buf_1.append(TWO).append(SEPRATOR);
		/*请求代码*/
		buf_1.append(serviceName+"-biz").append(APPID_LT);
		logger_3.staticInfo(buf_1.toString());
	}
	
	/**
	 * <交易应答接收>
	 * <功能详细描述>
	 * @author  liujie
	 * @param requestMap
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public static void requestReciveLog(SzMonitorDomain domain){
		StringBuffer buf_1 = new StringBuffer(APPID_GL);
		/*日志记录类别*/
		buf_1.append(SIX).append(SEPRATOR);
		/*日志时间*/
		Date date_1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateStr_1 = sdf.format(date_1);
		buf_1.append(dateStr_1).append(SEPRATOR);
		/* 交易标识 */
		buf_1.append(domain.getTrace()).append(SEPRATOR);   
		/*应用标识*/
		buf_1.append(MACHINEIP).append(APPID_DEVICE).append(domain.getPort()).append(SEPRATOR);
		/*进程标识*/
		String name = ManagementFactory.getRuntimeMXBean().getName();    
		/*get pid */   
		String pid = name.split("@")[0];
		buf_1.append(pid).append(SEPRATOR);
		/*消息标识*/
		buf_1.append(TWO).append(".").append(ONE).append(SEPRATOR);
		/*系统处理结果*/
		String isSuc = domain.getRetCode();
		String flag = ZERO;
		if (!SUCCESS.equals(isSuc)) {
			flag = ONE;
		}
		buf_1.append(flag).append(SEPRATOR);   
		/*错误代码*/ 
		buf_1.append(isSuc).append(SEPRATOR);
		/*特殊处理标志*/
		buf_1.append(ZERO).append(APPID_LT);
		logger_3.staticInfo(buf_1.toString());
	}
	
	/**
	 * <获取请求响应报文中的字段值>
	 * <功能详细描述>
	 * @author  liujie
	 * @param json
	 * @param logKey
	 * @param bodyKey
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
//	private static String returnLogAttr(String json, String logKey, String bodyKey, String bool) throws Exception{
//		StringBuffer logAttr = new StringBuffer();
//		// 解析报文
//		HashMap<String, Object> headMap = JSON.parseObject(json,
//				new TypeReference<HashMap<String, Object>>() {
//				});
//		Object body = headMap.get(bodyKey);
//		HashMap<String, Object> bodyMap = null;
//		if(body != null){
//			bodyMap = JSON.parseObject(body.toString(),
//					new TypeReference<HashMap<String, Object>>() {
//					});
//		}
//		Object keyValue;
//		if (ZERO.equals(bool)) {
//			keyValue = bodyMap.get(logKey);
//		}else {
//			keyValue = headMap.get(logKey);
//		}
//		logAttr.append(keyValue.toString());
//		return logAttr.toString();
//	}
	
	/**
	 * <监控中心对象>
	 * <功能详细描述>
	 * @author  liujie
	 * @param trace 流水号
	 * @param serviceName 服务名称
	 * @param port 端口号
	 * @param code 返回码
	 * @param step 步长
	 * @param cellNum 电话号码
	 * @param prov 省份编码
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static SzMonitorDomain getDomain(String trace, String serviceName, String port, String code,String step, String cellNum, String prov){
		SzMonitorDomain obj = new SzMonitorDomain();
		obj.setTrace(trace);
		obj.setPort(port);
		obj.setServiceName(serviceName);
		obj.setRetCode(code);
		obj.setCellNum(cellNum);
		obj.setProv(prov);
		obj.setStep(step);
		obj.setName(serviceName);
		return obj;
	}
}
