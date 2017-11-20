/*
 * 文 件 名:  BaseService.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-3-31,  All rights reserved  
 */
package cn.leadeon.base.service;

import java.util.List;
import java.util.Map;
/**
 * 基础servie接口定义
 * 
 * @author liudongdong
 * @version  [1.0, 2014-3-31]
 * @since [中国移动手机营业厅BSS系统]
 */
public interface BaseService {
	/**
	 * 增加从主库查
	 * @param sqlName
	 * @param parameterObject
	 * @return
	 */
	public Object findByWrite(String sqlName, Object parameterObject);
	/**
	 *添加
	 * @param entity
	 * @return
	 */
	public Object insert(String sqlname,Object parameterObject);
	/**
	 *删除
	 * @param entity
	 * @return
	 */
	public Integer delete(String sqlName, Object parameterObject);
	/**
	 *修改
	 * @param entity
	 * @return
	 */
	public Integer update(String sqlName, Object parameterObject);
	/**
	 *查询
	 * @param entity
	 * @return
	 */
	public List<?> select(String sqlName, Object parameterObject);
	public List<?> select(String sqlName, Map<String, Object> parameterObject);
	/**
	 *查询单个
	 * @param entity
	 * @return
	 */
	public Object findBy(String sqlName, Object parameterObject);
	public Object findBy(String sqlName, Map<String, Object> parameterObject);
	
}
