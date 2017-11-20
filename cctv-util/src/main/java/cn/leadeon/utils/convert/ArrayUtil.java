/*
 * 文 件 名:  ArrayUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月3日,  All rights reserved  
 */
package cn.leadeon.utils.convert;

import java.util.ArrayList;
import java.util.List;
/**
 * 数组转换list
 * 
 * @author liudongdong
 * @version [1.0, 2014年4月3日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class ArrayUtil {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List convertArrayToList(int[] ids) {

		int i;
		List result = null;
		if (ids != null) {
			result = new ArrayList();
			for (i = 0; i < ids.length; ++i)
				result.add(new Integer(ids[i]));
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List convertArrayToList(String[] ids) {
		int i;
		List result = null;
		if (ids != null) {
			result = new ArrayList();
			for (i = 0; i < ids.length; ++i)
				result.add(new String(ids[i]));
		}
		return result;
	}
}