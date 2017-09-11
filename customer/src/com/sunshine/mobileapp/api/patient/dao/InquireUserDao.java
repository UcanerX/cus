package com.sunshine.mobileapp.api.patient.dao;

import org.springframework.stereotype.Repository;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.patient.entity.InquireUser;

@Repository
public interface InquireUserDao extends BaseDao<InquireUser, String>{

	
    int deleteByPrimaryKey(String id);

    String insert(InquireUser record);

    int insertSelective(InquireUser record);

    InquireUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InquireUser record);

    int updateByPrimaryKey(InquireUser record);
    
    
}