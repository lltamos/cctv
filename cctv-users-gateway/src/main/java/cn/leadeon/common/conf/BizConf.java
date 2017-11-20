/*
 * 文 件 名:  ThirdPartyConf.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-4-24,  All rights reserved  
 */
package cn.leadeon.common.conf;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;




/**
 * <业务配置>
 * 
 * @author liujie
 * @version [1.0, 2014-4-24]
 * @since [中国移动手机营业厅BSS系统/common模块]
 */
public class BizConf implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5900034437400374535L;

	// 程序配置定义, 从配置文件读取具体配置
	private static String smsServer = null;
	private static String triggerserver = null;
	private static String nginxServer = null;
	private static String appkey = null;
	
	//二级返回码读取方式  0读取配置文件  1读取数据库
	private static String isWay = null;
	
	//实名制开发省份配置
	private static String provinces = null;
	//更新用户积分获取url配置
	private static String userSignInUrl = null;
	//爱流量http接口调用url
	private static String freeFlowUrl = null;

	static Properties conf = new Properties();

	static {
		try {
			conf.load(BizConf.class.getClassLoader().getResourceAsStream("bizConf.properties"));
			setConf(conf);
		} catch (Exception e) {
			System.out.println("load bizConf.properties error!");
		}
	}

	private static void setConf(Properties conf) {
		try {
			appkey = conf.getProperty("GuangDongAppKey");
			
			triggerserver = conf.getProperty("TriggerServer");
			
			smsServer = conf.getProperty("SMS_SERVER"); 
			
			nginxServer = conf.getProperty("NGINX_SERVER");
			
			isWay = conf.getProperty("ISWAY");
			
			provinces = conf.getProperty("PROVINCES");
			
			userSignInUrl = conf.getProperty("HTTP_USERSIGNIN_URL");
			
			freeFlowUrl = conf.getProperty("HTTP_FREEFLOW_URL");
			
			if (StringUtils.isEmpty(smsServer) || StringUtils.isEmpty(nginxServer) || StringUtils.isEmpty(isWay) 
					|| StringUtils.isEmpty(provinces) || StringUtils.isEmpty(userSignInUrl)
					|| StringUtils.isEmpty(freeFlowUrl)){
				System.out.println("bizConf.properties：Some config emlement is empty!");
				// 配置错误，程序退出
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("bizConf.properties：config error" + e);
			System.exit(0);
		}
	}

	/**
	 * 获取 smsServer
	 * @return 返回 smsServer
	 */
	public static String getSmsServer() {
		return smsServer;
	}

	/**
	 * 设置 smsServer
	 * @param 对smsServer进行赋值
	 */
	public static void setSmsServer(String smsServer) {
		BizConf.smsServer = smsServer;
	}

	/**
	 * @return the nginxServer
	 */
	public static String getNginxServer() {
		return nginxServer;
	}

	/**
	 * @param nginxServer the nginxServer to set
	 */
	public static void setNginxServer(String nginxServer) {
		BizConf.nginxServer = nginxServer;
	}

	/**
	 * 获取 isWay
	 * @return 返回 isWay
	 */
	public static String getIsWay() {
		return isWay;
	}

	/**
	 * 设置 isWay
	 * @param 对isWay进行赋值
	 */
	public static void setIsWay(String isWay) {
		BizConf.isWay = isWay;
	}

	/**
	 * 获取 provinces
	 * @return 返回 provinces
	 */
	public static String getProvinces() {
		return provinces;
	}

	/**
	 * 设置 provinces
	 * @param 对provinces进行赋值
	 */
	public static void setProvinces(String provinces) {
		BizConf.provinces = provinces;
	}

	/**
	 * @return the triggerserver
	 */
	public static String getTriggerserver() {
		return triggerserver;
	}

	/**
	 * @param triggerserver the triggerserver to set
	 */
	public static void setTriggerserver(String triggerserver) {
		BizConf.triggerserver = triggerserver;
	}

	/**
	 * @return the appkey
	 */
	public static String getAppkey() {
		return appkey;
	}

	/**
	 * @param appkey the appkey to set
	 */
	public static void setAppkey(String appkey) {
		BizConf.appkey = appkey;
	}

	public static String getUserSignInUrl() {
		return userSignInUrl;
	}

	public static void setUserSignInUrl(String userSignInUrl) {
		BizConf.userSignInUrl = userSignInUrl;
	}

	public static String getFreeFlowUrl() {
		return freeFlowUrl;
	}

	public static void setFreeFlowUrl(String freeFlowUrl) {
		BizConf.freeFlowUrl = freeFlowUrl;
	}
	
	
}
