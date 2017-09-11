package com.sunshine.mobileapp.api.patient.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.patient.dao.InquireUserDao;
import com.sunshine.mobileapp.api.patient.entity.InquireUser;



@Repository
public class InquireUserDaoImpl extends BaseDaoImpl<InquireUser, String> implements InquireUserDao {
	private final static Logger logger = LoggerFactory.getLogger(InquireUserDaoImpl.class);
	private final static String SQLNAME_FIND_BY_ACCOUNT = "findByAccount";

	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertSelective(InquireUser record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public InquireUser selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int updateByPrimaryKeySelective(InquireUser record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateByPrimaryKey(InquireUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
