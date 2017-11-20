package cn.leadeon.utils.convert;

public class TrafficConvert {

	private static final Integer ratio = 1024;
	
	
	/**
	 * 将流量数字转换为 包含单位的流量类型
	 * @param num
	 * @return
	 */
	public static String traffic(String num){
		try{
			Integer n = Integer.parseInt(num);
			
			return convert(n);
			
		}catch(Exception e){
			System.err.println("转换流量单位异常");
		}
		return null;
	}
	
	private static String convert(Integer num){
		String dw = "";
		int i = num / ratio;
		if(0 == i){
			dw = num+"MB";
		}else if(i >= 1 && i < 1024){
			dw = i+"GB";
		}else if(i >= 1024){
			i = i /ratio;
			dw = i+"TB";
		}
		
		return dw;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(new Integer(500) / new Integer(1024));
//		System.out.println(new Integer(2000) / new Integer(1024));
//		System.out.println(new Integer(2048) / new Integer(1024));
//		System.out.println(new Integer(1024*1024) / new Integer(1024));
		
		String content = "尊敬的客户您好，您在中国移动官网参加【#$marketingName#$】，获得【#$trafficNum#$】全国通用流量已到账，感谢您的参与！";
		content = content.replace("#$marketingName#$", "免费流量限时抢购活动");
		content = content.replace("#$trafficNum#$", "500MB");
		
		System.out.println(content);
	}

}
