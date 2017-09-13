/**
* <html>
*  <body>
*   <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016年4月25日</p>
*   <p> Created by 姓名</p>
*  </body>
* </html>
*/
package com.sunshine.common.msgmanager.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

public class MsgCenterRecord extends BaseSQLEntity {

	private static final long serialVersionUID = -4986026824515861472L;

	/**
	 * userId或者第三方平台openId
	 */
	private String userId;

	/**
	 * 消息编码
	 */
	private String msgCode;

	/**
	 * 消息标题
	 */
	private String msgTitle;

	/**
	 * 1:未读,2:已读
	 */
	private Integer isRead;

	/**
	 * 消息头内容,即消息内容的第一条内容,用作消息中心列表展示
	 */
	private String msgHeader;

	/**
	 * JSON格式的消息内容
	 */
	private String msgContent;

	/**
	 * JSON格式的消息功能
	 */
	private String msgFunction;

	/**
	 * 消息生成时间
	 */
	private Date msgDate;

	/**
	 * 消息生成时间
	 */
	protected String msgDateStr;

	private Map<String, Serializable> otherParams;

	public MsgCenterRecord() {
		super();
	}

	/**
	 * @param userId
	 * @param hospitalId
	 * @param hospitalName
	 * @param msgCode
	 * @param msgTitle
	 * @param isRead
	 * @param msgHeader
	 * @param msgContent
	 * @param msgFunction
	 * @param msgDate
	 */
	public MsgCenterRecord(String userId, String msgCode, String msgTitle, Integer isRead, String msgHeader, String msgContent, String msgFunction,
			Date msgDate) {
		super();
		this.userId = userId;
		this.msgCode = msgCode;
		this.msgTitle = msgTitle;
		this.isRead = isRead;
		this.msgHeader = msgHeader;
		this.msgContent = msgContent;
		this.msgFunction = msgFunction;
		this.msgDate = msgDate;
	}

	/**
	* @return the userId
	*/
	public String getUserId() {
		return userId;
	}

	/**
	* @param code
	*            the userId to set
	*/
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
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
	* @return the isRead
	*/
	public Integer getIsRead() {
		return isRead;
	}

	/**
	* @param code
	*            the isRead to set
	*/
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	/**
	* @return the msgHeader
	*/
	public String getMsgHeader() {
		return msgHeader;
	}

	/**
	* @param code
	*            the msgHeader to set
	*/
	public void setMsgHeader(String msgHeader) {
		this.msgHeader = msgHeader == null ? null : msgHeader.trim();
	}

	/**
	* @return the msgContent
	*/
	public String getMsgContent() {
		return msgContent;
	}

	/**
	* @param code
	*            the msgContent to set
	*/
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent == null ? null : msgContent.trim();
	}

	/**
	* @return the msgFunction
	*/
	public String getMsgFunction() {
		return msgFunction;
	}

	/**
	* @param code
	*            the msgFunction to set
	*/
	public void setMsgFunction(String msgFunction) {
		this.msgFunction = msgFunction == null ? null : msgFunction.trim();
	}

	/**
	* @return the msgDate
	*/
	public Date getMsgDate() {
		return msgDate;
	}

	/**
	* @param code
	*            the msgDate to set
	*/
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	public String getMsgDateStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(msgDate);
	}

	public void setMsgDateStr(String msgDateStr) {
		this.msgDateStr = msgDateStr;
	}

	public Map<String, Serializable> getOtherParams() {
		return otherParams;
	}

	public void setOtherParams(Map<String, Serializable> otherParams) {
		this.otherParams = otherParams;
	}

}