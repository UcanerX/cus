package com.sunshine.mobileapp.api.patient.service;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.patient.entity.PatientUser;

public interface PatientUserRestService {
	
	public RestResponse add(PatientUser patientUser);
	
	public RestResponse findById(String patientUserId);
	
	public RestResponse update(PatientUser patientUser);
	
	public RestResponse getOrderCount(String patientId);
	
} 