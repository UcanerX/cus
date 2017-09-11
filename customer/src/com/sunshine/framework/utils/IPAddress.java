/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年9月13日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.framework.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.framework.utils
 * @ClassName: IPAddress
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年9月13日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class IPAddress {
	/**
	 * 获取客户端ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Cdn-Src-Ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.indexOf(",") > -1) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}
}
