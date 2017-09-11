package com.sunshine.mobileapp.api.patient.dao;

import org.springframework.stereotype.Repository;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.patient.entity.PatientUser;

@Repository
public interface PatientUserDao extends BaseDao<PatientUser, String> {
	
	public PatientUser findByAccount(String account);
}
