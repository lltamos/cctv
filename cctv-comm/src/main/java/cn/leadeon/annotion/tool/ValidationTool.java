package cn.leadeon.annotion.tool;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cn.leadeon.annotion.HasMail;
import cn.leadeon.annotion.IllegalPhoneNo;
import cn.leadeon.annotion.IsDouble;
import cn.leadeon.annotion.IsInteger;
import cn.leadeon.annotion.IsMail;
import cn.leadeon.annotion.StrVerify;
import cn.leadeon.comm.responsecode.ResponseEnum;

public class ValidationTool {

	/** 
     * 判断是否为浮点数或者整数 
     * @param str 
     * @return true Or false 
     */  
    public static boolean isNumeric(String str){  
          if( !str.matches("^(-?\\d+)(\\.\\d+)?$") ){  
                return false;  
          }  
          return true;  
    }  
      
    /** 
     * 判断是否为正确的邮件格式 
     * @param str 
     * @return boolean 
     */  
    public static boolean isEmail(String str){  
        if(StringUtils.isNotBlank(str))  
            return false;  
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");  
    }  
      
    /** 
     * 判断是否为正确的邮件格式 (邮件可为空)
     * @param str 
     * @return boolean 
     */  
    public static boolean hasEmail(String str){  
        if(str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")){
        	return true; 
        }  
            return false;  
    }  
    /** 
     * 判断字符串是否为移动手机号 
     * @param str 
     * @return boolean 
     */  
    public static boolean isCMCCMobile(String str){  
        if(StringUtils.isNotBlank(str))  
            return false;  
        return str.matches("^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8]|18[2])[0-9]{8}$");  
    }  
      
    
    
    /** 
     * 判断字符串是否为手机号 
     * @param str 
     * @return boolean 
     */  
    public static boolean isMobile(String str){  
        if(StringUtils.isBlank(str))  
            return false;  
        return str.matches("^1[3-8][0-9]{9}$");  
    }  
    
    /** 
     * 判断是否为数字 
     * @param str 
     * @return 
     */  
    public static boolean isNumber(String str) {
    	if(StringUtils.isNumeric(str))
    		return true;
    	return false;
    }  
      
  
    /**
	 * 判断是否为UTF8的中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUTF8Chinese(String str) {
		if (str == null) {
			return false;
		}
		char UTR8_MAX_VALUE = '\u9fa5';
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (str.charAt(i) > UTR8_MAX_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	 /** 
     * 判断字符串是否非法字符
     * @param str 
     * @return boolean 
     */  
    public static boolean isIllegal(String str){
    	
    	String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx); 
		 Matcher m = p.matcher(str);
		 if (m.find()) {
			 return true;
		 }else {
			 return false;
		 }
    }
    
	 /** 
     * 判断字符串是否非法字符(忽略给定字符)
     * @param str 
     * @return boolean 
     */  
    public static boolean isIllegal(String str,String [] ignoreStr){
    	
    	String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    	if(null!=ignoreStr&&ignoreStr.length>0){
    		for(int i=0;i<ignoreStr.length;i++){
        		regEx=regEx.replace(ignoreStr[i],"");
        	}
    	}
		Pattern p = Pattern.compile(regEx); 
		 Matcher m = p.matcher(str);
		 if (m.find()) {
			 return true;
		 }else {
			 return false;
		 }
    }
    /** 
     * 判断字符串是否为double类型 
     * @param str 
     * @return boolean 
     */  
    public static boolean isDoubleStr(String str){  
        if(StringUtils.isBlank(str))  
            return false;  
        return str.matches("[-+]?[0-9]+(\\.[0-9]+)?");   
    }  
	public static CodeMappingDb validationField(Object obj){
		CodeMappingDb codeDb = new CodeMappingDb();
		Field[] fields =  obj.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			Object o=null;
			try {
				o = field.get(obj);
			} catch (Exception e1) {
			}
			
			try{
				IsInteger isint = field.getAnnotation(IsInteger.class);
				if(isint!=null){
					
					//modify start by xuexiaohua at 2015-05-26 兼容当枚举类型字段值转为int类型值连续，且值允许为空时的判断校验逻辑
					if(isint.isNull() && (null == o || "".equals(o))){
						
					}
					else{
						if (StringUtils.isEmpty(o.toString())) {
//							codeDb.setCodeNumber(ResponseCode.NOTNULL_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不能为空!");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不能为空"));
							return codeDb;
						}
						
						if(!isNumber(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.ISNUMBER_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不是数字!");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不是数字"));
							return codeDb;
						}
						
						
						if(Integer.parseInt(o.toString())<isint.min()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 取值非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",取值非法"));
							return codeDb;
						}
						if(Integer.parseInt(o.toString())>isint.max()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 取值非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",取值非法"));
							return codeDb;
						}
					}
				}
				StrVerify sv = field.getAnnotation(StrVerify.class);
				if(sv!=null){
					if(sv.isNotNull()){
						if(null==o || StringUtils.isBlank(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.NOTNULL_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不能为空!");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不能为空"));
							return codeDb;
						}
					}
					
					if(o!=null &&  StringUtils.isNotBlank(o.toString())){
						if(sv.isNotIllegal()){
							if(null!=sv.ignoreIllegal()&&sv.ignoreIllegal().length>0 ){//忽略给定非法字符
								if(isIllegal(o.toString(),sv.ignoreIllegal())){
//									codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//									codeDb.setCodeDesc("字段名: " + field.getName() + " 含有非法字符! ");
									codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
									codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",含有非法字符"));
									return codeDb;
								}
							}else{
								if(isIllegal(o.toString())){
//									codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//									codeDb.setCodeDesc("字段名: " + field.getName() + " 含有非法字符! ");
									codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
									codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",含有非法字符"));
									return codeDb;
								}
							}
						}
						if(o.toString().length() < sv.minLength()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 长度非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",长度非法"));
							return codeDb;
						}
						if(o.toString().length() > sv.maxLength()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 长度非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",长度非法"));
							return codeDb;
						}
						
						if(sv.isNumber() && !StringUtils.isNumeric(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.ISNUMBER_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不是数字!");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不是数字"));
							return codeDb;
						}
						if(sv.isNumber() && !o.toString().matches("^[0-9]*[1-9][0-9]*$") && sv.isPage()){
//							codeDb.setCodeNumber(ResponseCode.ISNUMBER_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不能为小数或者零!");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不能为小数或者零"));
							return codeDb;
						}
						if(o.toString().contains(",")&&o.toString().contains("[")&&o.toString().contains("]")&&sv.isBigStr()==true){//检验字符串中含有特殊字符，比如["","",""...]
							
							String subStr = o.toString().substring(1, o.toString().length()-1);
							String[] splitStr = subStr.split(",");
							for(int i=0;i<splitStr.length;i++){
								if(Pattern.compile("[0-9]*").matcher(splitStr[i]).matches() ==false||sv.maxLength()>1024){
//										codeDb.setCodeNumber(ResponseCode.IDBIGSTR_ERROR);
//										codeDb.setCodeDesc("字段名: " + field.getName() + "中第"+(i+1)+"个数据不合法!");
									codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
									codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",第"+(i+1)+"个数据不合法"));
									break; 
								}else{
//									codeDb.setCodeNumber(ResponseCode.REQUEST_SUCCESS);
//									codeDb.setCodeDesc("SUCCESS");
									codeDb.setCodeNumber(ResponseEnum.R_0.getCode());
									codeDb.setCodeDesc(ResponseEnum.R_0.getDesc());
								}
							}
							return codeDb;
						}
					}
				}
				if(field.isAnnotationPresent(IsMail.class)){
					if(!isEmail(o.toString())){
//						codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//						codeDb.setCodeDesc("字段名: " + field.getName() + " 邮件格式非法! ");
						codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
						codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",邮件格式非法"));
						return codeDb;
					}
				}
				if(field.isAnnotationPresent(HasMail.class)){//邮件可为空时对邮件格式的校验
					if(null!=o&&!o.toString().trim().equals("")){
						if(!hasEmail(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 邮件格式非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",邮件格式非法"));
							return codeDb;
						}
					}
					
				}
				if(field.isAnnotationPresent(IllegalPhoneNo.class)){
					if((null!=o) && !(StringUtils.isEmpty(o.toString()))){
						if(!isMobile(o.toString())){
//						codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//						codeDb.setCodeDesc("字段名: " + field.getName() + " 格式非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",格式非法"));
							return codeDb;
						}
					}
				}
				IsDouble isDouble = field.getAnnotation(IsDouble.class);
				if((isDouble!=null) && (isDouble.isNotNull())){
					if((o!=null) && !(StringUtils.isEmpty(o.toString()))){
						if(StringUtils.isBlank(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.NOTNULL_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不能为空!");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不能为空"));
							return codeDb;
						}
						
						if(isDouble.isLegal() && o.toString().matches("[\\pP|~|$|^|<|>|\\||=]*")){
//							codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 含有非法字符! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",含有非法字符"));
							return codeDb;
						}
						
						if(o.toString().length() > isDouble.maxLength()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 长度非法! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",长度非法"));
							return codeDb;
						}
						
						if(!isDoubleStr(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.IDDOUBLE_ERROR);
//							codeDb.setCodeDesc("字段名: " + field.getName() + " 不是double类型! ");
							codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
							codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc(field.getName(),",不是double类型"));
							return codeDb;
						}
					}
				}
			}catch(Exception e){
//				codeDb.setCodeNumber(ResponseCode.REQUEST_ERROR);
//				codeDb.setCodeDesc("请合理请求!");
				codeDb.setCodeNumber(ResponseEnum.R_F_2.getCode());
				codeDb.setCodeDesc(ResponseEnum.R_F_2.getDesc());
				return codeDb;
			}
		}
//		codeDb.setCodeNumber(ResponseCode.REQUEST_SUCCESS);
//		codeDb.setCodeDesc("SUCCESS");
		codeDb.setCodeNumber(ResponseEnum.R_0.getCode());
		codeDb.setCodeDesc(ResponseEnum.R_0.getDesc());
		return codeDb;
	}
	

    private ValidationTool(){} 
    
}
