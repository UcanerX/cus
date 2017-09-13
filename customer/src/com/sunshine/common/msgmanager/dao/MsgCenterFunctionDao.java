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
package com.sunshine.common.msgmanager.dao;

import java.util.List;

import com.sunshine.common.msgmanager.entity.MsgCenterFunction;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;

/**
 * 个人中心消息功能Dao
 * @Project: sunshine_health
 * @Package: com.sunshine.platform.msgmanager.dao
 * @ClassName: MsgCenterFunctionDao
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
public interface MsgCenterFunctionDao extends BaseDao<MsgCenterFunction, String> {
	/**
	 * 根据个人中心消息库消息ID删除
	 * @param msgId
	 */
	public abstract void deleteByMsgId(String msgId);

	/**
	 * 根据个人中心消息库消息ID查询
	 * @param msgId
	 */
	public abstract List<MsgCenterFunction> findByMsgId(String msgId);
}