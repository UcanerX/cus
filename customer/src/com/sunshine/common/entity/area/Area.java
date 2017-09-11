/**
* <html>
*  <body>
*   <P> Copyright 2014 �㶫�������⿵��ҽ��Ͷ�ʹ������޹�˾. ��ICP��09007530��-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016��4��26��</p>
*   <p> Created by ����</p>
*  </body>
* </html>
*/
package com.sunshine.common.entity.area;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

public class Area extends BaseSQLEntity {

	private static final long serialVersionUID = -1719166303954581445L;

	/**
	 * 城市代码
	 */
	private String cityCode;

	/**
	 * 区县代码
	 */
	private String areaCode;

	/**
	 * 区县名称
	 */
	private String areaName;

	/**
	* @return the cityCode
	*/
	public String getCityCode() {
		return cityCode;
	}

	/**
	* @param code
	*            the cityCode to set
	*/
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode == null ? null : cityCode.trim();
	}

	/**
	* @return the areaCode
	*/
	public String getAreaCode() {
		return areaCode;
	}

	/**
	* @param code
	*            the areaCode to set
	*/
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode == null ? null : areaCode.trim();
	}

	/**
	* @return the areaName
	*/
	public String getAreaName() {
		return areaName;
	}

	/**
	* @param code
	*            the areaName to set
	*/
	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}
}