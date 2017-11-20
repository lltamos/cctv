/*
 * 文 件 名:  SerializeUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-3-31,  All rights reserved  
 */
package cn.leadeon.utils.clazz;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 序列化工具类
 * 
 * @author  liudongdong
 * @version  [1.0, 2014年6月6日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class SerializeUtils
{
   public final static int ARRAY_LENGTH = 1;
    public final static int ARRAY_VALUE = 2;
    public final static int STRING_LENGTH = 3;
    public final static int STRING_VALUE = 4;
    public final static int INT = 5;
    public final static int DOUBLE = 6;
    public final static int SET = 7;

    public final static String ARRAY = "a";
    public final static String DOUBLES = "d";
    public final static String STRING = "s";
    public final static String INTEGER = "i";
    public final static String NULL = "n";
    public final static String SEMICOLON = ";";
    public final static String COLON = ":";
    

    /**
     * 分析序列化内容
     * @param sb
     */
    private static Map<String, String> analyses(StringBuilder sb)
    {
        String[] arrays = sb.toString().split(SEMICOLON);
        Map<String, String> serialHashMap = new HashMap<String, String>();
        int length = arrays.length;
        String key = "";
        for (int i = 0; i < length; i++)
        {
            String temp = arrays[i];
            String[] elements = temp.split(COLON);

            String value = "";
            int var = 0;

            int size = elements.length;
            for (int j = 0; j < size; j++)
            {
                String element = elements[j];

                // 1.获取数据类型
                if(STRING.equalsIgnoreCase(element))
                {
                    var = STRING_LENGTH;
                    continue;
                }

                // 2.获取相应数据类型长度
                if(STRING_LENGTH == var)
                {
                    var = STRING_VALUE;
                    continue;
                }

                // 3.获取相应数据类型值
                if(STRING_VALUE == var && ((i&1) == 0))
                {
                    key = element.substring(1, element.length() - 1);
                    continue;
                } else
                {
                    // 若value>3,表示value中包含COLON,并补回colon
                    value = concat(value, size, j, element);

                    // 两者相等时
                    key = pushMap(serialHashMap, key, size, j, value);
                    continue;
                }

                // 其它类型暂时不考虑
            }

        }
        return serialHashMap;
    }


    /**
     * 连接丢失字符
     * @param value
     * @param size
     * @param j
     * @param element
     */
    private static String concat(String value, int size, int j, String element)
    {
        if(size >= 3)
        {
            if(j == 2)
            {
                value = element;
            } else 
            {
                value = value.concat(COLON).concat(element);
            }
        }
        return value;
    }


    /**
     * @param serialHashMap
     * @param key
     * @param size
     * @param j
     * @param element
     * @return
     */
    private static String pushMap(Map<String, String> serialHashMap,
        String key, int size, int j, String element)
    {
        String value;

        if(element.length() != 0)
        {
            if(j == size - 1)
            {
                //为了保持数据的原子性,此构件中将不处理数据.
                int startIndex = element.indexOf('<') ;
                int endIndex = element.lastIndexOf(">");

                if(endIndex == -1)
                {
                    value = element.substring(1, element.length() - 1);
                } else 
                {
                    value = element.substring(startIndex, endIndex+1);
                }
                serialHashMap.put(key, value);
                key = "";
            }
        }
        return key;
    }
}
