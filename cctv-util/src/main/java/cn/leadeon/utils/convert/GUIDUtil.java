/*
 * 文 件 名:  GUIDUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月3日,  All rights reserved  
 */
package cn.leadeon.utils.convert;

import java.util.UUID;

/**
 * 
 *  随机生成uid
 * 
 * @author  liudongdong
 * @version  [1.0, 2014年6月6日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class GUIDUtil {

	/**
	 * 产生一个GUID
	 * 
	 * @return
	 */
	public static String newGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

}
