///*
// * 文 件 名:  CacheManage.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-2-27,  All rights reserved  
// */
//package cn.leadeon.action.cache;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.OPTIONS;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import cn.leadeon.cache.senume.NameSpaceType;
//import cn.leadeon.cache.service.CacheOperationService;
//import cn.leadeon.comm.base.ResBody;
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.comm.responsecode.ResponseCode;
//import cn.leadeon.constant.ComVariable;
//import cn.leadeon.resultpojo.GiveStock;
//
//import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * <缓存操作>
// * 
// * @author 董飞
// * @version [2.0, 2015-2-27]
// * @since [中国移动手机营业厅/模块版本]
// */
//@Path("v1/cacheManage")
//@Component
//public class CacheManage {
//	private static final Log logger = new Log(CacheManage.class);
//	@Autowired
//	CacheOperationService cacheOperationService; //获取缓存数据Server
//
//	@OPTIONS
//	@POST
//	@Path("delNameSpace")
//	@Consumes()
//	@Produces(ContentType.APPLICATION_JSON_UTF_8)
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public ResBody<GiveStock> delNameSpace(@Context HttpServletRequest request, @Context HttpServletResponse response, String reqObj) {
//		ResBody resObj = new ResBody();
//		/* 获取trace */
//		String trace = (String) request.getHeader("Trace");
//		try{
//			//转换为json对象
//			JSONObject object =  JSONObject.parseObject(reqObj);;
//			String nameSpace = object.getString("nameSpace");
//			ResultData delResult = cacheOperationService.delNameSpace(nameSpace, trace);
//			final String CACHESUCESS = "0000";
//			if(!delResult.getResultCode().equals(CACHESUCESS)){
//				logger.info("Trace:" +  trace + "================清理缓存失败，原因:" + delResult.getDesc());
//				resObj.setRetCode(ResponseCode.EXCEPTION);
//				resObj.setRetDesc(delResult.getDesc());
//				return resObj;
//			}
//			
//			resObj.setRetCode(ResponseCode.REQUEST_SUCCESS);
//			resObj.setRetDesc(ComVariable.RSP_DESC_SUCCESS);
//		}catch(Exception e){
//			logger.error("Trace:" +  trace + "================clean nameSpace error! " + e);
//			resObj.setRetCode(ResponseCode.EXCEPTION);
//			resObj.setRetDesc("clean nameSpace error!");
//		}
//		
//		return resObj;
//	}
//	
//	@OPTIONS
//	@POST
//	@Path("delKey")
//	@Consumes()
//	@Produces(ContentType.APPLICATION_JSON_UTF_8)
//	@SuppressWarnings("rawtypes")
//	public ResBody delKey(@Context HttpServletRequest request,@Context HttpServletResponse response, String reqObj) {
//		ResBody resObj = new ResBody();
//		/* 获取trace */
//		String trace = (String) request.getHeader("Trace");
//		try{
//			//转换为json对象
//			JSONObject object =  JSONObject.parseObject(reqObj);;
//			String nameSpace = object.getString("nameSpace");
//			String key = object.getString("key");
//			logger.info("Trace:" +  trace + "================body:" + object);
//			ResultData delResult = cacheOperationService.delValue(getNameSpace(nameSpace),key, trace);
//			final String CACHESUCESS = "0000";
//			if(!delResult.getResultCode().equals(CACHESUCESS)){
//				logger.info("Trace:" +  trace + "================清理缓存失败，原因:" + delResult.getDesc());
//				resObj.setRetCode(ResponseCode.EXCEPTION);
//				resObj.setRetDesc(delResult.getDesc());
//				return resObj;
//			}
//			
//			resObj.setRetCode(ResponseCode.REQUEST_SUCCESS);
//			resObj.setRetDesc(ComVariable.RSP_DESC_SUCCESS);
//		}catch(Exception e){
//			logger.error("Trace:" +  trace + "================clean nameSpace error! " + e);
//			resObj.setRetCode(ResponseCode.EXCEPTION);
//			resObj.setRetDesc("clean nameSpace error!");
//		}
//		
//		return resObj;
//	}
//	
//	/**
//	 * 命名空间映射
//	 * @param nameSpace
//	 * @return
//	 */
//	private NameSpaceType getNameSpace(String nameSpace){
//		switch(nameSpace){
//		//case "900004" : return NameSpaceType.GOODSLIST_NAMESPACE; //商品列表命名空间
//		case "900005" : return NameSpaceType.REMINDER_INFO_NAMESPACE;//温馨提示语命名空间
//		default : return NameSpaceType.REMINDER_INFO_NAMESPACE;
//		}
//	}
//}
