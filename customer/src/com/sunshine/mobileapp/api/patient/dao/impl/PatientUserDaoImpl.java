package com.sunshine.mobileapp.api.patient.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.patient.dao.PatientUserDao;
import com.sunshine.mobileapp.api.patient.entity.PatientUser;



@Repository
public class PatientUserDaoImpl extends BaseDaoImpl<PatientUser, String> implements PatientUserDao {
	private final static String SQLNAME_FIND_BY_ACCOUNT = "findByAccount";
	private final static Logger logger = LoggerFactory.getLogger(PatientUserDao.class);
	
	@Override
	public PatientUser findByAccount(String account) {
		try {
			Assert.notNull(account, "根据账号查询用户，入参account不能为空");
			return sqlSession.selectOne(getSqlName(SQLNAME_FIND_BY_ACCOUNT), account);
		} catch (Exception e) {
			throw new SystemException(String.format("根据账号查询用户出错！语句：%s", getSqlName(SQLNAME_FIND_BY_ACCOUNT)), e);
		}
	}

}
