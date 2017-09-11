package com.sunshine.mobileapp.api.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sunshine.mobileapp.api.order.service.DoctorOrderService;

@Service
public class DoctorOrderServiceImpl extends OrderServiceImpl implements DoctorOrderService {

	public void updateStatusOnly(String orderId, Integer status) {
		if (!StringUtils.isEmpty(orderId) && status != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", orderId);
			params.put("orderStatus", status);
			//orderDao.updateByParams(params);
		}
	}
}
