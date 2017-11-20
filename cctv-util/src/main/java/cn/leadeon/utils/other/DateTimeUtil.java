package cn.leadeon.utils.other;

import java.text.SimpleDateFormat;

public class DateTimeUtil {

	private static final String date_format = "yyyy-MM-dd";
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(); 
 
    public static SimpleDateFormat getDateFormat()   
    {  
    	SimpleDateFormat df = threadLocal.get();  
        if(df==null){  
            df = new SimpleDateFormat(date_format);  
            threadLocal.set(df);  
        }  
        return df;  
    }  

}
