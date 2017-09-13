/**
 * <html>
 * <body>
 *  <P>  Copyright 2017 阳光康众</p>
 *  <p>  All rights reserved.</p>
 *  <p> Created on 2016-4-6</p>
 *  <p> Created by 于策</p>
 *  </body>
 * </html>
 */
package com.sunshine.common;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.sunshine.framework.config.SystemConfig;
import com.sunshine.framework.utils.DateUtils;

/**
 * @Package com.sunshine.common
 * @ClassName GlobalConstant
 * @Statement
 *            <p>
 *            全局常量
 *            </p>
 * @JDK version used: 1.7
 * @Author: 于策
 * @Create Date: 2016-4-6
 * @modify-Author:
 * @modify-Date:
 * @modify-Why/What:
 * @Version 1.0
 */
public class GlobalConstant {

	public final static String SESSION_PLATFORM_USER_ACCOUNT_KEY = "userAccount";

	/**
	 * 保存在session中的后台管理 用户登录信息键值常量
	 */
	public final static String SESSION_PLATFORM_USER_KEY = "platformUser";

	/**
	 * 保存在session中的前端管理 用户登录信息键值常量
	 */
	public final static String SESSION_MOBILEAPP_USER_KEY = "mobileAppUser";

	/*** 启动版本定义参数 0开发 1测试 2正式 ***/
	public static String SYSTEM_PUBLISH_VERSION_RC = "2";
	public static String SYSTEM_PUBLISH_VERSION_TEST = "1";
	public static String SYSTEM_PUBLISH_VERSION_DEV = "0";

	public static String STRING_SPLIT_CHAR = ",";

	/**
	 * 状态有效
	 */
	public static final int STATUS_VALID = 1;
	/**
	 * 状态无效
	 */
	public static final int STATUS_INVALID = 0;

	public static final int YES = 1;
	public static final int NO = 0;

	public static final String MOTHED_INVOKE_RES_ENTITY_ID = "entityId";
	public static final String MOTHED_INVOKE_RES_ENTITY = "entity";
	public static final String MOTHED_INVOKE_RES_ENTITIES = "entities";
	public static final String MOTHED_INVOKE_RES_MSG = "msg";
	public static final String MOTHED_INVOKE_RES_IS_SUCCESS = "isSuccess";

	public static final int SERVICE_TYPE_PHONE_CALL = 0;

	public static final int SERVICE_TYPE_TEXT_IMAGE = 0;

	/************************ 订单流水号缓存类型key start **************************/

	public static final String EH_COMMON_ORDER_SERIAL_NUMBER = "easyhealth_common_order_serial_number";

	public static final String EH_MEDICARE_ORDER_SERIAL_NUMBER = "easyhealth_medicare_order_serial_number";
	
	/************************ 订单流水号缓存类型key end **************************/

	/**
	 * @Description 获取服务端版本号
	 * @return
	 * @date 2017年7月3日
	 */
	public static String getServerVersion() {
		String server_version = null;
		String publish_version = SystemConfig.getStringValue("system.publish.version");
		if (StringUtils.isNotBlank(publish_version) && SYSTEM_PUBLISH_VERSION_RC.equalsIgnoreCase(publish_version)) {
			server_version = SystemConfig.getStringValue("server.version", DateUtils.getCurrentDate());
		} else {
			server_version = DateUtils.getCurrentTime();
		}
		return server_version;
	}

	/**
	 * 中文日期 yyyy-MM-dd E
	 */
	public static String formatYYYYMMDDE(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd E", Locale.CHINESE);
		return faseDF.format(date);
	}

	/**
	 * 中文日期 yyyy-MM-dd E
	 */
	public static Date parseYYYYMMDDE(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd E", Locale.CHINESE);
		return faseDF.parse(dateStr);
	}

	/**
	 * yyyy-MM-dd
	 */
	public static String formatYYYYMMDD(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd");
		return faseDF.format(date);
	}

	/**
	 * yyyyMMdd
	 */
	public static String formatyyyymmdd(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyyMMdd");
		return faseDF.format(date);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String formatYYYYMMDDHHMMSS(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		return faseDF.format(date);
	}

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String formatYYYYMMDDHHMM(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
		return faseDF.format(date);
	}

	/**
	 * yyyyMMddHHmm
	 */
	public static String formatyyyymmddhhmm(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyyMMddHHmm");
		return faseDF.format(date);
	}

	/**
	 * HH:mm
	 */
	public static String formatHHMM(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("HH:mm");
		return faseDF.format(date);
	}

	/**
	 * HHmm
	 */
	public static String formathhmm(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("HHmm");
		return faseDF.format(date);
	}

	/**
	 * MMddHHmmss
	 */
	public static String formatmmddhhmmss(Date date) {
		FastDateFormat faseDF = FastDateFormat.getInstance("MMddHHmmss");
		return faseDF.format(date);
	}

	/**
	 * yyyy-MM-dd
	 */
	public static Date parseYYYYMMDD(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd");
		return faseDF.parse(dateStr);
	}

	/**
	 * yyyyMMdd
	 */
	public static Date parseyyyymmdd(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyyMMdd");
		return faseDF.parse(dateStr);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static Date parseYYYYMMDDHHMMSS(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		return faseDF.parse(dateStr);
	}

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static Date parseYYYYMMDDHHMM(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
		return faseDF.parse(dateStr);
	}

	/**
	 * yyyyMMddHHmm
	 */
	public static Date parseyyyymmddhhmm(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("yyyyMMddHHmm");
		return faseDF.parse(dateStr);
	}

	/**
	 * HH:mm
	 */
	public static Date parseHHMM(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("HH:mm");
		return faseDF.parse(dateStr);
	}

	/**
	 * HHmm
	 */
	public static Date parsehhmm(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("HHmm");
		return faseDF.parse(dateStr);
	}

	/**
	 * MMddHHmmss
	 */
	public static Date parsemmddhhmmss(String dateStr) throws ParseException {
		FastDateFormat faseDF = FastDateFormat.getInstance("MMddHHmmss");
		return faseDF.parse(dateStr);
	}
}
