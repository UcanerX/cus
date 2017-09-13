/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年4月25日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.msgmanager.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.common.msgmanager.dao.MsgCenterRecordDao;
import com.sunshine.common.msgmanager.entity.MsgCenterRecord;
import com.sunshine.common.msgmanager.service.MsgCenterRecordService;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;

/**
 * @Project: sunshine_health 
 * @Package: com.sunshine.mobileapp.biz.msgcenter.service.impl
 * @ClassName: MsgCenterRecordServiceImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service
public class MsgCenterRecordServiceImpl extends BaseServiceImpl<MsgCenterRecord, String> implements MsgCenterRecordService {
	private static Logger logger = LoggerFactory.getLogger(MsgCenterServiceImpl.class);
	@Autowired
	private MsgCenterRecordDao msgCenterRecordDao;

	@Override
	protected BaseDao<MsgCenterRecord, String> getDao() {
		return msgCenterRecordDao;
	}

	@Override
	public MsgCenterRecord findById(String userId, String id) {
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("userId", userId);
		params.put("id", id);
		return msgCenterRecordDao.findById(params);
	}

	@Override
	public void updateIsRead(String userId, String id, Integer isRead) {
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("userId", userId);
		params.put("id", id);
		params.put("isRead", isRead);
		msgCenterRecordDao.updateIsRead(params);

	}

	@Override
	public Integer findCountByIsRead(String userId, Integer isRead) {
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("userId", userId);
		params.put("isRead", isRead);
		return msgCenterRecordDao.findCountByIsRead(params);
	}

	@Override
	public List<MsgCenterRecord> findThreeListByUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			return new ArrayList<MsgCenterRecord>();
		}
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("userId", userId);
		return msgCenterRecordDao.findThreeListByUser(params);
	}
}
