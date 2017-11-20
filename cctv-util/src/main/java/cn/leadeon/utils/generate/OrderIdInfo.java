/*
 * 文 件 名:  OrderIdInfo.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2016-6-17,  All rights reserved  
 */
package cn.leadeon.utils.generate;

import java.io.Serializable;

/**
 * <订单ID详细组成信息>
 * <功能详细描述>
 * 
 * @author  yunhaibin
 * @version  [版本号, 2016-6-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrderIdInfo implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5931670688754551149L;
	
	private long id;
	/**
	 * time
	 */
	private long time;
	/**
	 * idVersion
	 */
	private long idVersion;
	/**
	 * database
	 */
	private long database;
	/**
	 * dbTable
	 */
	private long dbTable;
	/**
	 * workerId
	 */
	private long workerId;
	/**
	 * sequence
	 */
	private long sequence;
	/**
	 * 获取 time
	 * @return 返回 time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * 设置 time
	 * @param 对time进行赋值
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * 获取 idVersion
	 * @return 返回 idVersion
	 */
	public long getIdVersion() {
		return idVersion;
	}
	/**
	 * 设置 idVersion
	 * @param 对idVersion进行赋值
	 */
	public void setIdVersion(long idVersion) {
		this.idVersion = idVersion;
	}
	/**
	 * 获取 database
	 * @return 返回 database
	 */
	public long getDatabase() {
		return database;
	}
	/**
	 * 设置 database
	 * @param 对database进行赋值
	 */
	public void setDatabase(long database) {
		this.database = database;
	}
	/**
	 * 获取 dbTable
	 * @return 返回 dbTable
	 */
	public long getDbTable() {
		return dbTable;
	}
	/**
	 * 设置 dbTable
	 * @param 对dbTable进行赋值
	 */
	public void setDbTable(long dbTable) {
		this.dbTable = dbTable;
	}
	/**
	 * 获取 workerId
	 * @return 返回 workerId
	 */
	public long getWorkerId() {
		return workerId;
	}
	/**
	 * 设置 workerId
	 * @param 对workerId进行赋值
	 */
	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}
	/**
	 * 获取 sequence
	 * @return 返回 sequence
	 */
	public long getSequence() {
		return sequence;
	}
	/**
	 * 设置 sequence
	 * @param 对sequence进行赋值
	 */
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	/**
	 * 获取 id
	 * @return 返回 id
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设置 id
	 * @param 对id进行赋值
	 */
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "OrderIdInfo [id=" + id + ", time=" + time + ", idVersion="
				+ idVersion + ", database=" + database + ", dbTable=" + dbTable
				+ ", workerId=" + workerId + ", sequence=" + sequence + "]";
	}
	
}
