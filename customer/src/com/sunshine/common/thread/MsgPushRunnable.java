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
package com.sunshine.common.thread;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunshine.common.msgmanager.service.MsgPushService;
import com.sunshine.common.vo.MessagePushParamsVo;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

/**
 * @Package: com.yxw.mobileapp.common.thread
 * @ClassName: MessagePushRunnable
 * @Statement: <p>消息推送处理线程</p>
 * @JDK version used: 1.6
 * @Author: Yuce
 * @Create Date: 2015-7-13
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class MsgPushRunnable implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(MsgPushRunnable.class);
	private MsgPushService msgPush = SpringContextHolder.getBean(MsgPushService.class);

	private MessagePushParamsVo params;

	public MsgPushRunnable(MessagePushParamsVo params) {
		super();
		this.params = params;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		//logger.info("MessagePushRunnable is start.messageParams:{}", JSON.toJSONString(params));
		Map<String, Serializable> paramMap = params.getParamMap();

		String account = params.getAccount();

		//String eventCode = params.getEventCode();
		logger.info("orderNo:{},scheduleDateLable:{}", paramMap.get("orderNo"), paramMap.get("scheduleDateLable"));
		//msgPush.msgPush(params.getHospitalId(), params.getAppCode(), eventCode, account, paramMap);
	}
}
