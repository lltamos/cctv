/*
 * 文 件 名:  PropertiesParserUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月16日,  All rights reserved  
 */
package cn.leadeon.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * 读取加载xml、properties文件工具类
 * 
 * @author liudongdong
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class PropertiesParserUtils {

	public static Properties prop = new Properties();

	/**
	 * 读取属性文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getProp(String fileName) {
		Properties prop = new Properties();
		ClassLoader cl = PropertiesParserUtils.class.getClassLoader();
		InputStream is = cl.getResourceAsStream(fileName);

		try {
			if (is != null)
				prop.load(is);
		} catch (IOException e) {
			System.out.println(e + "file " + fileName + " not found");
			e.printStackTrace();
		} finally {
			close(is);
		}

		return prop;
	}

	/**
	 * 读取XML文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getPropertiesByXml(String fileName) {
		File f = new File(fileName);

		// 1.文件是否存在
		if (!f.exists())
			System.out.println("File not exists");

		// 2.是否为文件
		if (!f.isFile())
			System.out.println("File not found");

		FileInputStream is = null;
		try {
			is = new FileInputStream(fileName);
			if (is != null)
				prop.loadFromXML(is);
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(is);
		}
		return prop;
	}

	/**
	 * 关闭IO流
	 * 
	 * @param is
	 */
	public static void close(InputStream is) {
		if (is != null)
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
}
