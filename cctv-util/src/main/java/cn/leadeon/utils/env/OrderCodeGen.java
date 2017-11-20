/*package cn.leadeon.utils.env;

import cn.leadeon.utils.crc32.Crc32Util;
import cn.leadeon.utils.generate.IdGenerate;
import cn.leadeon.utils.generate.OrderIdInfo;

import com.github.diamond.client.PropertiesConfiguration;
import com.github.diamond.client.PropertiesConfigurationFactoryBean;

*//**
 * 订单ID 生成工具类
 * 
 * @author lixuming
 * 
 *//*
public class OrderCodeGen {

	private static PropertiesConfiguration config = PropertiesConfigurationFactoryBean
			.getPropertiesConfiguration();

	// 分库基数
	private static final int DATABASE_BASE = 32;

	private static final IdGenerate idGenerate = (null != ServiceInfo.SERVICE_INFO && !""
			.equals(ServiceInfo.SERVICE_INFO)) ? new IdGenerate(
			Long.parseLong(ServiceInfo.SERVICE_INFO)) : null;

	*//**
	 * 根据用户手机号生成的订单ID实体
	 * 
	 * @param cellNum
	 * @return
	 *//*
	public static OrderIdInfo getOrderCode(String cellNum) {
		OrderIdInfo orderInfo = null;
		try {
			long orderCode = -1L;
			long idVersion = config.getLong("idVersion");
			if (null != idGenerate) {
				idGenerate.setDatabase(getDataBaseIndex(cellNum));
				idGenerate.setDbTable(DataTableGen.getTableIndex(cellNum));
				idGenerate.setIdVersion(idVersion);
				orderCode = idGenerate.nextId();
				orderInfo = getOrderCode(orderCode);
			} else {
				System.err.println("OrderCodeGen has Error! idGenerate is null");
				orderInfo = null;
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement= e.getStackTrace()[0];// 得到异常棧的首个元素
			System.err.println("OrderCodeGen throws Exception! "+" ["+stackTraceElement.getLineNumber()+"] "+e.getMessage());
			orderInfo = null;
		}
		return orderInfo;
	}

	*//**
	 * 根据订单ID获取订单ID实体
	 * 
	 * @param id
	 * @return
	 *//*
	public static OrderIdInfo getOrderCode(long id) {
		OrderIdInfo orderInfo = null;
		try {
			orderInfo = IdGenerate.getOrderIdInfo(id);
			orderInfo.setDatabase(orderInfo.getDatabase()
					% config.getInt("splitDatabase"));
		} catch (Exception e) {
			//orderInfo = null;
		}
		return orderInfo;
	}

	public static long getTableIndex(long id) {
		try {
			return IdGenerate.getIdDbTable(id);
		} catch (Exception e) {

		}
		return -1;
	}

	public static long getDataBaseRealIndex(String cellNum) {
		return getDataBaseIndex(cellNum) % config.getInt("splitDatabase");
	}

	private static long getDataBaseIndex(String cellNum) {
		long index = 0L;
		int crcKey = Crc32Util.crc32(cellNum);
		index = crcKey % DATABASE_BASE;
		// index /= config.getInt("splitDatabase");
		return index;
	}

	public static void main(String[] args) {
		
		
		
//		System.out.println(OrderCodeGen.getOrderCode(4653154757524136000L));
		
		System.out.println();
		
	}
}
*/