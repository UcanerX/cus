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

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * 个人中心消息内容
 * @Project: sunshine_health
 * @Package: com.sunshine.common.entity.platform.msgmanager
 * @ClassName: MsgCenterContent
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
public class MsgCenterContent extends BaseSQLEntity {

	private static final long serialVersionUID = 4811624832501927283L;

	/**
	 * 序号
	 */
	private Integer sort;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * 内容
	 */
	private String value;
	/**
	 * 字体颜色编码
	 */
	private String fontColorCode;

	/**
	 * 消息中心消息ID
	 */
	private String msgId;

	public MsgCenterContent() {
		super();
	}

	/**
	 * @param sort
	 * @param keyword
	 * @param value
	 * @param msgId
	 */
	public MsgCenterContent(Integer sort, String keyword, String value, String msgId) {
		super();
		this.sort = sort;
		this.keyword = keyword;
		this.value = value;
		this.msgId = msgId;
	}

	/**
	* @return the sort
	*/
	public Integer getSort() {
		return sort;
	}

	/**
	* @param code
	*            the sort to set
	*/
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	* @return the keyword
	*/
	public String getKeyword() {
		return keyword;
	}

	/**
	* @param code
	*            the keyword to set
	*/
	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	/**
	* @return the value
	*/
	public String getValue() {
		return value;
	}

	/**
	* @param code
	*            the value to set
	*/
	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	/**
	 * @return the fontColorCode
	 */
	public String getFontColorCode() {
		return fontColorCode;
	}

	/**
	 * @param fontColorCode the fontColorCode to set
	 */
	public void setFontColorCode(String fontColorCode) {
		this.fontColorCode = fontColorCode == null ? null : fontColorCode.trim();
	}

	/**
	* @return the msgId
	*/
	public String getMsgId() {
		return msgId;
	}

	/**
	* @param code
	*            the msgId to set
	*/
	public void setMsgId(String msgId) {
		this.msgId = msgId == null ? null : msgId.trim();
	}
}