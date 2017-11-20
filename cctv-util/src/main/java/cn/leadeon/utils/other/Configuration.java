/*
 * 文 件 名:  Configuration.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;

/**
 * 
 * @author sunwm
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class Configuration {
	public static String GetConfig(String theConfig) {
		String return_value = null;
		return_value = Resources.getString(theConfig);
		return return_value;
	}

	public static boolean ReloadConfig() {
		return Resources.reload();
	}

	public static boolean getBooleanValue(String theConfig) {
		boolean return_value = false;
		try {
			return_value = Boolean.parseBoolean(Resources.getString(theConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_value;
	}
}