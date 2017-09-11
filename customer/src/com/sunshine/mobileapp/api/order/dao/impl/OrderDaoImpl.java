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
package com.sunshine.mobileapp.api.order.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.order.dao.OrderDao;
import com.sunshine.mobileapp.api.order.dao.OrderStatusDao;
import com.sunshine.mobileapp.api.order.entity.Order;
import com.sunshine.mobileapp.api.order.entity.OrderStatus;
import com.sunshine.mobileapp.api.order.vo.OrderStatusEnum;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.dao.impl
 * @ClassName: OrderDaoImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Repository
public class OrderDaoImpl  extends BaseDaoImpl<Order, String>implements OrderDao{

	   private final static String SQLNAME_FIND_LIST_BY_PARAMS = "findListByParams";
	
	    private static String SQLNAME_FIND_TRANS_DOC="findTransDoc";
	    private static String SQLNAME_FIND_DOC_INFO="findDocInfo";
	    
	    private static String SQLNAME_COUNT_WAIT_DEAL="countWaitDeal";
	    private static String SQLNAME_COUNT_SERVICE="countService";
	    private static String SQLNAME_COUNT_OVER="countOver";
	    
	    private static String SQLNAME_UPDATE_ORDER_STATUS="updateOrderStatus";
	    private static String SQLNAME_UPDATE_TO_TIMEOUT = "updateToTimeOut";
	    private static String SQLNAME_FIND_POSSIBLE_TIMEOUT_ORDER = "findPossibleTimeoutOrder";
	    

	    @Resource(name="orderStatusDaoImpl")
	    private OrderStatusDao orderStatusDao;
	    
		private String phoneConsultServiceId;

	    
		@Override
		public PageInfo<String> findTransDoc(Map<String, Object> params, Page<String> page) {
			PageHelper.startPage(page.getPageNum(), page.getPageSize());
			List<String> list = sqlSession.selectList(getSqlName(SQLNAME_FIND_TRANS_DOC), params);
			return new PageInfo<String>(list);
			
//			return sqlSession.selectList(getSqlName(SQLNAME_FIND_TRANS_DOC), params);
		}

		public List<Map<String,Object>> findDocInfo(List<String> params){
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_DOC_INFO), params);
		}


		@Override
		public int countWaitDeal(String doctorId) {
			return  sqlSession.selectOne(getSqlName(SQLNAME_COUNT_WAIT_DEAL),doctorId);
		}


		@Override
		public int countService(String doctorId) {
			return sqlSession.selectOne(getSqlName(SQLNAME_COUNT_SERVICE),doctorId);
		}

		
		@Override
		public int countOver(String doctorId) {
			return sqlSession.selectOne(getSqlName(SQLNAME_COUNT_OVER),doctorId);
		}
		
		
		@Override
		public void updateToTimeOut(String id) {
			Assert.notNull(id, "更改订单为超时，订单id不能为空");
			sqlSession.update(getSqlName(SQLNAME_UPDATE_TO_TIMEOUT), id);
			OrderStatus s = new OrderStatus();
			s.setChangeTime(new Date());
			s.setOrderId(id);
			s.setStatus(OrderStatusEnum.TIMEOUT.getOrderStatus());
			orderStatusDao.insert(s);
		}
//		@Override
//		public List<Order> findPossibleTimeoutOrder(Date baseTime) {
//			Map<String, Object> params = new HashMap<String, Object>();
//			if(phoneConsultServiceId == null) {
//				SysServiceCache sysServiceCache = SpringContextHolder.getBean(SysServiceCache.class);
//				ServiceInfoManage service = sysServiceCache.getPhoneConsultService();
//				if (service != null) {
//					phoneConsultServiceId = service.getId();
//				}
//			}
//			params.put("startTime", baseTime);
//			params.put("serviceId", phoneConsultServiceId);
//			return sqlSession.selectList(getSqlName(SQLNAME_FIND_POSSIBLE_TIMEOUT_ORDER), params);
//		}

	
		
//		@Override
//		public List<Order> findMsgPromptOrder() {
//			Map<String, Object> params = new HashMap<String, Object>();
//			
//			params.put("orderStatus", OrderStatusEnum.UNSERVICE.getOrderStatus());
//			
//			params.put("appointmentTime", DateUtils.currentDate());
//			params.put("promptTime", DateUtils.getPromptDate(OrderConstant.MSG_PROMPT_OFFSETTIME_IN_MINUTE));
//			
//			// 当前时间（基于基准日期）
//			params.put("promptStopTime", DateUtils.getBaseCurrentTime());
//			
//			if(phoneConsultServiceId == null) {
//				SysServiceCache sysServiceCache = SpringContextHolder.getBean(SysServiceCache.class);
//				ServiceInfoManage service = sysServiceCache.getPhoneConsultService();
//				if (service != null) {
//					phoneConsultServiceId = service.getId();
//				}
//			}
//			params.put("serviceId", phoneConsultServiceId);
//			
//			List<Order> orderList =  findListByParams(params);
//			
//			return orderList;
//		}
//
//		@Override
//		public int updateOrderSatus(Map<String, Object> params) {
//			return  sqlSession.update(getSqlName(SQLNAME_UPDATE_ORDER_STATUS),params);
//		}
		
		/* (non-Javadoc)
		 * @see com.sunshine.mobileapp.api.order.dao.OrderDao#findListByParams(java.util.Map)
		 */
		@Override
		public List<Order> findListByParams(Map<String, Object> params) {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST_BY_PARAMS), params);
		}

		@Override
		public int updateOrderSatus(Map<String, Object> params) {
			// TODO Auto-generated method stub
			return 0;
		}
}
