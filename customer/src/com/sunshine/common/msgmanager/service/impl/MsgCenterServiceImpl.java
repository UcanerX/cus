/**
* <html>
*  <body>
*   <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016年4月11日</p>
*   <p> Created by 姓名</p>
*  </body>
* </html>
*/
package com.sunshine.common.msgmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunshine.common.msgmanager.cache.MsgCenterCache;
import com.sunshine.common.msgmanager.dao.MsgCenterContentDao;
import com.sunshine.common.msgmanager.dao.MsgCenterDao;
import com.sunshine.common.msgmanager.dao.MsgCenterFunctionDao;
import com.sunshine.common.msgmanager.entity.MsgCenter;
import com.sunshine.common.msgmanager.entity.MsgCenterContent;
import com.sunshine.common.msgmanager.entity.MsgCenterFunction;
import com.sunshine.common.msgmanager.service.MsgCenterService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;

public class MsgCenterServiceImpl extends BaseServiceImpl<MsgCenter, String> implements MsgCenterService {
	private Logger logger = LoggerFactory.getLogger(MsgCenterServiceImpl.class);

	@Autowired
	private MsgCenterDao msgCenterDao;
	@Autowired
	private MsgCenterContentDao msgCenterContentDao;
	@Autowired
	private MsgCenterFunctionDao msgCenterFunctionDao;

	@Override
	protected BaseDao<MsgCenter, String> getDao() {
		return this.msgCenterDao;
	}

	@Override
	public String insert(MsgCenter entity) {
		MsgCenterCache msgCenterCache = SpringContextHolder.getBean(MsgCenterCache.class);
		String msgId = msgCenterDao.insert(entity);
		List<MsgCenterContent> msgCenterContents = entity.getMsgCenterContents();
		Date date = new Date();
		for (MsgCenterContent msgCenterContent : msgCenterContents) {
			msgCenterContent.setMsgId(msgId);
			msgCenterContent.setCp(entity.getCp());
			msgCenterContent.setCpName(entity.getCpName());
			msgCenterContent.setCt(date);
			msgCenterContent.setEp(entity.getEp());
			msgCenterContent.setEpName(entity.getEpName());
			msgCenterContent.setEt(date);
		}
		msgCenterContentDao.batchInsert(msgCenterContents);
		List<MsgCenterFunction> msgCenterFunctions = entity.getMsgCenterFunctions();
		for (MsgCenterFunction msgCenterFunction : msgCenterFunctions) {
			msgCenterFunction.setMsgId(msgId);
			msgCenterFunction.setCp(entity.getCp());
			msgCenterFunction.setCpName(entity.getCpName());
			msgCenterFunction.setCt(date);
			msgCenterFunction.setEp(entity.getEp());
			msgCenterFunction.setEpName(entity.getEpName());
			msgCenterFunction.setEt(date);
		}
		msgCenterFunctionDao.batchInsert(msgCenterFunctions);
		msgCenterCache.updateMsgCenter(entity);
		return null;
	}

	@Override
	public void update(MsgCenter entity) {
		MsgCenterCache msgCenterCache = SpringContextHolder.getBean(MsgCenterCache.class);
		msgCenterDao.update(entity);
		List<MsgCenterContent> msgCenterContents = entity.getMsgCenterContents();
		msgCenterContentDao.deleteByMsgId(entity.getId());
		Date date = new Date();
		for (MsgCenterContent msgCenterLibraryContent : msgCenterContents) {
			msgCenterLibraryContent.setMsgId(entity.getId());
			msgCenterLibraryContent.setCp(entity.getCp());
			msgCenterLibraryContent.setCpName(entity.getCpName());
			msgCenterLibraryContent.setCt(date);
			msgCenterLibraryContent.setEp(entity.getEp());
			msgCenterLibraryContent.setEpName(entity.getEpName());
			msgCenterLibraryContent.setEt(date);
		}
		msgCenterContentDao.batchInsert(msgCenterContents);
		msgCenterFunctionDao.deleteByMsgId(entity.getId());
		List<MsgCenterFunction> msgCenterFunctions = entity.getMsgCenterFunctions();
		for (MsgCenterFunction msgCenterFunction : msgCenterFunctions) {
			msgCenterFunction.setMsgId(entity.getId());
			msgCenterFunction.setCp(entity.getCp());
			msgCenterFunction.setCpName(entity.getCpName());
			msgCenterFunction.setCt(date);
			msgCenterFunction.setEp(entity.getEp());
			msgCenterFunction.setEpName(entity.getEpName());
			msgCenterFunction.setEt(date);
		}
		msgCenterFunctionDao.batchInsert(msgCenterFunctions);
		msgCenterCache.updateMsgCenter(entity);
	}

}