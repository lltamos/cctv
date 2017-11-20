///*
// * 文 件 名:  AsyncHttpClient.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2016-1-13,  All rights reserved  
// */
//package cn.leadeon.utils.http;
//
//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeoutException;
//
//import com.ning.http.client.AsyncHttpClient;
//import com.ning.http.client.AsyncHttpClientConfig;
//import com.ning.http.client.Response;
//
///**
// * <HTTP请求工具类>
// * <功能详细描述>
// * 
// * @author  yunhaibin
// * @version  [版本号, 2016-1-13]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public final class HttpClient {
//	/** 
//	 * <默认构造函数>
//	 */
//	private HttpClient(){};
//	
//	/**
//	 * ASYNC_HTTP_CLIENT_CONFIG
//	 */
//	private static final AsyncHttpClientConfig ASYNC_HTTP_CLIENT_CONFIG = new AsyncHttpClientConfig.
//			Builder().setConnectionTimeoutInMs(3000).setRequestTimeoutInMs(6000).build();
//	
//	private static final AsyncHttpClient HTTP_CLIENT = new AsyncHttpClient(ASYNC_HTTP_CLIENT_CONFIG);
//	
//	public static void main(String[] args) {
//		Response response;
//		try {
//			response = HTTP_CLIENT.prepareGet("http://www.nickid.cn/wx/sample.php")
//					.execute().get();
//			String result = response.getResponseBody();
//			System.out.println(result);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			System.out.println("响应超时");
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
