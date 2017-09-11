/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年6月30日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.common;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common
 * @ClassName: MessageCentorConstant
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月30日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class MessageCentorConstant {

	/**
	 * 未读消息
	 */
	public static final int UNREAD_MESSAGE=1;
	
	/**
	 *  已读消息
	 */
	public static final int HAS_READ_MESSAGE=2;
	
	
	/**
	 * 消息类别- 服务消息
	 */
	public static final int SERVICE_MESSAGE=1;
	
	/**
	 * 消息类别- 系统消息
	 */
	public static final int SYSTEM_MESSAGE=2;
	
	/**
	 * 
	 */
	public static final String SERVICE_MESSAGE_CATEGORY="service";
	
	/**
	 * 
	 */
	public static final String SYSTEM_MESSAGE_CATEGORY="system";
	
	/**
	 * 医生图文咨询code
	 */
	public static final String YS_TWZX_MESSAGE_CODE="YS_TWZX";
	
	/**
	 * 医生电话咨询code
	 */
	public static final String YS_DHZX_MESSAGE_CODE="YS_DHZX";
	
	/**
	 * 医生电话服务code 消息提醒
	 */
	public static final String YS_DHFW_MESSAGE_CODE="YS_DHFW";
	
	/**
	 * 医生认证通过code
	 */
	public static final String YS_RZTG_MESSAGE_CODE="YS_RZTG";
	
	/**
	 * 医生认证失败code
	 */
	public static final String YS_RZSB_MESSAGE_CODE="YS_RZSB";
	
}
