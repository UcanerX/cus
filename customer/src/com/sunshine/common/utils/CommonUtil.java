/**
 * <html>
 * <body>
 *  <P>  Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p>  All rights reserved.</p>
 *  <p> Created on 2016-4-8</p>
 *  <p> Created by 无名子</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.sunshine.framework.config.SystemConfig;

/**
 * @Package com.sunshine.common.utils
 * @ClassName CommonUtil
 * @Statement
 * 			<p>
 *            </p>
 * @JDK version used: 1.7
 * @Author: 无名子
 * @Create Date: 2016-4-8
 * @modify-Author:
 * @modify-Date:
 * @modify-Why/What:
 * @Version 1.0
 */
public class CommonUtil {
	private static String SYSTEM_REQUEST_PATH = "";

	/**
	 * 获取系统request Scheme
	 * 
	 * @return
	 */
	public static String getRequestContextPath(HttpServletRequest request) {
		if (StringUtils.isBlank(SYSTEM_REQUEST_PATH)) {
			String requestScheme = SystemConfig.getStringValue("system.request.scheme", "https://");
			SYSTEM_REQUEST_PATH = requestScheme + request.getServerName()
					+ (80 == request.getServerPort() ? "" : ":" + request.getServerPort());
		}
		return SYSTEM_REQUEST_PATH.concat(request.getContextPath());
	}
}
