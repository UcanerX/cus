package com.sunshine.mobileapp.api.order.service;

import com.sunshine.mobileapp.api.order.entity.Order;


public interface PatientOrderService extends OrderService {
	
	public Order generateOrder(Order unsavedOrder, String appointmentDate, String appointmentTime);
}
