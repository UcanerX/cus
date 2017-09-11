package com.sunshine.common;

import com.sunshine.framework.config.SystemConfig;

/**
 * @Package: com.sunshine.mobileapp.biz
 * @ClassName: BizConstant
 * @Statement:
 * 			<p>
 *             易健通系统定义的公共常量
 *             </p>
 * @JDK version used: 1.6
 * @Author: Yuce
 * @Create Date: 2015-6-9
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DoctorConstant {
	/**
	 * 页面与控制层传值对象的标示
	 */
	public final static String DOCTOR_HTTP_PARAMS_VO = "httpParams";
	public final static String DOCTOR_ENTITY_LIST = "entityList";
	public final static String DOCTOR_ENTITY = "entity";
	public final static String DOCTOR_USER_APPLY_ENTITY = "applyEntity";
	public final static String DOCTOR_PROVINCE_LIST = "entityList";
	public final static String DOCTOR_CITY_MAP = "entityMap";

	/**
	 * 医生登录信息标示
	 */
	public final static String DOCTOR_LOGIN_INFO = "doctorLoginInfo";

	/**
	 * 保存在cookies中的登录账号信息键值常量
	 */
	public final static String COOKIES_LOGINACCOUNT_KEY = "cookiesLoginAccount";

	/**
	 * 验证码签名
	 */
	public static final String VALIDATION_CODE_SIGNATURE = SystemConfig.getStringValue("validation_code_signature");

	// >>>>>>>>>>>>>>> 开通状态>>>>>>>>>>>>>>>>>>>>>>>
	/** 未开通 */
	public static final int NOT_OPENED_STATUS = 0;
	/** 开通 */
	public static final int OPENED_STATUS = 1;

	// >>>>>>>>>>>>>>> 认证申请审核>>>>>>>>>>>>>>>>>>>>>>>
	/** 未申请 */
	public static final int APPLY_STATUS_NOT_APPLY = 0;
	/** 审核中 */
	public static final int APPLY_STATUS_AUDITING = 1;
	/** 通过审核 */
	public static final int APPLY_STATUS_AUDITED = 2;
	/** 审核不通过 */
	public static final int APPLY_STATUS_NOT_PASS = 3;
	/** 基本资料填写完成 */
	public static final int APPLY_STATUS_BASE_INFO = 4;

	// >>>>>>>>>>>>>>> 进入选择医院入口类型>>>>>>>>>>>>>>>>>>>>>>>
	/** 认证申请 */
	public static final int ENTRANCE_TYPE_APPLY = 1;
	/** 执业点 */
	public static final int ENTRANCE_TYPE_PRACTICE_POINT = 2;
	/** 我的资料 */
	public static final int ENTRANCE_TYPE_MY_DATA = 3;

	// >>>>>>>>>>>>>>> 进入认证申请入口类型>>>>>>>>>>>>>>>>>>>>>>>
	/** 医生编辑资料 或其它页面*/
	public static final int APPLY_ENTRANCE_TYPE_EDITDATA = 0;
	/** 医生注册后 */
	public static final int APPLY_ENTRANCE_TYPE_REGISTER = 1;
	/** 服务介绍跳转认真页面 */
	public static final int APPLY_ENTRANCE_TYPE_SERVICE_INTRODUCE = 2;

}
