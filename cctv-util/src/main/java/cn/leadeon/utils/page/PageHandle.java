/*
 * 文 件 名:  PageHandle.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.page;


/**
 *分页参数封装计算工具类
 *yunhaibin@weke.com
 * @author  yunhaibin
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class PageHandle {
	/**
	 * @Fields DEFAULT_PAGE_SIZE 默认每页显示条数
	 */
	private static final Integer DEFAULT_PAGE_SIZE = 10;
	/**
     * 获取分页对象
     * @param recordCount 总记录数
     * @return
     */
    public static PageInfo getPageInfo(Integer curPage ,int pageSize,int recordCount){
        PageInfo page;
        if (curPage == null || curPage <= 1) {
            page = new PageInfo(pageSize, 1, 0);
        } else {
            page = new PageInfo(pageSize, curPage, 0);
        }
        int pageCount;
        if (recordCount>0){
            if (recordCount % page.getPageSize() != 0) {
                pageCount = recordCount / page.getPageSize() + 1;
            } else {
                pageCount = recordCount / page.getPageSize();
            }
            page.setPageCount(pageCount);
        }else {
            page.setPageCount(0);
        }
        return page;
    }

    /** 
     * @Title: getPageInfo 
     * @Description: TODO 构造分页类型
     * @param curPage 当前页
     * @param recordCount 总记录数
     * @return 
     * @throws 
     */
    public static PageInfo getPageInfo(Integer curPage ,int recordCount){
        PageInfo page;
        if (curPage == null || curPage <= 1) {
            page = new PageInfo(DEFAULT_PAGE_SIZE, 1, 0);
        } else {
            page = new PageInfo(DEFAULT_PAGE_SIZE, curPage, 0);
        }
        int pageCount;
        if (recordCount>0){
            if (recordCount % page.getPageSize() != 0) {
                pageCount = recordCount / page.getPageSize() + 1;
            } else {
                pageCount = recordCount / page.getPageSize();
            }
            page.setPageCount(pageCount);
        }else {
            page.setPageCount(0);
        }
        return page;
    }
}
