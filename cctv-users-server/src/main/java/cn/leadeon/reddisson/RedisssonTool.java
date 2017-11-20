package cn.leadeon.reddisson;



import java.util.HashMap;
import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import cn.leadeon.comm.base.ResultData;
import cn.leadeon.comm.log.Log;
import cn.leadeon.comm.responsecode.ResponseCode;

public class RedisssonTool {
	@Autowired
	private RedissonClient redissonClient;
	//接口标志
	public static final String ID = "redisscache";
	private static final Log logger = new Log(RedisssonTool.class);//日志记录 
	/** 
	* @Title: getRMap
	* @Description:缓存对象接口 
	* @param @param key 
	*  @return   
	*/ 
	public ResultData<Map<String, Object>> getRMap(String key, String trace) {
		long startTime = System.currentTimeMillis();
		logger.reqPrint(Log.CACHE_SIGN, Log.CACHE_REQUEST, "getRMap",  "key="+key, trace);
		ResultData<Map<String, Object>> result = new ResultData<Map<String, Object>>();
		try{
			RMap<String, String> rMap = redissonClient.getMap(key);
			result.setResultData(rMapChange(rMap));
			result.setResultCode(ResponseCode.REQUEST_SUCCESS);
			return result;
		}catch(Exception e){
	        logger.error(trace,"mget redisson getRMapFunction failure.", e);
	        result.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
	        result.setDesc("查询库存数据失败");
		}finally{
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace, "getRMap", System.currentTimeMillis()-startTime, JSON.toJSONString(result));
		}
		return result;
	}
	/** 
	* @Title: add
	* @Description:缓存对象接口 
	* @param key 
	* @param 例子（18706716190:5178297548088049728$0$611$18706716193$2017-07-15 21:17:59）
	* @param hashKey(18706716190) 
	* @param value(5178297548088049728$0$611$18706716193$2017-07-15 21:17:59)
	* @param @return   
	* @throws
	*/ 
	public ResultData<Map<String, Object>> add(String key, String hashKey,String value, String trace) {
		long startTime = System.currentTimeMillis();
		logger.reqPrint(Log.CACHE_SIGN, Log.CACHE_REQUEST, "add",  "key="+key, trace);
		ResultData<Map<String, Object>> result = new ResultData<Map<String, Object>>();
		try{
			RMap<String, String> rMap = redissonClient.getMap(key);
				 rMap.fastPut(hashKey, value);
				 result.setResultCode(ResponseCode.REQUEST_SUCCESS);
				 result.setDesc("添加数据成功");
			 return result;
		}catch(Exception e){
	        logger.error(trace,"mget redisson getRMapFunction failure.", e);
	        result.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
	        result.setDesc("添加数据失败");
		}finally{
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace, "add", System.currentTimeMillis()-startTime, JSON.toJSONString(result));
		}
		return result;
	}
	/** 
	* @Title: delete
	* @Description:缓存对象接口 
	* @param key 
	* @param @return   
	* @throws
	*/ 
	public ResultData<Map<String, Object>> delete(String key, String trace) {
		long startTime = System.currentTimeMillis();
		logger.reqPrint(Log.CACHE_SIGN, Log.CACHE_REQUEST, "delete",  "key="+key, trace);
		ResultData<Map<String, Object>> result = new ResultData<Map<String, Object>>();
		try{
			RMap<String, String> rMap = redissonClient.getMap(key);
			if(!rMap.isEmpty()){
				rMap.remove(key);
				result.setResultCode(ResponseCode.REQUEST_SUCCESS);
				result.setDesc("删除数据成功");
				 return result;
			}else{
				 result.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
			      result.setDesc("删除数据为空");
			}
		}catch(Exception e){
	        logger.error(trace,"mget redisson getRMapFunction failure.", e);
	        result.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
	        result.setDesc("删除数据失败");
		}finally{
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace, "delete", System.currentTimeMillis()-startTime, JSON.toJSONString(result));
		}
		return result;

	}
	/** 
	* @Title: update
	* @Description:缓存对象接口 
	* @param key 
	* 例子（18706716190:5178297548088049728$0$611$18706716193$2017-07-15 21:17:59）
	* @param hashKey(18706716190) 
	* @param value(5178297548088049728$0$611$18706716193$2017-07-15 21:17:59)
	* @param @return   
	* @throws
	*/
	public ResultData<Map<String, Object>> update(String key,String hashKey,String value, String trace) {
		long startTime = System.currentTimeMillis();
		logger.reqPrint(Log.CACHE_SIGN, Log.CACHE_REQUEST, "update",  "key="+key, trace);
		ResultData<Map<String, Object>> result = new ResultData<Map<String, Object>>();
		try{
			RMap<String, String> rMap = redissonClient.getMap(key);
			if(!rMap.isEmpty()){
				rMap.fastPut(hashKey, value);
				result.setResultCode(ResponseCode.REQUEST_SUCCESS);
				result.setDesc("修改数据成功");
				return result;
			}else{
				result.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		        result.setDesc("修改的数据Key"+key+"不存在");
			}
		}catch(Exception e){
	        logger.error(trace,"修改数据失败", e);
	        result.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
	        result.setDesc("修改数据失败");
		}finally{
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace, "update", System.currentTimeMillis()-startTime, JSON.toJSONString(result));
		}
		return result;

	}

	private Map<String, Object> rMapChange(RMap<String, String> rMap){
		logger.info("开始转换rmap");
		Map<String, Object> map=new HashMap<String,Object>();
		for (String key :rMap.keySet()) {
			logger.info("aMap=====key===="+key+"value========="+rMap.get(key));
		}	
		for(String key :rMap.keySet()){  
			map.put(key, rMap.get(key));
		}
		return map;
	}
	
}
