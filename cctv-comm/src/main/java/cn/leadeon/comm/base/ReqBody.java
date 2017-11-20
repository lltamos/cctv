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
package cn.leadeon.comm.base;

import java.io.Serializable;

import cn.leadeon.annotion.StrVerify;


/**
 * @author billy
 * @description TODO
 * @date 2017年8月22日 上午11:15:26
 */
public class ReqBody<T extends Object> implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1355633772408742873L;

	private String cid;
    
    private String en;
    
    private String t;
    
    private String sn;
    
    @StrVerify(isNotNull=true,isNotIllegal=false)
    private String cv;
    
    @StrVerify(isNotNull=true)
    private String st;
    
    private String sv;
    
    private String sp;
    
    private String ak;
    
    private String ctid;
    
    private String xk;
    
    private String xc;
    
    private String cellNum;
    
    @StrVerify(isNotNull=true)
    private String transId;
    
    private String smsAuth;
    
    private T reqBody;

	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * @return the en
	 */
	public String getEn() {
		return en;
	}

	/**
	 * @param en the en to set
	 */
	public void setEn(String en) {
		this.en = en;
	}

	/**
	 * @return the t
	 */
	public String getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(String t) {
		this.t = t;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the cv
	 */
	public String getCv() {
		return cv;
	}

	/**
	 * @param cv the cv to set
	 */
	public void setCv(String cv) {
		this.cv = cv;
	}

	/**
	 * @return the st
	 */
	public String getSt() {
		return st;
	}

	/**
	 * @param st the st to set
	 */
	public void setSt(String st) {
		this.st = st;
	}

	/**
	 * @return the sv
	 */
	public String getSv() {
		return sv;
	}

	/**
	 * @param sv the sv to set
	 */
	public void setSv(String sv) {
		this.sv = sv;
	}

	/**
	 * @return the sp
	 */
	public String getSp() {
		return sp;
	}

	/**
	 * @param sp the sp to set
	 */
	public void setSp(String sp) {
		this.sp = sp;
	}

	/**
	 * @return the ak
	 */
	public String getAk() {
		return ak;
	}

	/**
	 * @param ak the ak to set
	 */
	public void setAk(String ak) {
		this.ak = ak;
	}

	/**
	 * @return the ctid
	 */
	public String getCtid() {
		return ctid;
	}

	/**
	 * @param ctid the ctid to set
	 */
	public void setCtid(String ctid) {
		this.ctid = ctid;
	}

	/**
	 * @return the xk
	 */
	public String getXk() {
		return xk;
	}

	/**
	 * @param xk the xk to set
	 */
	public void setXk(String xk) {
		this.xk = xk;
	}

	/**
	 * @return the xc
	 */
	public String getXc() {
		return xc;
	}

	/**
	 * @param xc the xc to set
	 */
	public void setXc(String xc) {
		this.xc = xc;
	}

	/**
	 * @return the cellNum
	 */
	public String getCellNum() {
		return cellNum;
	}

	/**
	 * @param cellNum the cellNum to set
	 */
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	/**
	 * @return the transId
	 */
	public String getTransId() {
		return transId;
	}

	/**
	 * @param transId the transId to set
	 */
	public void setTransId(String transId) {
		this.transId = transId;
	}

	/**
	 * @return the smsAuth
	 */
	public String getSmsAuth() {
		return smsAuth;
	}

	/**
	 * @param smsAuth the smsAuth to set
	 */
	public void setSmsAuth(String smsAuth) {
		this.smsAuth = smsAuth;
	}

	/**
	 * @return the reqBody
	 */
	public T getReqBody() {
		return reqBody;
	}

	/**
	 * @param reqBody the reqBody to set
	 */
	public void setReqBody(T reqBody) {
		this.reqBody = reqBody;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReqBody [cid=" + cid + ", en=" + en + ", t=" + t + ", sn=" + sn + ", cv=" + cv + ", st=" + st + ", sv="
				+ sv + ", sp=" + sp + ", ak=" + ak + ", ctid=" + ctid + ", xk=" + xk + ", xc=" + xc + ", cellNum="
				+ cellNum + ", transId=" + transId + ", smsAuth=" + smsAuth + ", reqBody=" + reqBody + "]";
	}
    
}
