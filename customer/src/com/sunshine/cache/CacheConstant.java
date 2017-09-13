/**
 * <html>
 * <body>
 *  <P> Copyright 2017 阳光康众</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年7月1日</p>
 *  <p> Created by 于策/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.cache;

import org.apache.commons.lang.StringUtils;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.sunshine.cache
 * @ClassName CacheConstant.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年7月1日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
public class CacheConstant {
	/**
	 * 缓存key的分割符
	 */
	public static final String CACHE_KEY_SPLIT_CHAR = ":";

	/**
	 * key模糊匹配通配符
	 */
	public static final String CACHE_KEY_PATTERN_CHAR = "*";
	/**
	 * 缓存新增
	 */
	public static final String CACHE_OP_ADD = "cache_add";

	/**
	 * 缓存删除
	 */
	public static final String CACHE_OP_DEL = "cache_del";

	/**
	 * 缓存更新
	 */
	public static final String CACHE_OP_UPDATE = "cache_update";

	/**
	 * 标记为null的值
	 */
	public static final String CACHE_NULL_STRING = "null";

	/**
	 * redis 缓存key不存在返回的标志
	 */
	public static final String CACHE_KEY_NOT_EXIST = "nil";

	/**
	 * redis分布式锁 锁key的后缀
	 */
	public static final String REDIS_LOCK_KEY_PREFIX = "_lock";

	/**
	 * 一小时共计的秒数
	 */
	public static final int ONE_HOUR_SECONDS = 3600;
	/**
	 * 一天共计的秒数
	 */
	public static final int ONE_DAY_SECONDS = 86400;

	/**
	 * 7天共计的秒数
	 */
	public static final int SEVEN_DAY_SECONDS = 604800;

	/**
	 * 30天共计的秒数
	 */
	public static final int SEVEN_MONTH_SECONDS = 2592000;

	/**
	 * 3 * 60 * 1000 = 180000毫秒
	 */
	public static final int REDIS_LOCKED_DEF_TIME = 180000;

	/**
	 * 判断缓存是否有值
	 * 
	 * @param jsonData
	 * @return true 有值 <br>
	 *         false 没有值
	 */
	public static boolean isHadValue(String jsonData) {
		boolean isHadValue = false;
		if (StringUtils.isNotBlank(jsonData) && !CacheConstant.CACHE_KEY_NOT_EXIST.equalsIgnoreCase(jsonData)) {
			isHadValue = true;
		}
		return isHadValue;
	}

	public static final Integer HAD_INIT_YES = 1;
	public static final Integer HAD_INIT_NO = 0;
	public static final String DATA_CACHE_VERSION = "data.cache.version";

	public enum CacheKeys {
		/**
		 * 资源主子关系
		 */
		RESOURCE_PARENT_CACHE_KEY("reource.parent"),
		/**
		 * 角色与资源关系
		 */
		ROLE_RESOURCE_CACHE_KEY("role.resource");
		private String cacheKey;

		private CacheKeys(String cacheKey) {
			this.cacheKey = cacheKey;
		}

		public String getCacheKey() {
			return cacheKey;
		}

		public void setCacheKey(String cacheKey) {
			this.cacheKey = cacheKey;
		}

		@Override
		public String toString() {
			return getCacheKey();
		}
	}

	public enum CacheFields {
		/**
		 * 资源主子关系
		 */
		RESOURCE_PARENT_PREFIX("parentId:"),
		/**
		 * 角色与资源关系
		 */
		ROLE_RESOURCE_PREFIX("roleId:");
		private String cacheField;

		private CacheFields(String cacheField) {
			this.cacheField = cacheField;
		}

		public String getCacheField() {
			return cacheField;
		}

		public void setCacheField(String cacheField) {
			this.cacheField = cacheField;
		}

		@Override
		public String toString() {
			return getCacheField();
		}
	}

	/**
	 * 医院省、市、区县缓存前缀
	 */
	public static final String CACHE_HOSPITAL_AREA_KEY_PREFIX = "hospital.area.infos";

	/**
	 * 咨询统计  
	 */
	public static final String PATIENT_INQUIRY_STATISTIC_PREFIX = "patient.inquiry.statistic";

	/**
	 * 订单计数
	 */
	public static final String PATIENT_ORDER_COUNT = "patient.order.count";

	/**
	 * 医生订单计数
	 */
	public static final String DOCTOR_ORDER_COUNT = "doctor.order.count";

	/**
	 * 医生服务列表缓存
	 */
	public static final String CACHE_DOCTOR_SERVICE_LIST_KEY_PREFIX = "doctor.service.list.infos";

	/**
	 * 医生消息
	 */
	public static final String DOCTOR_MSG_COUNT_KEY_PREFIX = "doctor.msg.count";

	/**
	 * 短信提醒的订单信息缓存
	 */
	public static final String MSG_PROMPT_ORDER_KEY_PREFIX = "order.msg.prompt";

	/**
	 * 电话绑定关系缓存前缀
	 */
	public static final String PHONE_CALL_BIND_KEY_PREFIX = "phone.call.bind.infos";

	/**
	 * 电话管理订单缓存前缀
	 */
	public static final String PHONE_CALL_ASSOCIATED_ORDER_KEY_PREFIX = "phone.call.associated.orderno";

	/**
	 * 电话咨询服务设置缓存
	 */
	public static final String CACHE_DOCTOR_SERVICE_SETTING_CONSULT_KEY_PREFIX = "doctor.service.consult.setting";

	/**
	 * 新订单提示缓存
	 */
	public static final String PROMPT_NEW_ORDER_KEY_PREFIX = "order.new.prompt";

	/**
	 * 医生可用排班时间缓存
	 */
	public static final String DOCTOR_AVAILABLE_TIME_KEY_PREFIX = "doctor.availabletime.infos";

	/**
	 * 医生剩余预约时间缓存
	 */
	public static final String DOCTOR_APPOINTMENT_TIME_KEY_PREFIX = "doctor.appointment.time.info";

	/**
	 * 系统服务列表项缓存
	 */
	public static final String SYS_SERVICE_LIST_KEY_PREFIX = "sys.service.infos";

	/**
	 * 医生昵称缓存key
	 */
	public static final String DOCTOR_USER_ALIAS_NAME_KEY_PREFIX = "doctor.user.alias.name.infos";

	/**
	 * 个人中心消息:缓存hash结构的cachekey 前缀
	 */
	public static final String CACHE_MSG_CENTER_KEY_FREFIX = "msg.center.hash";

	/**
	 * 问诊人填写相关的临时缓存
	 */
	public static final String INQUIRE_USER_TEMP_KEY_PREFIX = "inquire.user.temp.infos";

}
