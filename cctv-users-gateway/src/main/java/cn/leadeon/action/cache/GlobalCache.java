///*
// * 文 件 名:  ReqFilter.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-2-3,  All rights reserved  
// */
//package cn.leadeon.action.cache;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ReadListener;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.WriteListener;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//
//import cn.leadeon.cache.result.CellNumLocationRes;
//import cn.leadeon.cache.service.CacheOperationService;
//import cn.leadeon.cache.service.CellNumLocationService;
//import cn.leadeon.comm.base.ResBody;
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.comm.namespace.NameSpace;
//import cn.leadeon.comm.responsecode.ResponseEnum;
//import cn.leadeon.common.DateTimeTool;
//import cn.leadeon.common.ResponseHeaderUtil;
//import cn.leadeon.constant.ComVariable;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * <全局缓存处理>
// * <功能详细描述>
// * 
// * @author  liujie
// * @version  [版本号, 2015-9-9]
// * @see  [相关类/方法]
// * @since  [产品/中国移动2.2模块版本]
// */
//public class GlobalCache implements Filter {
//	private static final Log logger = new Log(GlobalCache.class);
//	private static final String PROJECTNAME = "/SG";
//	private static final int NOCACHE = 0;
//	
//	// 缓存命名空间 <key: uri, value: bussCode>
//	private static HashMap<String, Integer> nameSpaceMap = new HashMap<String, Integer>();
//	// 业务缓存关键字 <key: bussCode, value: keyList>
//	private static HashMap<Integer, String[]> cacheKeyMap = new HashMap<Integer, String[]>();
//	
//	//需要查询号段的接口列表,1表示需要号段关联，2表示不需要，列表里面为空也不需要
//	private static HashMap<String, String> cellNumLocationServiceList = new HashMap<String, String>();
//	
//	private CacheOperationService cacheOperationService; //获取缓存数据Server
//	
//	private CellNumLocationService cellNumLocationService; //号段查询
//	
//	private static final String SUCCESS = "0";
//	private static final String ERROR = "1";
//	static{
//
//		// 存储uri及命名空间映射,此处存在表示对应业务需要做全局缓存
//		
//		//==============温馨提示，添加缓存需求接口==========start
//		nameSpaceMap.put(PROJECTNAME + "/v1/reminder/getReminder", NameSpace.REMINDER_INFO_NAMESPACE);
//		//==============温馨提示，添加缓存需求接口==========end
//		
//		
//		//==================需要关联号段的接口列表============start
//		cellNumLocationServiceList.put(PROJECTNAME + "/v1/reminder/getReminder", "1");
//		//==================需要关联号段的接口列表============end
//		
//		
//		//=====================缓存key参数列表==========start
//		cacheKeyMap.put(NameSpace.REMINDER_INFO_NAMESPACE, new String[] {"flowType","exchangeType","clientType"}); //温馨提示
//		//=====================缓存key参数列表==========end
//		
//	}
//	/**
//	 * 重载方法
//	 * 
//	 * @param request
//	 * @param response
//	 * @param chain
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse rsp = (HttpServletResponse) response;
//
//		Long startTime = System.currentTimeMillis();
//		/* 获取trace */
//		String trace = (String) req.getHeader("Trace");
//
//		RequestWrapper reqWrapper = new RequestWrapper(req);
//		String reqJson = reqWrapper.getReqBody();
//
//		String uri = req.getRequestURI();
//
//		String rspData = "";
//		
//		// 是否需要从业务接口获取响应数据标识
//		boolean getFromBuss = true;
//
//		//标志号段是否有对应的省市编码
//		boolean flags = false;
//		//充值号码号段查询
//		ResultData<CellNumLocationRes> chargeResultData = null;
//		//需要用省编码作为key的接口，如果请求参数里面只有电话号码，需要关联号段
//		String prcode = "";
//		if(cellNumLocationServiceList.containsKey(uri) && "1".equals(cellNumLocationServiceList.get(uri))){
//			// 解析请求报文
//			JSONObject object =  JSONObject.parseObject(reqJson);
//			if(object != null){
//				if(object.get("chargeCell") != null && StringUtils.isNotEmpty(object.get("chargeCell").toString())){
//					chargeResultData = cellNumLocationService.getCellNumLoca(trace, object.get("chargeCell").toString());
//				}else if(object.get("provinceCode") != null && StringUtils.isNotEmpty(object.get("provinceCode").toString())){
//					prcode = object.get("provinceCode").toString();
//				}else{
//					flags = false;
//				}
//			}
//			
//			/*if(chargeResultData != null && chargeResultData.getResultData() != null && chargeResultData.getResultData().getProvCode() != null){
//				flags = true;
//			}*/
//		}
//		
//		
//		//如果对应业务属于一级缓存，尝试从缓存获取数据
//		int nameSpace = getNameSpace(uri);
//		String cacheKey = getCacheKey(nameSpace, uri, reqJson);
////		if(flags){
//			if(StringUtils.isNotEmpty(prcode)){
//				cacheKey = prcode + cacheKey;
//			}else if(chargeResultData != null && chargeResultData.getResultData() != null){
//				cacheKey = chargeResultData.getResultData().getProvCode() + cacheKey;
//			}else{
//				cacheKey ="";
//			}
////		}
//		
//		logger.info("Trace:" + trace + "================cacheKey" + cacheKey);
//		
//		//缓存里面有数据，从缓存中获取数据
//		if ((nameSpace != NOCACHE) && !(StringUtils.isEmpty(cacheKey))) {
//			rspData = getRespData(trace, nameSpace, cacheKey);
//			// 从缓存成功获取数据，打印及返回给客户端
//			if (!StringUtils.isEmpty(rspData)) {
//				getFromBuss = false;
//				// 客户端请求参数打印
//				logger.reqPrint(Log.CACHE_SIGN, Log.CLIENT_REQUEST, trace, String.valueOf(nameSpace), reqJson);
//
//				writeResponse(trace, rsp, rspData);
//				
//				// 响应客户端参数打印
//				logger.respPrint(Log.CACHE_SIGN, Log.CLIENT_RESPONSE, trace, String.valueOf(nameSpace), System.currentTimeMillis() - startTime, rspData);
//			}
//		}
//		
//		//缓存中没有数据，调取接口
//		if (getFromBuss) {
//			// 如果缓存无数据，调用接口查询结果
//			WapperedResponse rspWrapper = new WapperedResponse(rsp);
//			chain.doFilter(reqWrapper, rspWrapper);
//			rspData = rspWrapper.getResponseData();
//			
//			if(rspData != null){
//				@SuppressWarnings("rawtypes")
//				ResBody resObj = JSON.parseObject(rspData, ResBody.class);
//				
//				if(StringUtils.equals(resObj.getRetCode(), ResponseEnum.R_0000.getCode())){
//					rspWrapper.setHeader("RspCode", SUCCESS);
//					
//					if((nameSpace != NOCACHE) && !StringUtils.isEmpty(cacheKey)){
//						setRspData(trace, nameSpace, cacheKey, rspData);
//					}
//				}else{
//					rspWrapper.setHeader("RspCode", ERROR);
//				}
//			}else{
//				ResBody<Object> resObj = new ResBody<Object>();
//				resObj.setRetCode(ResponseEnum.R_1001.getCode());
//				resObj.setRetDesc(ComVariable.RSP_DESC_DEFAULT_ERR);
//				rspData = JSON.toJSONString(resObj);
//			}
//			writeResponse(trace, rsp, rspData);
//		}
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		ServletContext sc = arg0.getServletContext();         
//		XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc); 
//		cacheOperationService = (CacheOperationService) cxt.getBean("cacheOperationService");
//		cellNumLocationService = (CellNumLocationService) cxt.getBean("cellNumLocationService");
//	}
//
//	class RequestWrapper extends HttpServletRequestWrapper {
//		private String reqBody;
//		private HttpServletRequest request;
//
//		/**
//		 * 设置 reqBody
//		 * 
//		 * @param 对reqBody进行赋值
//		 */
//		public void setReqBody(String reqBody) {
//			this.reqBody = reqBody;
//		}
//
//		public RequestWrapper(HttpServletRequest request) {
//			super(request);
//			this.request = request;
//		}
//
//		public String getReqBody() {
//			ServletInputStream stream = null;
//			try {
//				stream = request.getInputStream();
//				reqBody = StringUtils.replace(IOUtils.toString(stream, ComVariable.ENCODING_UTF8), ">\\s{1,}<", "><");
//			} catch (IOException e) {
//				logger.error("", e);
//			} finally {
//				if (stream != null) {
//					try {
//						stream.close();
//					} catch (IOException e) {
//						logger.error("", e);
//					}
//				}
//			}
//
//			return reqBody;
//		}
//
//		public ServletInputStream getInputStream() {
//			byte[] buffer = null;
//			try {
//				buffer = reqBody.getBytes(ComVariable.ENCODING_UTF8);
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			final ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
//
//			ServletInputStream newStream = new ServletInputStream() {
//
//				@Override
//				public int read() throws IOException {
//					return bais.read();
//				}
//
//				@Override
//				public boolean isFinished() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				@Override
//				public boolean isReady() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				@Override
//				public void setReadListener(ReadListener listener) {
//					// TODO Auto-generated method stub
//
//				}
//			};
//			return newStream;
//		}
//	}
//
//	class WapperedResponse extends HttpServletResponseWrapper {
//		private ByteArrayOutputStream buffer = null;
//		private ServletOutputStream out = null;
//		private PrintWriter writer = null;
//
//		public WapperedResponse(HttpServletResponse resp) throws IOException {
//			super(resp);
//			// 真正存储数据的流
//			buffer = new ByteArrayOutputStream();
//			out = new WapperedOutputStream(buffer);
//			writer = new PrintWriter(new OutputStreamWriter(buffer,
//					this.getCharacterEncoding()));
//		}
//
//		// 重载父类获取outputstream的方法
//		@Override
//		public ServletOutputStream getOutputStream() throws IOException {
//			return out;
//		}
//
//		// 重载父类获取writer的方法
//		@Override
//		public PrintWriter getWriter() throws UnsupportedEncodingException {
//			return writer;
//		}
//
//		// 重载父类获取flushBuffer的方法
//		@Override
//		public void flushBuffer() throws IOException {
//			if (out != null) {
//				out.flush();
//			}
//			if (writer != null) {
//				writer.flush();
//			}
//		}
//
//		@Override
//		public void reset() {
//			buffer.reset();
//		}
//
//		public String getResponseData() throws IOException {
//			// 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
//			flushBuffer();
//			return buffer.toString(ComVariable.ENCODING_UTF8);
//		}
//
//		// 内部类，对ServletOutputStream进行包装
//		private class WapperedOutputStream extends ServletOutputStream {
//			private ByteArrayOutputStream bos = null;
//
//			public WapperedOutputStream(ByteArrayOutputStream stream)
//					throws IOException {
//				bos = stream;
//			}
//
//			@Override
//			public void write(int b) throws IOException {
//				bos.write(b);
//			}
//
//			@Override
//			public boolean isReady() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public void setWriteListener(WriteListener listener) {
//				// TODO Auto-generated method stub
//
//			}
//		}
//	}
//
//	
//	private void writeResponse(String trace, HttpServletResponse rsp, String rspData){
//		ServletOutputStream out = null;
//		rsp.setHeader("Content-Type", ComVariable.CONTENTTYPE_JSON);
//		rsp.setHeader("DataEncoding", ComVariable.ENCODING_UTF8);
//		ResponseHeaderUtil.setH5CorsHeader(rsp);
//		
//		try {
//			out = rsp.getOutputStream();
//			out.write(rspData.getBytes(ComVariable.ENCODING_UTF8));
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			logger.error(trace, e);
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//			} catch (IOException e) {
//				logger.error(trace, e);
//			}
//		}
//	}
//	
//	
//	// 获取缓存key
//	private String getCacheKey(int nameSpace, String uri, String reqJson){
//		StringBuffer cacheKey = new StringBuffer();
//		if(nameSpace != NOCACHE){
//			String[] keys = cacheKeyMap.get(nameSpace);
//			// 解析请求报文
//			JSONObject object =  JSONObject.parseObject(reqJson);
//			Object keyValue;
//			for(String key: keys){
//				logger.info("=================" + key);
//				if((object != null) && ((keyValue=object.get(key)) != null)){
//					cacheKey.append("_").append(keyValue.toString());
//				}else{
//					logger.error("Error cache key: key=" + key + " reqJson=" + reqJson);
//					return "";
//				}
//			}
//		}
//		return cacheKey.toString();
//	}
//	
//	/*
//	 * 根据请求url获取接口编码
//	 */
//	private int getNameSpace(String uri) {
//		if (!uri.contains(PROJECTNAME)) {
//			uri = PROJECTNAME + uri;
//		}
//
//		int nameSpace = NOCACHE;
//		if (nameSpaceMap.containsKey(uri)) {
//			nameSpace = nameSpaceMap.get(uri);
//		}
//
//		return nameSpace;
//	}
//	
//	/**
//	 * 从缓存中获取响应报文
//	 * @param trace
//	 * @param nameSpace
//	 * @param key
//	 * @return
//	 */
//	private String getRespData(String trace, int nameSpace, String key) {
//		String respData = "";
//		try {
//			@SuppressWarnings("unchecked")
//			ResultData<String> rspData = cacheOperationService.get(nameSpace, key, trace);
//			if (!StringUtils.isEmpty(rspData.getResultData())) {
//				respData = StringUtils.replace(rspData.getResultData(), ">\\s{1,}<", "><");
//			}
//		} catch (Exception e) {
//			logger.error(trace, e.getMessage());
//			return respData;
//		}
//
//		return respData;
//	}
//	
//	/**
//	 * 数据保存到缓存中
//	 * @param trace
//	 * @param nameSpace
//	 * @param key
//	 * @param value
//	 */
//	private void setRspData(String trace, int nameSpace, String key, String value) {
//		try {
//			cacheOperationService.set(nameSpace, key, value, DateTimeTool.DAY_SEC_NUMBER, trace);
//		} catch (Exception e) {
//			logger.error(trace, e);
//		}
//	}
//}