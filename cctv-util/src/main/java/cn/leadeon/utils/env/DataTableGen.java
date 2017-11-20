/*package cn.leadeon.utils.env;

import com.github.diamond.client.PropertiesConfiguration;
import com.github.diamond.client.PropertiesConfigurationFactoryBean;

import cn.leadeon.utils.crc32.Crc32Util;
import cn.leadeon.utils.generate.IdGenerate;

public class DataTableGen {
	
	private static PropertiesConfiguration config = PropertiesConfigurationFactoryBean
			.getPropertiesConfiguration();

	// 分库基数
	private static final int DATABASE_BASE = 32;

	// 分表基数
	private static final int TABLE_BASE = 32;
	
	
	public static long getDataBaseRealIndex(String cellNum) {
		return getDataBaseIndex(cellNum) % config.getInt("splitDatabase");
	}

	public static long getDataBaseIndex(String cellNum) {
		long index = 0L;
		int crcKey = Crc32Util.crc32(cellNum);
		index = crcKey % DATABASE_BASE;
		return index;
	}

	public static long getTableIndex(String cellNum) {
		int crcKey = Crc32Util.crc32(cellNum);
		return crcKey % TABLE_BASE;
	}
	
	public static long getTableIndex(long id) {
		try{
			return IdGenerate.getIdDbTable(id);
		}catch(Exception e){
			
		}
		return -1;
	}

	public static void main(String[] args){
		
		System.out.println(DataTableGen.getTableIndex(4798313178860245056L));
		
//		System.out.println(getTableIndex("13468673694"));
		
//		System.out.println(DataTableGen.getTableIndex("4696375487461994560"));
//		System.out.println(DataTableGen.getTableIndex("4696408581728514304"));
//		
//		System.out.println(getTableIndex(Long.parseLong("4696375487461994560")));
//		System.out.println(getTableIndex(Long.parseLong("4696408581728514304")));
		
	}
	
}
*/