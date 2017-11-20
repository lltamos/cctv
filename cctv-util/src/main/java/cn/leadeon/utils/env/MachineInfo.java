/*
 * 文 件 名:  GetMachineID.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2016-5-23,  All rights reserved  
 */
package cn.leadeon.utils.env;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <获取机器标识>
 * <功能详细描述>
 * 
 * @author  yunhaibin
 * @version  [版本号, 2016-5-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MachineInfo {
	/**
	 * MACHINEID 机器ID，取机器IP的最后一组数字
	 */
	public static final int MACHINE_ID;
	/**
	 * MACHINEIP 机器完整IP
	 */
	public static final String MACHINE_NAME;
	/**
	 * MACHINENAME 获取当前服务路径
	 */
	public static final String SERVICE_NAME;
	
	static {
		// 获取机器IP最后一段定义为机器ID
		String ip[] = null;
		String mName = null;
		String sName = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress().split("\\.");
			mName = InetAddress.getLocalHost().getHostName();
			sName = System.getProperty("user.dir");
			
			sName = sName.substring(0, sName.lastIndexOf(File.separator));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MACHINE_ID = Integer.valueOf(ip[3]);
		MACHINE_NAME = mName;
		SERVICE_NAME = sName;
	}
	
	public static void main(String[] args) {
//		System.out.println(MachineInfo.MACHINE_ID);
//		System.out.println(MachineInfo.MACHINE_NAME);
//		System.out.println(MachineInfo.SERVICE_NAME);
		
//		System.out.println(System.getProperty("os.name"));
//		System.out.println(System.getProperty("os.version"));
//		System.out.println("Java运行时环境版本:"+System.getProperty("java.version"));
//		System.out.println("Java 运行时环境供应商:"+System.getProperty("java.vendor"));
//		System.out.println("Java 供应商的URL:"+System.getProperty("java.vendor.url"));
//		System.out.println("Java 安装目录:"+System.getProperty("java.home"));
//		System.out.println("Java 虚拟机规范版本:"+System.getProperty("java.vm.specification.version"));
//		System.out.println("Java 类格式版本号:"+System.getProperty("java.class.version"));
//		System.out.println("Java 类路径："+System.getProperty("java.class.path"));
//		System.out.println("加载库时搜索的路径列表:"+System.getProperty("java.library.path"));
//		System.out.println("默认的临时文件路径:"+System.getProperty("java.io.tmpdir"));
//		System.out.println("要使用的 JIT 编译器的名称:"+System.getProperty("java.compiler"));
//		System.out.println("一个或多个扩展目录的路径:"+System.getProperty("java.ext.dirs"));
//		System.out.println("操作系统的名称:"+System.getProperty("os.name"));
//		System.out.println("操作系统的架构:"+System.getProperty("os.arch"));
//		System.out.println("操作系统的版本:"+System.getProperty("os.version"));
//		System.out.println("文件分隔符（在 UNIX 系统中是“/”）:"+System.getProperty("file.separator"));
//		System.out.println("路径分隔符（在 UNIX 系统中是“:”）:"+System.getProperty("path.separator"));
//		System.out.println("行分隔符（在 UNIX 系统中是“/n”）:"+System.getProperty("line.separator"));
//		System.out.println("用户的账户名称:"+System.getProperty("user.name"));
//		System.out.println("用户的主目录:"+System.getProperty("user.home"));
////		while(true){
////			try {
////				Thread.sleep(1000);
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//			System.out.println("用户的当前工作目录: "+System.getProperty("user.dir"));
////		}

	}
}
