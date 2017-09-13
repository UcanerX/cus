/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年4月21日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.msgmanager.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sunshine.common.msgmanager.cache.MsgCenterCache;
import com.sunshine.common.msgmanager.entity.MsgCenter;
import com.sunshine.common.msgmanager.entity.MsgCenterContent;
import com.sunshine.common.msgmanager.entity.MsgCenterFunction;
import com.sunshine.common.msgmanager.entity.MsgCenterRecord;
import com.sunshine.common.msgmanager.service.MsgCenterRecordService;
import com.sunshine.common.msgmanager.service.MsgPushService;
import com.sunshine.common.msgmanager.vo.MsgCenterRecordContentVO;
import com.sunshine.common.msgmanager.vo.MsgCenterRecordFunctionVO;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.common.vo.Response;

/**
 * 消息推送
 * @Project: sunshine_health 
 * @Package: com.sunshine.msgpush.impl
 * @ClassName: MsgPushServiceImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月21日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service
public class MsgPushServiceImpl implements MsgPushService {
	private Logger logger = LoggerFactory.getLogger(MsgPushServiceImpl.class);

	@Override
	public Response msgPush(String hospitalId, String appCode, String eventCode, String userIdentification, Map<String, Serializable> params) {
		Response response = new Response();
		/*
		try {
		
		
			AppUserService appUserService = SpringContextHolder.getBean(AppUserService.class);
			AppUser appUser = appUserService.findByAccount(userIdentification);
			MsgCenterRecord msgCenterRecord = null;
			//用户不存在,并且不是单院版的微信/支付宝渠道.单院版的微信/支付宝渠道由于没有注册,没有账号信息,消息记录不知道插入到哪个账号下面去,所以过滤掉
			if (appUser == null && !GlobalConstant.PLATFORM_TYPE_HOSPITAL_WECHAT.equalsIgnoreCase(appCode)
					&& !GlobalConstant.PLATFORM_TYPE_HOSPITAL_ALIPAY.equalsIgnoreCase(appCode)) {
				logger.error("user does not exist!userIdentification:{}", userIdentification);
				response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
				response.setResultMessage("根据用户账户查询用户信息失败!");
				return response;
			} else if (appUser != null) {
				//医程通体系的渠道记录消息记录,进行短信推送
				//首先将消息记录到消息中心,无论消息是否成功推送
				logger.info("消息中心待保存数据,hospitalId:{},hospitalName:{},eventCode:{},userId:{},params:{}", hospitalId, hospitalAndAppVo.getHospitalName(),
						eventCode, appUser.getUserId(), params);
				msgCenterRecord = saveMsgCenterRecord(hospitalId, hospitalAndAppVo.getHospitalName(), eventCode, appUser.getUserId(), params);
				//短信推送
				smsPush(hospitalId, appCode, eventCode, userIdentification, params);
			}

			//查询推送规则信息
			RuleConfigCache ruleConfigCache = SpringContextHolder.getBean(RuleConfigCache.class);
			RuleMsgPush ruleMsgPush = ruleConfigCache.queryRuleMsgPushByHospitalId(hospitalId, appCode, eventCode);

			//没有查询到规则信息
			if (ruleMsgPush == null) {
				logger.error("pushing rules info does not exist!hospitalId:{},appCode:{},eventCode:{}", hospitalId, appCode, eventCode);
				response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
				response.setResultMessage("根据医院ID,平台编码和事件编码查询医院推送规则信息失败!");
				return response;
			}
			//根据医院平台的不同调用不同的推送渠道
			if (GlobalConstant.PLATFORM_TYPE_YCT_APP.equalsIgnoreCase(appCode)) {
				//手机推送,如果消息中心记录没有生成则不推送
				if (msgCenterRecord != null) {
					//是否需要推送消息
					if (1 == ruleMsgPush.getIsPushMobileNotice()) {
						APPDeviceInfoService appDeviceInfoService = SpringContextHolder.getBean(APPDeviceInfoService.class);
						Map<String, Object> parMap = new HashMap<String, Object>();
						parMap.put("userId", appUser.getUserId());
						APPDeviceInfo appDeviceInfo = appDeviceInfoService.findByUserId(parMap);
						//设备信息不存在
						if (appDeviceInfo == null) {
							logger.error("device information does not exist!hospitalId:{},appCode:{},userId:{},eventCode:{}", hospitalId,
									GlobalConstant.PLATFORM_TYPE_YCT_APP, appUser.getUserId(), eventCode);
							response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
							response.setResultMessage("根据用户ID查询用户设备信息失败!");
							return response;
						}
						msgCenterRecord.setOtherParams(params);
						MsgMobileNoticePushService msgMobileNoticePushService = SpringContextHolder.getBean(MsgMobileNoticePushService.class);
						response = msgMobileNoticePushService.mobileNoticePush(appDeviceInfo.getChannelId(), appDeviceInfo.getDeviceType(), msgCenterRecord);
					} else {
						//消息推送规则指定该事件不需要推送消息
						logger.info("no need to push mobile notice messages!appId:{},appCode:{},eventCode:{},isPushMobileNotice:{}",
								hospitalAndAppVo.getAppId(), appCode, eventCode, ruleMsgPush.getIsPushMobileNotice());
						response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
						response.setResultMessage("消息推送规则指定该事件不需要推送消息!");
					}

				} else {
					logger.error("message center record not generated!hospitalId:{},appCode:{},eventCode:{}", hospitalId, appCode, eventCode);
					response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
					response.setResultMessage("由于消息中心未生成对应的消息记录,手机通知推送失败!");
				}

			} else if (GlobalConstant.PLATFORM_TYPE_YCT_WECHAT.equalsIgnoreCase(appCode)
					|| GlobalConstant.PLATFORM_TYPE_HOSPITAL_WECHAT.equalsIgnoreCase(appCode)) {
				//微信推送

				//是否需要推送消息
				if (1 == ruleMsgPush.getIsPushWechat()) {
					response =
							msgPush(hospitalAndAppVo.getAppId(), hospitalAndAppVo.getPublicKey(), appCode, SDKConstants.PTYPE_WECHAT, userIdentification,
									ruleMsgPush.getPushWechatCode(), params);
				} else {
					//消息推送规则指定该事件不需要推送消息
					logger.info("no need to push wechat messages!appId:{},appCode:{},eventCode:{},isPushWechat:{}", hospitalAndAppVo.getAppId(), appCode,
							eventCode, ruleMsgPush.getIsPushWechat());
					response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
					response.setResultMessage("消息推送规则指定该事件不需要推送消息!");
				}

			} else if (GlobalConstant.PLATFORM_TYPE_YCT_ALIPAY.equalsIgnoreCase(appCode)
					|| GlobalConstant.PLATFORM_TYPE_HOSPITAL_ALIPAY.equalsIgnoreCase(appCode)) {
				//支付宝推送

				//是否需要推送消息
				if (1 == ruleMsgPush.getIsPushAlipay()) {
					response =
							msgPush(hospitalAndAppVo.getAppId(), hospitalAndAppVo.getPrivateKey(), appCode, SDKConstants.PTYPE_ALIPAY, userIdentification,
									ruleMsgPush.getPushAlipayCode(), params);
				} else {
					//消息推送规则指定该事件不需要推送消息
					logger.info("no need to push alipay messages!appId:{},appCode:{},eventCode:{},isPushAlipay:{}", hospitalAndAppVo.getAppId(), appCode,
							eventCode, ruleMsgPush.getIsPushAlipay());
					response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
					response.setResultMessage("消息推送规则指定该事件不需要推送消息!");
				}
			} else {
				//医院不属于任何一个平台
				logger.error("hospital does not belong to any platform!hospitalId:{},appCode:{}", hospitalId, appCode);
				response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
				response.setResultMessage("医院不属于任何一个平台!");
			}
		} catch (Exception e) {
			logger.error("message push exception!", e);
			response.setResultCode(InvokeConstant.REQUEST_INTERFACE_ERROR[0]);
			response.setResultMessage("消息推送异常!");
			e.printStackTrace();
		}*/
		return response;
	}

	@Override
	public Response msgPush(String appId, String appSecret, String appCode, String platformType, String userIdentification, String msgCode,
			Map<String, Serializable> params) {
		Response response = new Response();
		/*
		try {
			MsgTemplateCache msgTemplateCache = SpringContextHolder.getBean(MsgTemplateCache.class);
			boolean isTempalte = msgTemplateCache.existsMsgTemplate(appId, appCode, msgCode);
			//是否是模板消息
			if (isTempalte) {
				MsgTemplate msgTemplate = msgTemplateCache.getMsgTemplate(appId, appCode, msgCode);
				//url参数占位符替换
				if (StringUtils.isNotBlank(msgTemplate.getUrl())) {
					msgTemplate.setUrl(replace(msgTemplate.getUrl(), params));
				}

				//模板内容占位符替换
				if (msgTemplate.getMsgTemplateContents().size() > 0) {
					List<MsgTemplateContent> msgTemplateContents = new ArrayList<MsgTemplateContent>();
					for (MsgTemplateContent msgTemplateContent : msgTemplate.getMsgTemplateContents()) {
						msgTemplateContent.setValue(replace(msgTemplateContent.getValue(), params));
						msgTemplateContents.add(msgTemplateContent);
					}
					msgTemplate.setMsgTemplateContents(msgTemplateContents);
				}

				//推送模板消息
				MsgTemplatePushService msgTemplatePushService = SpringContextHolder.getBean(MsgTemplatePushService.class);
				if (SDKConstants.PTYPE_WECHAT == platformType) {
					//推送微信模板消息
					response = msgTemplatePushService.wechatMsgTemplatePush(appId, appSecret, appCode, userIdentification, msgTemplate);
				} else {
					//推送支付宝模板消息
					response = msgTemplatePushService.alipayMsgTemplatePush(appId, appSecret, appCode, userIdentification, msgTemplate);
				}
			} else {
				MsgCustomerCache msgCustomerCache = SpringContextHolder.getBean(MsgCustomerCache.class);
				boolean isCustomer = msgCustomerCache.existsMsgCustomer(appId, appCode, msgCode);
				//是否是客服消息
				if (isCustomer) {
					MsgCustomer msgCustomer = msgCustomerCache.getMsgCustomer(appId, appCode, msgCode);
					//模板内容占位符替换
					if (StringUtils.isNotBlank(msgCustomer.getMsgContent())) {
						msgCustomer.setMsgContent(replace(msgCustomer.getMsgContent(), params));
					}
					//推送客服消息
					MsgCustomerPushService msgCustomerPushService = SpringContextHolder.getBean(MsgCustomerPushService.class);
					if (SDKConstants.PTYPE_WECHAT == platformType) {
						//推送微信客服消息
						response = msgCustomerPushService.wechatMsgCustomerPush(appId, appSecret, appCode, userIdentification, msgCustomer);
					} else {
						//推送支付宝客服消息
						response = msgCustomerPushService.alipayMsgCustomerPush(appId, appSecret, appCode, userIdentification, msgCustomer);
					}
				} else {
					//消息模板不存在
					logger.error("message template does not exist!appId:{},appCode:{},msgCode:{}", appId, appCode, msgCode);
					response.setResultCode(InvokeConstant.SUCCESS_NOT_LIMIT[0]);
					response.setResultMessage("消息模板不存在!");
				}
			}
		} catch (Exception e) {
			logger.error("message push exception!", e);
			response.setResultCode(InvokeConstant.REQUEST_INTERFACE_ERROR[0]);
			response.setResultMessage("消息推送异常!");
			e.printStackTrace();
		}
		*/
		return response;

	}

	/**
	 * 占位符查找替换
	 * @param value
	 * @param params
	 * @return
	 */
	private String replace(String value, Map<String, Serializable> params) {
		/*
		 * 将占位符替换为真正的内容,提取占位符和填充内容的逻辑如下:
		 * 例如:http://www.baidu.com?hospital=<%>hospital<%>&name=<%>name<%>
		 * 根据<%>符号进行分割,获取<%><%>之间的占位符,分割后的字符串数组如下:
		 * [0]:http://www.baidu.com?hospital=
		 * [1]:hospital
		 * [2]&name=
		 * [3]:name
		 * 分析上面的数组得出,偶数索引是正常的内容,奇数索引则是需要替换为真实内容的占位符(key).
		*/
		String[] vals = value.split("<%>");
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < vals.length; i++) {
			//如果是偶数则是正常的内容
			if (i % 2 == 0) {
				stringBuffer.append(vals[i]);
			} else {
				//如果是奇数则是占位符,进行匹配替换
				if (params.containsKey(vals[i])) {
					stringBuffer.append(params.get(vals[i]));
				}
			}
		}
		return stringBuffer.toString();
	}

	/* (non-Javadoc)
	 * @see com.sunshine.common.msgmanager.service.MsgPushService#saveMsgCenterRecord(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public MsgCenterRecord saveMsgCenterRecord(String hospitalId, String hospitalName, String eventCode, String userId,
			Map<String, Serializable> params) {
		MsgCenterCache msgCenterCache = SpringContextHolder.getBean(MsgCenterCache.class);
		boolean isCenter = msgCenterCache.existsMsgCenter(eventCode);
		if (isCenter) {
			MsgCenter msgCenter = msgCenterCache.getMsgCenter(eventCode);
			List<MsgCenterRecordContentVO> msgCenterRecordContentVOs = new ArrayList<MsgCenterRecordContentVO>();
			//模板内容占位符替换
			if (msgCenter.getMsgCenterContents().size() > 0) {
				List<MsgCenterContent> msgCenterContents = new ArrayList<MsgCenterContent>();
				for (MsgCenterContent msgCenterContent : msgCenter.getMsgCenterContents()) {
					msgCenterContent.setValue(replace(msgCenterContent.getValue(), params));
					msgCenterContents.add(msgCenterContent);
					MsgCenterRecordContentVO msgCenterRecordContentVO =
							new MsgCenterRecordContentVO(msgCenterContent.getSort(), msgCenterContent.getKeyword(), msgCenterContent.getValue(),
									msgCenterContent.getFontColorCode());
					msgCenterRecordContentVOs.add(msgCenterRecordContentVO);
				}
				msgCenter.setMsgCenterContents(msgCenterContents);
			}

			List<MsgCenterRecordFunctionVO> msgCenterRecordFunctionVOs = new ArrayList<MsgCenterRecordFunctionVO>();
			if (msgCenter.getMsgCenterFunctions().size() > 0) {
				List<MsgCenterFunction> msgCenterFunctions = new ArrayList<MsgCenterFunction>();
				for (MsgCenterFunction msgCenterFunction : msgCenter.getMsgCenterFunctions()) {
					msgCenterFunction.setFunctionCode(replace(msgCenterFunction.getFunctionCode(), params));
					msgCenterFunctions.add(msgCenterFunction);
					MsgCenterRecordFunctionVO msgCenterRecordFunctionVO =
							new MsgCenterRecordFunctionVO(msgCenterFunction.getSort(), msgCenterFunction.getFunctionName(),
									msgCenterFunction.getFunctionCode(), msgCenterFunction.getShowType());
					msgCenterRecordFunctionVOs.add(msgCenterRecordFunctionVO);
				}
				msgCenter.setMsgCenterFunctions(msgCenterFunctions);
			}

			//取模板内容的第一条数据,用作消息中心列表展示的展示内容
			MsgCenterRecordContentVO firstCenterRecordContentVO = msgCenterRecordContentVOs.get(0);
			MsgCenterRecord msgCenterRecord =
					new MsgCenterRecord(userId, eventCode, msgCenter.getMsgTitle(), 1, firstCenterRecordContentVO.getValue(),
							JSON.toJSONString(msgCenterRecordContentVOs), JSON.toJSONString(msgCenterRecordFunctionVOs), new Date());
			MsgCenterRecordService msgCenterRecordService = SpringContextHolder.getBean(MsgCenterRecordService.class);
			msgCenterRecordService.insert(msgCenterRecord);
			//logger.info("msgCenter message push success!hospitalId:{},appCode:{},msgCode:{},msgCenterRecordJsonStr:{}", hospitalId,
			//		GlobalConstant.PLATFORM_TYPE_YCT_APP, ruleMsgPush.getPushMsgCenterCode(), JSON.toJSONString(msgCenterRecord));
			return msgCenterRecord;
		}
		return null;
	}

}
