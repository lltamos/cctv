package cn.leadeon.utils.clazz;

/**
 * 
 *得到实体类
 * 
 * @author  liudongdong
 * @version  [1.0, 2014年6月12日]
 * @since  [中国移动手机营业厅BSS系统]
 */
public class ImportEntityUtil {
	/**
	 * 得到实体类
	 * @param fullentity
	 * @return
	 */
	 @SuppressWarnings("rawtypes")
	public static  Class getEntityClass(String fullentity){
		 Class entityClass = null;
		try {
			entityClass = (Class) Class.forName(fullentity);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		 return entityClass;
	 }  
	 
	 
}
