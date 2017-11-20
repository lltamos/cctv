/*
 * 文 件 名:  BaseDAOImp.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-3-31,  All rights reserved  
 */
package cn.leadeon.base.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.leadeon.base.dao.BaseWriterDAO;
/**
 * 基础dao实现
 * 
 * @author liudongdong
 * @version  [1.0, 2014-3-31]
 * @since [中国移动手机营业厅BSS系统]
 */
public class BaseWriterDAOImp extends SqlMapClientDaoSupport implements BaseWriterDAO{
  
	@Override
	public Object insert(String sqlname, Object parameterObject) {
		return getSqlMapClientTemplate().insert(sqlname, parameterObject);
	}

	@Override
	public Integer delete(String sqlName, Object parameterObject) {
		return getSqlMapClientTemplate().delete(sqlName, parameterObject);
	}

	@Override
	public Integer update(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(sqlName, parameterObject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> select(String sqlName, Map<String, Object> paratemObject) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(sqlName, paratemObject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> select(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		
		return getSqlMapClientTemplate().queryForList(sqlName, parameterObject);
	}

	@Override
	public Object findBy(String sqlName, Map<String, Object> paratemObject) {
		return getSqlMapClientTemplate().queryForObject(sqlName, paratemObject);
	}

	@Override
	public Object findBy(String sqlName, Object parameterObject) {
		return getSqlMapClientTemplate().queryForObject(sqlName, parameterObject);
	}

//	@Override
//	public Object select(String sqlName, Object parameterObject) {
//		
//		return getSqlMapClientTemplate().queryForObject(sqlName, parameterObject);
//	}

}
