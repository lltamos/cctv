/*
 * 文 件 名:  SzMonitorDomain.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-11-18,  All rights reserved  
 */
package cn.leadeon.comm.log;

import java.io.Serializable;

/**
 * <深圳监控中心domain>
 * <功能详细描述>
 * 
 * @author  liujie
 * @version  [版本号, 2015-11-18]
 * @see  [相关类/方法]
 * @since  [产品/中国移动2.2模块版本]
 */
public class SzMonitorDomain implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -213174289804121434L;
	private String trace; //流水号
	private String serviceName;//服务名称
	private String port;//端口号
	private String step;//步长
	private String cellNum;//电话号码
	private String prov;//身份编码
	private String retCode;//返回码
	private String name;//应用名称
	
	
	/**
	 * 获取 name
	 * @return 返回 name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 name
	 * @param 对name进行赋值
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取 trace
	 * @return 返回 trace
	 */
	public String getTrace() {
		return trace;
	}
	/**
	 * 设置 trace
	 * @param 对trace进行赋值
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}
	/**
	 * 获取 serviceName
	 * @return 返回 serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * 设置 serviceName
	 * @param 对serviceName进行赋值
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * 获取 port
	 * @return 返回 port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * 设置 port
	 * @param 对port进行赋值
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * 获取 step
	 * @return 返回 step
	 */
	public String getStep() {
		return step;
	}
	/**
	 * 设置 step
	 * @param 对step进行赋值
	 */
	public void setStep(String step) {
		this.step = step;
	}
	/**
	 * 获取 cellNum
	 * @return 返回 cellNum
	 */
	public String getCellNum() {
		return cellNum;
	}
	/**
	 * 设置 cellNum
	 * @param 对cellNum进行赋值
	 */
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	/**
	 * 获取 prov
	 * @return 返回 prov
	 */
	public String getProv() {
		return prov;
	}
	/**
	 * 设置 prov
	 * @param 对prov进行赋值
	 */
	public void setProv(String prov) {
		this.prov = prov;
	}
	/**
	 * 获取 retCode
	 * @return 返回 retCode
	 */
	public String getRetCode() {
		return retCode;
	}
	/**
	 * 设置 retCode
	 * @param 对retCode进行赋值
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	
}
