/*
 * 文 件 名:  BaseServiceImp.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-3-31,  All rights reserved  
 */
package cn.leadeon.base.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.leadeon.base.dao.BaseDAO;
import cn.leadeon.base.dao.BaseWriterDAO;
import cn.leadeon.base.service.BaseService;
/**
 * 基础service实现
 * 
 * @author liudongdong
 * @version  [1.0, 2014-3-31]
 * @since [中国移动手机营业厅BSS系统]
 */

public class BaseServiceImp implements BaseService {

	private BaseDAO baseDAO;
	private BaseWriterDAO baseWriteDAO;
	public void setBaseDAO(BaseDAO baseDAO) {
	
		this.baseDAO = baseDAO;
	}

	public void setBaseWriteDAO(BaseWriterDAO baseWriteDAO) {
	
		this.baseWriteDAO = baseWriteDAO;
	}


	
	

	@Override
	public Object insert(String sqlname, Object parameterObject) {
		// TODO Auto-generated method stub
		return baseWriteDAO.insert(sqlname, parameterObject);
	}

	@Override
	public Integer delete(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		return baseWriteDAO.delete(sqlName, parameterObject);
	}

	@Override
	public Integer update(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		return baseWriteDAO.update(sqlName, parameterObject);
	}
	
	@Override
	public Object findByWrite(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		return baseWriteDAO.findBy(sqlName, parameterObject);
	}

	@Override
	public List<?> select(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		//System.out.println(sqlName);
		return baseDAO.select(sqlName, parameterObject);
	}

	@Override
	public List<?> select(String sqlName, Map<String, Object> parameterObject) {
		// TODO Auto-generated method stub
		return baseDAO.select(sqlName, parameterObject);
	}

	@Override
	public Object findBy(String sqlName, Object parameterObject) {
		// TODO Auto-generated method stub
		return baseDAO.findBy(sqlName, parameterObject);
	}

	@Override
	public Object findBy(String sqlName, Map<String, Object> parameterObject) {
		// TODO Auto-generated method stub
		return baseDAO.findBy(sqlName, parameterObject);
	}

	
	public void startTransaction() throws SQLException{
		baseWriteDAO.getSqlMapClientTemplate().getSqlMapClient().startTransaction();
	}
	
	
	public void commitTransaction() throws SQLException{
		baseWriteDAO.getSqlMapClientTemplate().getSqlMapClient().commitTransaction();
	}
	
	
	public void endTransaction() throws SQLException{
		baseWriteDAO.getSqlMapClientTemplate().getSqlMapClient().endTransaction();
	}
}
