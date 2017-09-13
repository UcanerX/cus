/**
* <html>
*  <body>
*   <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016年4月11日</p>
*   <p> Created by 姓名</p>
*  </body>
* </html>
*/
package com.sunshine.common.msgmanager.entity;

import java.util.ArrayList;
import java.util.List;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * 个人中心消息配置
 * @Project: sunshine_health
 * @Package: com.sunshine.common.entity.platform.msgmanager
 * @ClassName: MsgCenter
 * @Statement: <p>
 *             </p>
 * @JDK version used:
 * @Author: 申姜
 * @Create Date: 2016-4-11
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class MsgCenter extends BaseSQLEntity {
	private static final long serialVersionUID = -3935164853475476467L;

	/**
	 * 医院ID
	 */
	private String hospitalId;

	/**
	 * 消息编码
	 */
	private String msgCode;

	/**
	 * 消息标题
	 */
	private String msgTitle;
	/**
	 * 消息内容
	 */
	private List<MsgCenterContent> msgCenterContents = new ArrayList<MsgCenterContent>();
	/**
	 * 消息功能
	 */
	private List<MsgCenterFunction> msgCenterFunctions = new ArrayList<MsgCenterFunction>();

	public MsgCenter() {
		super();
	}

	/**
	 * @param hospitalId
	 * @param msgCode
	 * @param msgTitle
	 * @param msgCenterContents
	 * @param msgCenterFunctions
	 */
	public MsgCenter(String hospitalId, String msgCode, String msgTitle, List<MsgCenterContent> msgCenterContents,
			List<MsgCenterFunction> msgCenterFunctions) {
		super();
		this.hospitalId = hospitalId;
		this.msgCode = msgCode;
		this.msgTitle = msgTitle;
		this.msgCenterContents = msgCenterContents;
		this.msgCenterFunctions = msgCenterFunctions;
	}

	/**
	* @return the hospitalId
	*/
	public String getHospitalId() {
		return hospitalId;
	}

	/**
	* @param code
	*            the hospitalId to set
	*/
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId == null ? null : hospitalId.trim();
	}

	/**
	* @return the msgCode
	*/
	public String getMsgCode() {
		return msgCode;
	}

	/**
	* @param code
	*            the msgCode to set
	*/
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode == null ? null : msgCode.trim();
	}

	/**
	* @return the msgTitle
	*/
	public String getMsgTitle() {
		return msgTitle;
	}

	/**
	* @param code
	*            the msgTitle to set
	*/
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle == null ? null : msgTitle.trim();
	}

	/**
	 * @return the msgCenterContents
	 */
	public List<MsgCenterContent> getMsgCenterContents() {
		return msgCenterContents;
	}

	/**
	 * @param msgCenterContents the msgCenterContents to set
	 */
	public void setMsgCenterContents(List<MsgCenterContent> msgCenterContents) {
		this.msgCenterContents = msgCenterContents;
	}

	/**
	 * @return the msgCenterFunctions
	 */
	public List<MsgCenterFunction> getMsgCenterFunctions() {
		return msgCenterFunctions;
	}

	/**
	 * @param msgCenterFunctions the msgCenterFunctions to set
	 */
	public void setMsgCenterFunctions(List<MsgCenterFunction> msgCenterFunctions) {
		this.msgCenterFunctions = msgCenterFunctions;
	}

}