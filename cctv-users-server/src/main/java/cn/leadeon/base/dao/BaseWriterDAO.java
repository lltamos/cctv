/*
 * 文 件 名:  BaseDAO.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-3-31,  All rights reserved  
 */
package cn.leadeon.base.dao;

/**
 * 基础dao接口定义
 * 
 * @author liudongdong
 * @version  [1.0, 2014-3-31]
 * @since [中国移动手机营业厅BSS系统]
 */

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public interface BaseWriterDAO {
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
	//public Object select(String sqlName, Object parameterObject);
	
	public List<Object> select(String sqlName,Map<String , Object> paratemObject);
	
	public List<Object> select(String sqlName,Object parameterObject);
	/**
	 *查询单个
	 * @param entity
	 * @return
	 */
	public Object findBy(String sqlName,Map<String , Object> paratemObject);
	
	public Object findBy(String sqlName,Object parameterObject);
	
	
	public  SqlMapClientTemplate getSqlMapClientTemplate();
	
}
