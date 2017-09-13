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
package com.sunshine.common.msgmanager.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sunshine.common.msgmanager.entity.MsgCenterRecord;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;

/**
 * 消息中心dao
 * @Project: sunshine_health 
 * @Package: com.sunshine.common.dao.msgcenter
 * @ClassName: MsgCenterRecordDao
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface MsgCenterRecordDao extends BaseDao<MsgCenterRecord, String> {
	/**
	 * 根据ID查找消息
	 * @param params
	 * @return
	 */
	public abstract MsgCenterRecord findById(Map<String, Serializable> params);

	/**
	 * 更新是否已读标示
	 * @param params
	 */
	public abstract void updateIsRead(Map<String, Serializable> params);

	/**
	 * 获取未读消息总数
	 * @param params
	 * @return
	 */
	public abstract Integer findCountByIsRead(Map<String, Serializable> params);

	/**
	 * 根据用户ID查找最新三条消息  首页显示
	 * @param userId
	 * @param id
	 * @return
	 */
	public abstract List<MsgCenterRecord> findThreeListByUser(Map<String, Serializable> params);
}
