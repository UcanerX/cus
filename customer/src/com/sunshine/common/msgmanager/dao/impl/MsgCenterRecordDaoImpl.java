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
package com.sunshine.common.msgmanager.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunshine.common.msgmanager.dao.MsgCenterRecordDao;
import com.sunshine.common.msgmanager.entity.MsgCenterRecord;
import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;

/**
 * 消息中心dao实现类
 * @Project: sunshine_health 
 * @Package: com.sunshine.common.dao.msgcenter.impl
 * @ClassName: MsgCenterRecordDaoImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Repository
public class MsgCenterRecordDaoImpl extends BaseDaoImpl<MsgCenterRecord, String> implements MsgCenterRecordDao {

	private static Logger logger = LoggerFactory.getLogger(MsgCenterRecordDaoImpl.class);
	private static final String SQLNAME_FIND_LIST_BY_PAGE = "findListByPage";
	private static final String SQL_FIND_BY_ID = "findById";
	private static final String SQL_UPDATE_IS_READ = "updateIsRead";
	private static final String SQL_FIND_COUNT_BY_IS_READ = "findCountByIsRead";
	private static final String SQL_FIND_THREELIST_BY_USER = "findThreeListByUser";

	@Override
	public MsgCenterRecord findById(Map<String, Serializable> params) {
		try {
			//params.put("hashTableName", SimpleHashTableNameGenerator.getMsgCenterRecordHashTable(String.valueOf(params.get("userId"))));
			return sqlSession.selectOne(getSqlName(SQL_FIND_BY_ID), params);
		} catch (Exception e) {
			logger.error(String.format("根据ID查询对象出错！语句：%s", getSqlName(SQL_FIND_BY_ID)), e);
			throw new SystemException(String.format("根据ID查询对象出错！语句：%s", getSqlName(SQL_FIND_BY_ID)), e);
		}
	}

	@Override
	public PageInfo<MsgCenterRecord> findListByPage(Map<String, Object> params, Page<MsgCenterRecord> page) {
		try {
			PageHelper.startPage(page.getPageNum(), page.getPageSize());
			//params.put("hashTableName", SimpleHashTableNameGenerator.getMsgCenterRecordHashTable(String.valueOf(params.get("userId"))));
			List<MsgCenterRecord> list = sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST_BY_PAGE), params);
			return new PageInfo<MsgCenterRecord>(list);
		} catch (Exception e) {
			logger.error(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SQLNAME_FIND_LIST_BY_PAGE)), e);
			throw new SystemException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SQLNAME_FIND_LIST_BY_PAGE)), e);
		}
	}

	@Override
	public void updateIsRead(Map<String, Serializable> params) {
		try {
			//params.put("hashTableName", SimpleHashTableNameGenerator.getMsgCenterRecordHashTable(String.valueOf(params.get("userId"))));//
			sqlSession.update(getSqlName(SQL_UPDATE_IS_READ), params);
		} catch (Exception e) {
			logger.error(String.format("更新是否已读状态出错！语句:%s", getSqlName(SQL_UPDATE_IS_READ)), e);
			throw new SystemException(String.format("更新是否已读状态出错！语句:%s", getSqlName(SQL_UPDATE_IS_READ)), e);
		}

	}

	@Override
	public Integer findCountByIsRead(Map<String, Serializable> params) {
		try {
			//params.put("hashTableName", SimpleHashTableNameGenerator.getMsgCenterRecordHashTable(String.valueOf(params.get("userId"))));
			return sqlSession.selectOne(getSqlName(SQL_FIND_COUNT_BY_IS_READ), params);
		} catch (Exception e) {
			logger.error(String.format("查询对象出错！语句:%s", getSqlName(SQL_UPDATE_IS_READ)), e);
			throw new SystemException(String.format("查询对象出错！语句:%s", getSqlName(SQL_UPDATE_IS_READ)), e);
		}
	}

	@Override
	public List<MsgCenterRecord> findThreeListByUser(Map<String, Serializable> params) {
		try {
			//params.put("hashTableName", SimpleHashTableNameGenerator.getMsgCenterRecordHashTable(String.valueOf(params.get("userId"))));
			return sqlSession.selectList(getSqlName(SQL_FIND_THREELIST_BY_USER), params);
		} catch (Exception e) {
			logger.error(String.format("查询对象列表出错！语句:%s", getSqlName(SQL_FIND_THREELIST_BY_USER)), e);
			throw new SystemException(String.format("查询对象列表出错！语句:%s", getSqlName(SQL_FIND_THREELIST_BY_USER)), e);
		}
	}

}
