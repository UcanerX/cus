/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年4月20日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.msgmanager.service;

import java.io.Serializable;
import java.util.Map;

import com.sunshine.common.msgmanager.entity.MsgCenterRecord;
import com.sunshine.framework.common.vo.Response;

/**
 * 消息推送接口
 * @Project: sunshine_health 
 * @Package: com.sunshine.msgpush
 * @ClassName: MsgPushService
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月20日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface MsgPushService {

	/**
	 * 消息推送(微信/支付宝模板消息,客服消息,手机通知,短信消息推送)
	 * @param hospitalId 医院ID
	 * @param appCode 平台编码
	 * @param eventCode 事件编码
	 * @param userIdentification 微信/支付宝用户ID,用户唯一标识
	 * @param paramMap 待替换的参数
	 */
	public abstract Response msgPush(String hospitalId, String appCode, String eventCode, String userIdentification,
			Map<String, Serializable> paramMap);

	/**
	 * 模板和客服消息推送
	 * @param appId
	 * @param appSecret
	 * @param appCode
	 * @param platformType
	 * @param userIdentification
	 * @param msgCode
	 * @param params
	 */
	public abstract Response msgPush(String appId, String appSecret, String appCode, String platformType, String userIdentification, String msgCode,
			Map<String, Serializable> params);

	/**
	 * 保存消息到消息中心
	 * @param hospitalId
	 * @param hospitalName
	 * @param eventCode
	 * @param appCode
	 * @param userId
	 * @param params
	 * @return
	 */
	public abstract MsgCenterRecord saveMsgCenterRecord(String hospitalId, String hospitalName, String eventCode, String userId,
			Map<String, Serializable> params);
}
