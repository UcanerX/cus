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

public class Province extends BaseSQLEntity {

	private static final long serialVersionUID = -4409533030702279992L;

	/**
	 * 省份代码
	 */
	private String provinceCode;

	/**
	 * 省份名称
	 */
	private String provinceName;

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
	* @return the provinceName
	*/
	public String getProvinceName() {
		return provinceName;
	}

	/**
	* @param code
	*            the provinceName to set
	*/
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName == null ? null : provinceName.trim();
	}
}