/*
 * 文 件 名:  XPathUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */

package cn.leadeon.utils.file;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;


/**
 * The <code>XPathUtils.java</code> encapsulates file path.
 * @author sunwm
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class XPathUtils {

    private static final String PREFIX = "/";


    /**
     * 获取文件路径
     *
     * @param absoluteFName
     * @return
     * @throws Exception 
     * @throws java.io.UnsupportedEncodingException
     *
     */
    public static String getRelativePath(String absoluteFName) throws Exception {
        URL str = Thread.currentThread().getContextClassLoader()
                .getResource("");

        if (StringUtils.isEmpty(absoluteFName)) {
        	throw new Exception("file not found!");
        }

        // 忽略开发者使用的相对路径的前缀'/'
        if (absoluteFName.startsWith(PREFIX)) {
            absoluteFName = absoluteFName.substring(1);
        }

        String fpath=null;
        try {
            fpath = URLDecoder.decode(str.getPath().concat(absoluteFName),
                    "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	try {
				throw new Exception("file not found!");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        return fpath;
    }

}
