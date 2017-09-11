package com.sunshine.mobileapp.api.order.service;


public interface DoctorOrderService extends OrderService {
	
	/**
	 * 更新订单状态，不记录更新记录（BIZ_ORDER_STATE_CHANGE表记录）
	 * 
	 * @param orderId
	 * @param status
	 * @return
	 */
	public void updateStatusOnly(String orderId, Integer status);
	
}
