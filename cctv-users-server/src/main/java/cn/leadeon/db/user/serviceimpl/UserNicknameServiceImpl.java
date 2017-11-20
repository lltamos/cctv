/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *                佛祖保佑             永无BUG
 */
package cn.leadeon.db.user.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.leadeon.base.service.impl.BaseServiceImp;
import cn.leadeon.comm.base.ResultData;
import cn.leadeon.comm.businesscode.BusinessCode;
import cn.leadeon.comm.log.Log;
import cn.leadeon.comm.responsecode.ResponseCode;
import cn.leadeon.db.parampojo.UserNicknameParam;
import cn.leadeon.db.resultpojo.UserNicknameResult;
import cn.leadeon.db.user.UserNicknameService;
import cn.leadeon.reddisson.RedissonListUtil;

/**
 * @author billy
 * @description TODO
 * @date 2017年10月24日 下午6:04:10
 */
//@Service
public class UserNicknameServiceImpl extends BaseServiceImp implements UserNicknameService {

	private static final Log logger = new Log(UserNicknameServiceImpl.class);
	@Resource
	RedissonListUtil redissonTool;
	
	@Override
	public ResultData<UserNicknameResult> getNickname(String trace, UserNicknameParam param) {
		long startTime = System.currentTimeMillis();
		// 请求db数据打印
        logger.reqPrint(Log.SERVER_USERS_SIGN, Log.DB_REQUEST, trace, BusinessCode.USER_GET_NICKNAME, JSON.toJSONString(param));
        //接口响应消息体
		ResultData<UserNicknameResult> rsp = new ResultData<UserNicknameResult>();
//		RedissonListUtil redissonTool=new RedissonListUtil();
        try{
        	
        	UserNicknameResult userNicknameResult = new UserNicknameResult();
        	//TODO 此处替换为从数据库查询用户昵称
        	 Map<String, String> busRespParams = new HashMap<String, String>();
        	 busRespParams.put("userid", param.getUserid());
        	 ResultData<Boolean> flage= redissonTool.add("wenllin","20", null);
        	//TODO 此处替换为从数据库查询用户昵称
        	userNicknameResult=(UserNicknameResult) findBy("select.getNickname", busRespParams);
        	//将查询用户昵称放进返回体重。
        	rsp.setResultData(userNicknameResult);
			rsp.setResultCode(ResponseCode.REQUEST_SUCCESS);
			rsp.setDesc("SUCCESS");
			
		}catch (Exception e) {
            //设置异常信息
            rsp.setException(e);
            //设置返回信息
            rsp.setResultCode(ResponseCode.DB_OPERATION_ERROR);
            rsp.setDesc("query Exception");
            logger.error(trace,"from the db query user nickname failure.", e);
        } finally {
        	logger.respPrint(Log.SERVER_USERS_SIGN, Log.DB_RESPONSE, trace, BusinessCode.USER_GET_NICKNAME, System.currentTimeMillis()-startTime, JSON.toJSONString(rsp));
        }
		return rsp;
	}

}
