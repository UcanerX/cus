/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017-6-27</p>
 *  <p> Created by sun001</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.utils;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.service.order
 * @ClassName: WeekDay
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: sun001
 * @Create Date: 2017-6-27
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public enum WeekDay {
	Monday("monday"), Tuesday("tuesday"), Wednesday("wednesday"), Thursday("thursday"), Friday("friday"), Saturday("saturday"), Sunday("sunday");
	
	private String code;
	private WeekDay(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
