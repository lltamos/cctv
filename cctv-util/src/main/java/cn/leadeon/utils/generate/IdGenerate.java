/*
 * 文 件 名:  IdGenerate.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2016-5-23,  All rights reserved  
 
package cn.leadeon.utils.generate;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.leadeon.utils.convert.DateUtil;
import cn.leadeon.utils.env.OrderCodeGen;


*//**
 * <订单ID生成工具类> 
 * <订单ID生成算法：包含属性与每个属性固定的二进制位数为：unix时间戳占用39bit+订单版本号2bit+分库5bit+分表5bit+机器号6bit+自增序号6bit=63bit>
 * 
 * <unix时间戳为满足订单号为long型，二进制位为定长39bit，且生成订单号长度固定为19位，因此经过计算得出时间区间为：历史时间：1191474093056 → 2007-10-4 13:01:33，系统上线时间：1466352000000 → 2016-6-20 00:00:00，最大支撑时间：2016107813887 → 2033-11-20 21:56:53，可支撑时间为：2033年-2016年=17年>
 * unix时间戳区间如下：
    {
	  	100000000000000000000000000000000000000
		274877906944
		111111111111111111111111111111111111111
		549755813887
	}
	历史时间与系统最大支撑时间计算公式：
   	历史时间=系统上线时间-274877906944
   	最大支撑时间=系统上线时间+549755813887
 * 
 * 
 * @author yunhaibin
 * @version [版本号, 2016-6-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 *//*
public class IdGenerate {
	
	*//**
	 * twepoch 历史时间
	 *//*
	private final static long twepoch = 1191474093056L;
	
    private final long workerId;
    private long idVersion = 0L;
    private long database = 0L;
    private long dbTable = 0L;
    private long sequence = 0L;
    
    private final static long idVersionBits = 2L;
    private final static long databaseBits = 5L;
    private final static long dbTableBits = 5L;
    private final static long workerIdBits = 6L;
    private final static long sequenceBits = 6L;
    
    private final static long workerIdShift = sequenceBits;
    private final static long dbTableShift = workerIdShift + sequenceBits;
    private final static long databaseShift = dbTableShift + dbTableBits;
    private final static long idVersionShift = databaseShift + databaseBits;
    private final static long timestampLeftShift = idVersionShift + idVersionBits;
    
    public final static long maxIdVersion = -1L ^ -1L << idVersionBits;
    public final static long maxDatabase = -1L ^ -1L << databaseBits;
    public final static long maxDbTable = -1L ^ -1L << dbTableBits;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;
    
    private long lastTimestamp = -1L;
    
    *//** 
     * <默认构造函数>
     *//*
    public IdGenerate(final long workerId) {
        // 获取当前机器IP最后的尾数
//        this.workerId = GetMachineID.MACHINEID % 64;
    	this.workerId = workerId;
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
    }

    *//** 
     * <默认构造函数>
     * @param workerId 机器标识
     * @param database 分库信息
     * @param dbTable 分表信息
     * @param idVersion 订单版本号
     * 
     *//*
    public IdGenerate(final long workerId, long database, final long dbTable, final long idVersion) {
    	// 获取当前机器IP最后的尾数
//        this.workerId = GetMachineID.MACHINEID % 64;
        this.workerId = workerId;
        this.database = database;
        this.dbTable = dbTable;
        this.idVersion = idVersion;
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (database > maxDatabase || database < 0) {
        	throw new IllegalArgumentException(String.format("database can't be greater than %d or less than 0", maxDatabase));
        }
        if (dbTable > maxDbTable || dbTable < 0) {
        	throw new IllegalArgumentException(String.format("dbTable can't be greater than %d or less than 0", maxDbTable));
        }
        if (idVersion > maxIdVersion || idVersion < 0) {
        	throw new IllegalArgumentException(String.format("idVersion can't be greater than %d or less than 0", maxIdVersion));
        }
    }

    *//** 
     * <生成唯一ID>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     *//*
    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            //System.out.println(sequence);
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
//        long nextId = ((1466352000000L - twepoch << timestampLeftShift))
        		| (this.idVersion << idVersionShift) 
        		| (this.database << databaseShift) 
        		| (this.dbTable << dbTableShift) 
        		| (this.workerId << workerIdShift) 
        		| (this.sequence);
//         System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
//         + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
//         + workerId + ",sequence:" + sequence);
        return nextId;
    }
    
    *//** 
     * <根据生成的唯一ID获取此ID生成的时间>
     * <功能详细描述>
     * @param id 唯一ID
     * @return unix时间戳
     * @see [类、类#方法、类#成员]
     *//*
    public static long getIdTime(long id) {
        return (id >> timestampLeftShift) + twepoch;
    }
    
    *//** 
     * <根据生成的唯一ID获取此ID的版本号>
     * <功能详细描述>
     * @param id 唯一ID
     * @return 订单版本号
     * @see [类、类#方法、类#成员]
     *//*
    public static long getIdVersion(long id) {
        return ((id >> timestampLeftShift << timestampLeftShift) ^ id) >> idVersionShift;
    }
    
    *//** 
     * <根据生成的唯一ID获取此ID的分库信息>
     * <功能详细描述>
     * @param id 唯一ID
     * @return 分库信息
     * @see [类、类#方法、类#成员]
     *//*
    public static long getIdDatabase(long id) {
        return ((id >> idVersionShift << idVersionShift) ^ id) >> databaseShift;
    }
    
    *//** 
     * <根据生成的唯一ID获取此ID的分表信息>
     * <功能详细描述>
     * @param id 唯一ID
     * @return 分表信息
     * @see [类、类#方法、类#成员]
     *//*
    public static long getIdDbTable(long id) {
        return ((id >> databaseShift << databaseShift) ^ id) >> dbTableShift;
    }
    
    *//** 
     * <根据生成的唯一ID获取此ID生成的机器号>
     * <功能详细描述>
     * @param id 唯一ID
     * @return 机器号
     * @see [类、类#方法、类#成员]
     *//*
    public static long getIdWorkerId(long id) {
        return ((id >> dbTableShift << dbTableShift) ^ id) >> workerIdBits;
    }
    
    *//** 
     * <根据生成的唯一ID获取此ID生成的自增序号>
     * <功能详细描述>
     * @param id 唯一ID
     * @return 自增序号
     * @see [类、类#方法、类#成员]
     *//*
    public static long getIdSequence(long id) {
        return ((id >> sequenceBits << sequenceBits) ^ id);
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }
    
    *//** 
     * <获取订单ID对应的所有属性信息>
     * <功能详细描述>
     * @param id
     * @return OrderIdInfo
     * @see [类、类#方法、类#成员]
     *//*
    public static OrderIdInfo getOrderIdInfo(long id) {
    	OrderIdInfo orderIdInfo = new OrderIdInfo();
    	orderIdInfo.setId(id);
    	orderIdInfo.setTime(getIdTime(id));
    	orderIdInfo.setIdVersion(getIdVersion(id));
    	orderIdInfo.setDatabase(getIdDatabase(id));
    	orderIdInfo.setDbTable(getIdDbTable(id));
    	orderIdInfo.setWorkerId(getIdWorkerId(id));
    	orderIdInfo.setSequence(getIdSequence(id));
    	return orderIdInfo;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public long getIdVersion() {
		return idVersion;
	}

	public void setIdVersion(long idVersion) {
		this.idVersion = idVersion;
	}

	public long getDatabase() {
		return database;
	}

	public void setDatabase(long database) {
		this.database = database;
	}

	public long getDbTable() {
		return dbTable;
	}

	public void setDbTable(long dbTable) {
		this.dbTable = dbTable;
	}

	public static void main(String[] args) {
//    	IdGenerate worker = new IdGenerate(55, 0, 11, 1);
//        long id = worker.nextId();
//        System.out.println(id);
//        
//        OrderIdInfo orderIdInfo = IdGenerate.getOrderIdInfo(id);
//    	
//    	System.out.println(orderIdInfo.toString());
    	
//    	System.out.println(0%2);
//    	System.out.println();
    	
    	
//        IdGenerate worker = new IdGenerate(55, 28, 11, 0);
//        long id = worker.nextId();
//        
//        OrderIdInfo orderIdInfo = new OrderIdInfo();
//        orderIdInfo = worker.getOrderIdInfo(id);
//        System.out.println(orderIdInfo.getId());
//        System.out.println(orderIdInfo.getTime());
//        System.out.println(DateUtil.dateFormat(new Date(orderIdInfo.getTime()), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(orderIdInfo.getIdVersion());
//        System.out.println(orderIdInfo.getDatabase());
//        System.out.println(orderIdInfo.getDbTable());
//        System.out.println(orderIdInfo.getWorkerId());
//        System.out.println(orderIdInfo.getSequence());
        
//        System.out.println("ID:" + id);
//		System.out.println("ID full binary:" + Long.toBinaryString(id));
//		System.out.println("Unix time:" + worker.getIdTime(id));
//		System.out.println("Date time:" + DateUtil.dateFormat(new Date(worker.getIdTime(id)), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println("IdVersion:" + worker.getIdVersion(id));
//		System.out.println("Database:" + worker.getIdDatabase(id));
//		System.out.println("DbTable:" + worker.getIdDbTable(id));
//		System.out.println("WorkId:" + worker.getIdWorkerId(id));
//		System.out.println("Sequence:" + worker.getIdSequence(id));
        
//        for (int i = 0; i < 1000; i++) {
//        	long id = worker.nextId();
//        	System.out.println("ID:" + id);
////        System.out.println("ID full binary:" + Long.toBinaryString(id));
////        System.out.println("Unix time:" + worker.getIdTime(id));
////        System.out.println("Date time:" + DateUtil.dateFormat(new Date(worker.getIdTime(id)), "yyyy-MM-dd HH:mm:ss"));
////        System.out.println("IdVersion:" + worker.getIdVersion(id));
////        System.out.println("Database:" + worker.getIdDatabase(id));
////        System.out.println("DbTable:" + worker.getIdDbTable(id));
////        System.out.println("WorkId:" + worker.getIdWorkerId(id));
////        System.out.println("Sequence:" + worker.getIdSequence(id));
//        }
    	
    	
//
//    	IdGenerate id = new IdGenerate(1, 1, 1, 1);
//		for(int i=0;i<10;i++){
//			OrderIdInfo in = id.getOrderIdInfo(id.nextId());
//			System.out.print(sdf.format(new Date(in.getTime()))+" ");
//			System.out.println(in.getId());
//			System.out.println();
//		}
//    	
//    	System.out.println(getIdSequence(4643060122445353024L));
//    	
		OrderIdInfo id = IdGenerate.getOrderIdInfo(4791033422397046912L);
		System.out.println(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(new Date(id.getTime())));
    	
    }

}*/