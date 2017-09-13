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
 * @ClassName: MsgCenterFunctionVo
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class MsgCenterRecordFunctionVO {
	/**
	 * 功能排序号
	 */
	private Integer sort;

	/**
	 * 功能名称
	 */
	private String functionName;

	/**
	 * JS函数名称或者URL地址
	 */
	private String functionCode;

	/**
	 * 1:超链接,2:按钮
	 */
	private Integer showType;

	public MsgCenterRecordFunctionVO() {
		super();
	}

	/**
	 * @param sort
	 * @param functionName
	 * @param functionCode
	 * @param showType
	 */
	public MsgCenterRecordFunctionVO(Integer sort, String functionName, String functionCode, Integer showType) {
		super();
		this.sort = sort;
		this.functionName = functionName;
		this.functionCode = functionCode;
		this.showType = showType;
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
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * @return the functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * @param functionCode the functionCode to set
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * @return the showType
	 */
	public Integer getShowType() {
		return showType;
	}

	/**
	 * @param showType the showType to set
	 */
	public void setShowType(Integer showType) {
		this.showType = showType;
	}

}
