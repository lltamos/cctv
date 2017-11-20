/*
 * 文 件 名:  GetSerializeUUID.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;
import java.util.UUID;

/**
 * 生成uuid
 * @author zym
 * @version [1.0, 2012年3月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class GetSerializeUUID {

	public static String getUUID() {

		String s = UUID.randomUUID().toString();
		
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}
}