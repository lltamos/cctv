/*package cn.leadeon.utils.env;

import cn.leadeon.utils.crc32.Crc32Util;
import cn.leadeon.utils.generate.IdGenerate;

public class TestDataTableGen {
	
//	private static PropertiesConfiguration config = PropertiesConfigurationFactoryBean
//			.getPropertiesConfiguration();

	// 分库基数
	private static final int DATABASE_BASE = 32;

	// 分表基数
	private static final int TABLE_BASE = 32;
	
	
//	public static long getDataBaseRealIndex(String cellNum) {
//		return getDataBaseIndex(cellNum) % config.getInt("splitDatabase");
//	}

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
		
//		System.out.println(TestDataTableGen.getTableIndex(4817410897490755904L));
		
		System.out.println(getTableIndex("13571490362"));
		
//		System.out.println(getDataBaseIndex("13753448281"));
//		System.out.println(getTableIndex("sulykaka@163.com"));
//		System.out.println(getTableIndex(4765679465879363648L));
		
//		System.out.println(TestDataTableGen.getTableIndex(4765897382654959680L));
//		System.out.println(TestDataTableGen.getTableIndex(4766443382637252672L));
//		System.out.println(TestDataTableGen.getTableIndex(4911253773462552768L));
		
//		System.out.println(DataTableGen.getTableIndex("4696375487461994560"));
//		System.out.println(DataTableGen.getTableIndex("4696408581728514304"));
//		
//		System.out.println(getTableIndex(Long.parseLong("4696375487461994560")));
//		System.out.println(getTableIndex(Long.parseLong("4696408581728514304")));
		
	}
	
}
*/