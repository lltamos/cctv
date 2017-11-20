/*
 * 文 件 名:  RedissonListUtil.java
 * 版    权:  cctv-version1.0  
 */
package cn.leadeon.reddisson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


import cn.leadeon.comm.base.ResultData;
import cn.leadeon.comm.log.Log;
import cn.leadeon.comm.responsecode.ResponseCode;

/**
 * redisson操作List结构数据工具类
 * 
 * @author wl
 * @version [版本号, 2017年10月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class RedissonListUtil  {
	@Autowired
	private RedissonClient redissonClient;
	// 接口标志
	private static final Log logger = new Log(RedissonListUtil.class);// 日志记录


	public ResultData<List<Object>> getRlist(String key, String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<List<Object>> data = new ResultData<List<Object>>();
		try {
			RList<Object> list = redissonClient.getList(key);
			data.setResultData(changRlistToList(list));
		} catch (Exception e) {
			data.setDesc("获取缓存数据失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"getRlist", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}

	/**
	 * 添加方法啊
	 * @param key 
	 * @param value
	 * @param trace 
	 * @return
	 */
	public ResultData<Boolean> add(String key, String value, String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<Boolean> data = new ResultData<Boolean>();
		boolean b=true;
		try {
			RList<Object> list = redissonClient.getList(key);
			String [] values=value.split(",");
			List<String> valueList=Arrays.asList(values);
			for(String str:valueList){
				if(!list.add(str)){
					b=false;
				}
			}
			data.setResultData(b);
		} catch (Exception e) {
			data.setResultData(false);
			data.setDesc("添加缓存数据失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"add", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}
	/**
	 * 删除方法
	 * @param key
	 * @param index
	 * @param trace
	 * @return
	 */
	public ResultData<Boolean> delete(String key, int index, String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<Boolean> data = new ResultData<Boolean>();
		try {
			RList<Object> list = redissonClient.getList(key);
			list.fastRemove(index);
			data.setResultData(true);
		} catch (Exception e) {
			data.setResultData(false);
			data.setDesc("删除缓存数据失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"delete", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}
	/**
	 * 更新方法
	 * @param key
	 * @param index
	 * @param value
	 * @param trace
	 * @return
	 */
	public ResultData<Boolean> update(String key,int index, String value, String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<Boolean> data = new ResultData<Boolean>();
		try {
			RList<Object> list = redissonClient.getList(key);
			list.fastSet(index, value);
			data.setResultData(true);
		} catch (Exception e) {
			data.setResultData(false);
			data.setDesc("修改缓存数据失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"update", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}
	
	/**
	 * 设置缓存中key的失效时间
	 * @param key
	 * @param time
	 * @param timeUnit
	 * @param trace
	 * @return
	 */
	public ResultData<Boolean> setExpire(String key, Long time, TimeUnit timeUnit,
			String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<Boolean> data = new ResultData<Boolean>();
		try {
			RList<?> list = redissonClient.getList(key);
			data.setResultData(list.expire(time, timeUnit));
		} catch (Exception e) {
			data.setResultData(false);
			data.setDesc("设置缓存数据失效时间失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"setExpire", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}

	/**
	 * 判断该key值是否存在于缓存中
	 * @param key
	 * @param value
	 * @param trace
	 * @return
	 */
	public ResultData<Boolean> contains(String key, String value,
			String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<Boolean> data = new ResultData<Boolean>();
		try {
			RList<Object> list = redissonClient.getList(key);
			data.setResultData(list.contains(value));
		} catch (Exception e) {
			data.setResultData(false);
			data.setDesc("判断List缓存数据中是否包含当前值失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"contains", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}
	/**
	 * 获取list缓存中数据元素的位置
	 * @param key
	 * @param value
	 * @param trace
	 * @return
	 */
	public ResultData<Integer> indexOf(String key, String value, String trace) {
		logger.info("trace=" + trace + "key=" + key);
		long startTime = System.currentTimeMillis();
		ResultData<Integer> data = new ResultData<Integer>();
		try {
			RList<Object> list = redissonClient.getList(key);
			data.setResultData(list.indexOf(value));
		} catch (Exception e) {
			data.setDesc("获取List缓存数据中元素位置失败");
			data.setResultCode(ResponseCode.CACHE_OPERATION_ERROR);
		} finally {
			logger.respPrint(Log.CACHE_SIGN, Log.CACHE_RESPONSE, trace,
					"indexOf", System.currentTimeMillis() - startTime,
					JSON.toJSONString(data));
		}
		return data;
	}
	/**
	 * 将对象转化为集合方法
	 * @param rList
	 * @return
	 */
	private List<Object> changRlistToList(RList<?> rList) {
		List<Object> list = new ArrayList<>();
		for(Object object: rList){
			list.add(object);
		}
		return list;
	}

}
