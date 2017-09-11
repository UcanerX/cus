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

public class City extends BaseSQLEntity {

	private static final long serialVersionUID = -647316827812834733L;

	/**
	 * 省份代码
	 */
	private String provinceCode;

	/**
	 * 城市代码
	 */
	private String cityCode;

	/**
	 * 城市名称
	 */
	private String cityName;

	/**
	* @return the provinceCode
	*/
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	* @param code
	*            the provinceCode to set
	*/
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode == null ? null : provinceCode.trim();
	}

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
	* @return the cityName
	*/
	public String getCityName() {
		return cityName;
	}

	/**
	* @param code
	*            the cityName to set
	*/
	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}
}