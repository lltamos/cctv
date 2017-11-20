package cn.leadeon.comm.responsecode;

public enum ResponseEnum {

	//-----------系统公共 响应码 start------------//
	/**
	 * 0 成功
	 */
	R_0("0",""),
	

	/**
	 * -1 系统异常，请稍后重试 <br>
	 * <desc>系统内部异常</desc>
	 */
	R_F_1("-1","系统异常，请稍后重试"),
	
	
	/**
	 * -2 %s参数非法%s <br>
	 * <desc>%s参数非法%s</desc>
	 */
	R_F_2("-2","%s参数非法%s"),
	
	/**
	 * -3 %s数据查询失败%s <br>
	 * <desc>%s数据查询失败%s</desc>
	 */
	R_F_3("-3","%s数据查询失败%s");
	
	private String code;
	private String desc;
	
	ResponseEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public String getDesc(String... args){
		final int ftLen = getFormatSize(desc);
		if(ftLen <= 0){
			return desc;
		}
		String[] s = new String[ftLen];
		
		if(args.length != ftLen){
			s = new String[ftLen];
		}
		
		for(int i=0 ; i<s.length ;i++){
			if((i+1) > args.length){
				s[i] = "";
				continue;
			}
			if(null == args[i]){
				s[i] = "";
			}else{
				s[i] = args[i];
			}
		}
		return String.format(desc, s);
	}

	
	private static int getFormatSize(String str){
		int i =0;
		if(str.indexOf("%s") > -1){
			i++;
			i += getFormatSize(str.substring(str.indexOf("%s")+2, str.length()));
			return i;
		}else{
			return i;
		}
	}

	public static void main(String[] args){
//		System.out.println(ResponseEnum.R_2004.getDesc("aa","bb"));
//		System.out.println(ResponseEnum.R_2004.getDesc("aa","bb","cc"));
	}
	
}
