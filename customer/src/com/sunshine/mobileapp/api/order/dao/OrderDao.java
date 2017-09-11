/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年6月6日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.order.entity.Order;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.dao
 * @ClassName: OrderDao
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface OrderDao  extends BaseDao<Order, String>{
	
	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	public List<Order> findListByParams(Map<String, Object> params);
	
	/**
	 * 查询可转诊医生的id
	 */
	public  PageInfo<String>  findTransDoc(Map<String, Object> params, Page<String> page);
	
	/**
	 * 查询医生的信息根据id
	 */
	public List<Map<String,Object>> findDocInfo(List<String> list);
	
	public int countWaitDeal(String doctorId);
	
	public int countService(String doctorId);
	
	public int countOver(String doctorId);
	
	
	
	/**
	 * 将待接诊的订单更新为超时
	 */
	public void updateToTimeOut(String id);

	public int updateOrderSatus(Map<String, Object> params);
	
	/**
	 * 查询相对于基准时间可能过期的订单
	 * 
	 * @param baseTime 基准时间
	 */
	//public List<Order> findPossibleTimeoutOrder(Date baseTime);
	
	/**
	 * 获取需信息提醒的订单
	 * 
	 * @return
	 */
	//public List<Order> findMsgPromptOrder();
	
	
	/**
	 * 更新订单状态
	 */
	//public int updateOrderSatus(Map<String, Object> params);

}
