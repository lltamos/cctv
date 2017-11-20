/*
 * 文 件 名:  ExceptionUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * 
 * 打印异常栈信息
 * @author  liudongdong
 * @version  [1.0, 2014年6月4日]
 */
public final class ExceptionUtils {
    public static String formatStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        try {
            PrintWriter p = new PrintWriter(sw);
            t.printStackTrace(p);
        } catch (Exception e) {
        }
        return sw.toString();
    }
}
