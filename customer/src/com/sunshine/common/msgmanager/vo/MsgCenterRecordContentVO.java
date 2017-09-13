/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年4月25日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.msgmanager.vo;

/**
 * @Project: sunshine_health 
 * @Package: com.sunshine.common.vo
 * @ClassName: MsgCenterContentVO
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class MsgCenterRecordContentVO {
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

	public MsgCenterRecordContentVO() {
		super();
	}

	/**
	 * @param sort
	 * @param keyword
	 * @param value
	 * @param fontColorCode
	 */
	public MsgCenterRecordContentVO(Integer sort, String keyword, String value, String fontColorCode) {
		super();
		this.sort = sort;
		this.keyword = keyword;
		this.value = value;
		this.fontColorCode = fontColorCode;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
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
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
		this.fontColorCode = fontColorCode;
	}

}
