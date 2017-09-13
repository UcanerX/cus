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
package com.sunshine.common.msgmanager.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.common.msgmanager.dao.MsgCenterFunctionDao;
import com.sunshine.common.msgmanager.entity.MsgCenterFunction;
import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;

/**
 * 个人中心消息功能Dao
 * @Project: sunshine_health
 * @Package: com.sunshine.platform.msgmanager.dao.impl
 * @ClassName: MsgCenterFunctionDaoImpl
 * @Statement: <p>
 *             </p>
 * @JDK version used:
 * @Author: 申姜
 * @Create Date: 2016-4-11
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Repository
public class MsgCenterFunctionDaoImpl extends BaseDaoImpl<MsgCenterFunction, String> implements MsgCenterFunctionDao {
	private Logger logger = LoggerFactory.getLogger(MsgCenterFunctionDaoImpl.class);
	private final static String SQLNAME_DELETE_BY_MSG_ID = "deleteByMsgId";
	private final static String SQLNAME_FIND_BY_MSG_ID = "findByMsgId";

	@Override
	public void deleteByMsgId(String msgId) {
		try {
			Assert.notNull(msgId);
			sqlSession.delete(getSqlName(SQLNAME_DELETE_BY_MSG_ID), msgId);
		} catch (Exception e) {
			logger.error(String.format("根据msgId删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE_BY_MSG_ID)), e);
			throw new SystemException(String.format("根据msgId删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE_BY_MSG_ID)), e);
		}
	}

	@Override
	public List<MsgCenterFunction> findByMsgId(String msgId) {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_BY_MSG_ID), msgId);
		} catch (Exception e) {
			logger.error(String.format("根据msgId查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_BY_MSG_ID)), e);
			throw new SystemException(String.format("根据msgId查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_BY_MSG_ID)), e);
		}
	}
}