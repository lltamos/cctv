/**
 * 
 */
package cn.leadeon.comm.base;


/**
 * <公共工具类>
 * <功能详细描述>
 * 
 * @author  liujie
 * @version  [版本号, 2015-4-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */ 
public class BaseCommonTool {
	
	/**
	 * 价格是分为单位的,转换为圆并且保留四舍五入保留两位小数
	 * 如果价格后为25.00格式，则转换为整数格式
	 * add by liujie
	 * @param price
	 * @return
	 */
	public static String getFormatPrices(String price) {
		String val = "";
		if (!"".equals(price) && null != price) {
			val = String.format("%.2f",Double.parseDouble(price)/100);
			if(val.contains(".00")){
				val = val.replace(".00", "");
			}
		}
    	return val;
	}
	
	/**
	 * 价格是分为单位的,转换为圆并且保留四舍五入保留两位小数
	 * 如果价格后为25.00格式，则转换为整数格式
	 * add by liujie
	 * @param price
	 * @return
	 */
	public static String getFormatPrice(String price) {
		String val = "";
		if (!"".equals(price) && null != price) {
			val = String.format("%.2f",Double.parseDouble(price)/100);
		}
    	return val;
	}
	
	public static void main(String[] args) {
		System.out.println(BaseCommonTool.getFormatPrice("36000.00"));
	}

	/**
	 * 校验字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		boolean flag = false;
		if(str == null  || str == "" || str.isEmpty()){
			flag = true;
		}
		return flag;
	}
}
