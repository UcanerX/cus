package com.sunshine.mobileapp.api.patient.service;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.patient.entity.InquireUser;

public interface InquireUserRestService {
	
	public RestResponse add(InquireUser InquireUser);
	
	public RestResponse findById(String inquireUserId);
	
	public RestResponse update(InquireUser inquireUser);
	
} 