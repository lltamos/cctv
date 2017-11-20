/*
 * 文 件 名:  PageInfo.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.page;

import java.io.Serializable;


/**
 * 分页类
 * 邮 箱:hizhangyaoming@yahoo.com
 * @author  zhangyaomin
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 803380753167125418L;
	/**
	 * 默认每页记录数
	 */
	private static final Integer DEFAULT_PAGE_SIZE = 20;

	/**
	 * 每页记录数
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	/**
	 * 当前记录
	 */
	private Integer startRecord = 1;

	/**
	 * 跳过的记录
	 */
	private Integer skip = 0;

	/**
	 * 当前页码
	 */
	private Integer pageIndex = 1;

	/**
	 * 上一次查询的最大Id
	 */
	private Integer maxRecordId = 0;

	/**
	 * 总页数
	 */
	private Integer pageCount = 1;

	/**
	 * 
	 * @param pageSize
	 *            一夜显示多少条，不传默认为20条
	 * @param startPage
	 *            开始页(必填项)
	 * @param maxRecordId
	 *            当前页的最大ID(为可选项，不填分页性能很低)
	 */
	public PageInfo(Integer pageSize, Integer startPage, Integer maxRecordId) {
		this.pageSize = pageSize;
		this.pageIndex = startPage;
		this.maxRecordId = maxRecordId;
		this.startRecord = pageSize * (startPage - 1) + 1;
		this.skip = (pageIndex - 1) * pageSize;
	}

	/**
	 * 
	 * @param pageSize
	 *            一夜显示多少条，不传默认为20条
	 * @param startPage
	 *            开始页(必填项)
	 * @param maxRecordId
	 */
	public PageInfo(Integer pageSize, Integer startPage) {
		this.pageSize = pageSize;
		this.pageIndex = startPage;
		this.maxRecordId = pageSize * (startPage - 1);
		this.startRecord = pageSize * (startPage - 1) + 1;
		this.skip = (pageIndex - 1) * pageSize;
	}

	public PageInfo() {
		super();
	}

	public static void main(String[] args) {
		PageInfo pageInfo = new PageInfo(20, 2);
		System.out.println(pageInfo.getStartRecord());
	}

	/**
	 * 得到结束的记录数
	 * 
	 * @return
	 */

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getStartRecord() {
		return startRecord;
	}

	public Integer getMaxRecordId() {
		return maxRecordId;
	}

	public Integer getSkip() {
		return skip;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
