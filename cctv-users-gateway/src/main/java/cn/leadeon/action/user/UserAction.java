package cn.leadeon.action.user;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.leadeon.annotion.tool.CodeMappingDb;
import cn.leadeon.comm.base.ResBody;
import cn.leadeon.comm.base.ResultData;
import cn.leadeon.comm.businesscode.BusinessCode;
import cn.leadeon.comm.log.Log;
import cn.leadeon.comm.responsecode.ResponseCode;
import cn.leadeon.comm.responsecode.ResponseEnum;
import cn.leadeon.db.parampojo.UserNicknameParam;
import cn.leadeon.db.resultpojo.UserNicknameResult;
import cn.leadeon.db.user.UserNicknameService;
import cn.leadeon.resultpojo.UserNickNameRsp;


/**
 * 用户信息相关接口
 * @author billy
 * @description TODO
 * @date 2017年10月24日 上午11:01:03
 */
@Path("intf/napi/api.php")
@Component
public class UserAction {
	private static final Log log = new Log(UserAction.class);
	
	private static final SimpleDateFormat sdf_busResp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Resource
	private UserNicknameService userNicknameService;
	
	
	@GET
	@Consumes
	@PathParam("method=user.getNickName")
	@Produces("application/json; charset=utf-8")
	public ResBody<UserNickNameRsp> getNickName(@Context HttpServletRequest request, 
			@QueryParam("userid") String userid, 
			@QueryParam("client") String client){
		//系统时间
		long startTime=System.currentTimeMillis();
		
		//参数校验返回体
		CodeMappingDb codeDB=null;
		//流水号
        String trace = request.getHeader("Trace");
        
        //响应体
        ResBody<UserNickNameRsp> userRes=new ResBody<UserNickNameRsp>();
        
		try{
			//打印请求参数
			log.reqPrint(Log.USERS_SIGN, Log.CLIENT_REQUEST, trace, BusinessCode.USER_GET_NICKNAME, "userid:"+userid+",client:"+client);
			UserNicknameParam param = new UserNicknameParam();
			param.setUserid(userid);
			//远程调用查询用户昵称实现类
			ResultData<UserNicknameResult> resultData = userNicknameService.getNickname(trace, param);
			
			if(ResponseCode.REQUEST_SUCCESS.equals(resultData.getResultCode())){
				UserNicknameResult result = resultData.getResultData();
				
				UserNickNameRsp rsp = new UserNickNameRsp();
				rsp.setNickname(result.getNickname());
				
				userRes.setCode(ResponseEnum.R_0.getCode());
				userRes.setContent(rsp);
				return userRes;
			}else{
				userRes.setCode(ResponseEnum.R_F_3.getCode());
				userRes.setError(ResponseEnum.R_F_3.getDesc());
			}
		}catch(Exception e){
			userRes.setCode(ResponseEnum.R_F_1.getCode());
			userRes.setError(ResponseEnum.R_F_1.getDesc());
        	log.error(trace , BusinessCode.USER_GET_NICKNAME ,e);
		}finally{
			log.respPrint(Log.USERS_SIGN, Log.CLIENT_RESPONSE, trace, BusinessCode.USER_GET_NICKNAME, System.currentTimeMillis()-startTime, JSON.toJSONString(userRes));
		}
		return userRes;
	}
}
