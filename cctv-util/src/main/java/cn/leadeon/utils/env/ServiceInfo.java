/*
 * 文 件 名:  ServiceInfo.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2016-6-20,  All rights reserved  
 
package cn.leadeon.utils.env;

import cn.leadeon.utils.crc32.Crc32Util;
import cn.leadeon.utils.env.MachineInfo;

import com.github.diamond.client.PropertiesConfiguration;
import com.github.diamond.client.PropertiesConfigurationFactoryBean;

*//**
 * <获取本服务信息>
 * <功能详细描述>
 * 
 * @author  yunhaibin
 * @version  [版本号, 2016-6-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 *//*
public class ServiceInfo {
	
	public static PropertiesConfiguration config;
	
	*//**
	 * SERVICE_INFO 服务的唯一编号
	 *//*
	public static String SERVICE_INFO;
	*//**
	 * SERVICE_CRC_ID CRC32（机器名称+服务路径）
	 *//*
	public static String SERVICE_CRC_ID;
	
	*//** 
	 * <通过机器名称（hostname）+服务路径（程序路径）进行CRC32计算得出本服务的唯一编号>
	 *//*
	public ServiceInfo() {
		String key = MachineInfo.MACHINE_NAME + MachineInfo.SERVICE_NAME;
		int crcKey = Crc32Util.crc32(key);
		SERVICE_CRC_ID = String.valueOf(crcKey);
		config = PropertiesConfigurationFactoryBean.getPropertiesConfiguration();
		SERVICE_INFO = config.getString(SERVICE_CRC_ID);
		System.out.println("SERVICE KEY："+key);
		System.out.println("SERVICE CRCKEY："+crcKey);
		System.out.println("SERVICE_INFO: " + ServiceInfo.SERVICE_INFO);
	}
	
//	static {
//		String key = MachineInfo.MACHINE_NAME + MachineInfo.SERVICE_NAME;
//		int crcKey = Crc32Util.crc32(key);
//		config = PropertiesConfigurationFactoryBean.getPropertiesConfiguration();
//		SERVICE_INFO = config.getString(String.valueOf(crcKey));
//		System.out.println(key);
//		System.out.println(crcKey);
//		System.out.println("SERVICE_INFO: " + ServiceInfo.SERVICE_INFO);
//	}
	
	
	public static void main(String[] args){
//		ServiceInfo ser = new ServiceInfo();
//		System.out.println(ServiceInfo.SERVICE_CRC_ID);
//		System.out.println(ServiceInfo.SERVICE_INFO);
		
//		String key = MachineInfo.MACHINE_NAME + MachineInfo.SERVICE_NAME;
//		String key = "m2"+"/opt/pear/pear-orderdb-server";
//		System.out.println(key);
//		int crcKey = Crc32Util.crc32(key);
//		System.out.println(crcKey);
		
//		for(int i=1;i<=4;i++){
//		}
		String key1 = "yewuyingyong01e1003p201"+"/opt/pear/v1/tomcat-order-server";
		String key2 = "yewuyingyong02e1002p202"+"/opt/pear/v1/tomcat-order-server";
		String key3 = "yewuyingyong03e1001p203"+"/opt/pear/v1/tomcat-order-server";
		String key4 = "yewuyingyong04f0104p204"+"/opt/pear/v1/tomcat-order-server";
		//yewuyingyong02e1002p202
		//yewuyingyong03e1001p203
		//yewuyingyong04f0104p204
		System.out.println(key1);
		int crcKey1 = Crc32Util.crc32(key1);
		System.out.println(crcKey1+"=1");
		
		System.out.println(key2);
		int crcKey2 = Crc32Util.crc32(key2);
		System.out.println(crcKey2+"=2");
		
		System.out.println(key3);
		int crcKey3 = Crc32Util.crc32(key3);
		System.out.println(crcKey3+"=3");
		
		System.out.println(key4);
		int crcKey4 = Crc32Util.crc32(key4);
		System.out.println(crcKey4+"=4");
		
		
		String key5 = "yewuyingyong01e1003p201"+"/opt/pear-test/v1/tomcat-order-server-test";
		
		System.out.println(key5);
		int crcKey5 = Crc32Util.crc32(key5);
		System.out.println(crcKey5+"=1");
		
	}

}
*/