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
package com.sunshine.common.msgmanager.service;

import java.util.List;

import com.sunshine.common.msgmanager.entity.MsgCenterRecord;
import com.sunshine.framework.mvc.service.BaseService;

/**
 * @Project: sunshine_health 
 * @Package: com.sunshine.mobileapp.biz.msgcenter.service
 * @ClassName: MsgCenterRecordService
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface MsgCenterRecordService extends BaseService<MsgCenterRecord, String> {
	/**
	 * 根据ID查找消息
	 * @param userId
	 * @param id
	 * @return
	 */
	public abstract MsgCenterRecord findById(String userId, String id);

	/**
	 * 更新是否已读状态
	 * @param params
	 */
	public void updateIsRead(String userId, String id, Integer isRead);

	/**
	 * 根据是否已读状态获取消息数量
	 * @param userId
	 * @param isRead
	 * @return
	 */
	public abstract Integer findCountByIsRead(String userId, Integer isRead);

	/**
	 * 根据用户ID查找最新三条消息  首页显示
	 * @param userId
	 * @param id
	 * @return
	 */
	public abstract List<MsgCenterRecord> findThreeListByUser(String userId);
}
