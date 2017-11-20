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
package cn.leadeon.db.user;

import cn.leadeon.comm.base.ResultData;
import cn.leadeon.db.parampojo.UserNicknameParam;
import cn.leadeon.db.resultpojo.UserNicknameResult;

/**
 * @author billy
 * @description TODO
 * @date 2017年10月24日 下午5:58:09
 */
public interface UserNicknameService {
	public ResultData<UserNicknameResult> getNickname(String trace, UserNicknameParam param);
}
