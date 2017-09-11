/**
 * <html>
 * <body>
 *  <P> Copyright 2014-2017 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年8月31日</p>
 *  <p> Created by wumingzi/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project YiChenTong_Server
 * @Package com.sunshine.mobileapp.api.utils
 * @ClassName ValidationUtil.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年8月31日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
public class ValidationUtil {
	public static final String CHECK_IS_VALID = "isValid";
	public static final String CHECK_RES_MSG = "checkResMsg";
	public static final String NOT_BLANK = "不能为空!";

	public static Map<String, Object> notBlank(Object obj, String... propertyNames) {
		Map<String, Object> resMap = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		for (String propertyName : propertyNames) {
			Object val = ReflectionUtils.invokeGetterMethod(obj, propertyName);
			if (val == null || "".equalsIgnoreCase(val.toString())) {
				sb.append(propertyName).append(NOT_BLANK);
			}
		}

		if (sb.length() > 0) {
			resMap.put(CHECK_IS_VALID, false);
			resMap.put(CHECK_RES_MSG, sb.toString());
		} else {
			resMap.put(CHECK_IS_VALID, true);
		}
		return resMap;
	}
}
