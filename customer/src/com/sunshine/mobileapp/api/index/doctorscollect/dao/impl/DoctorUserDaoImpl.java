/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月17日</p>
 *  <p> Created by Allen</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.index.doctorscollect.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.index.doctorscollect.dao.DoctorUserDao;
import com.sunshine.mobileapp.api.index.doctorscollect.entity.DoctorUser;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.DoctorsParamsVo;

/**
 * @Project: easy_health
 * @Package: com.sunshine.doctor.user.dao.impl
 * @ClassName: DoctorUserDaoImpl
 * @Description:
 * 				<p>
 *               </p>
 * @JDK version used:
 * @Author: MuGua
 * @Create Date: 2016年10月17日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */

@Repository(value = "doctorUserDao")
public class DoctorUserDaoImpl extends BaseDaoImpl<DoctorUser, String> implements DoctorUserDao {

	private static final Logger logger = LoggerFactory.getLogger(DoctorUserDaoImpl.class);
	private static final String SQLNAME_FIND_BY_ACCOUNT = "findByAccount";
	private static final String SQLNAME_COUNT_BY_ACCOUNT = "countByAccount";
	private static final String SQLNAME_FIND_FAMOUS_DOCTOR_LIST = "findFamousDoctorList";
	private static final String SQLNAME_FIND_DOCTOR_LIST_BY_DEPT = "findDoctorListByDept";
	private static final String SQLNAME_FIND_DOCTOR_DETAIL_BY_ID = "findDoctorDetailById";
	private static final String SQLNAME_FIND_DOCTOR_LIST_BY_DEPT_AND_CITY = "findDoctorListByDeptAndCity";

	@Override
	public List<DoctorsParamsVo> findDoctorListByDeptAndCity(Map<String, String> params) {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_DOCTOR_LIST_BY_DEPT_AND_CITY), params);
		} catch (Exception e) {
			logger.error(String.format("根据科室ID和CityCoce查询医生列表出错！语句：%s", getSqlName(SQLNAME_FIND_DOCTOR_LIST_BY_DEPT_AND_CITY)), e);
			throw new SystemException(String.format("根据科室ID和CityCoce查询医生列表出错！语句：%s", getSqlName(SQLNAME_FIND_DOCTOR_LIST_BY_DEPT_AND_CITY)), e);
		}
	}

	@Override
	public DoctorsParamsVo findDoctorDetailById(String doctorId) {
		try {
			return sqlSession.selectOne(getSqlName(SQLNAME_FIND_DOCTOR_DETAIL_BY_ID), doctorId);
		} catch (Exception e) {
			logger.error(String.format("根据id查询医生详情！语句：%s", getSqlName(SQLNAME_FIND_DOCTOR_DETAIL_BY_ID)), e);
			throw new SystemException(String.format("根据id查询医生详情出错！语句：%s", getSqlName(SQLNAME_FIND_DOCTOR_DETAIL_BY_ID)), e);
		}
	}

	@Override
	public List<DoctorsParamsVo> findDoctorListByDept(String deptId) {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_DOCTOR_LIST_BY_DEPT), deptId);
		} catch (Exception e) {
			logger.error(String.format("根据科室查询医生出错！语句：%s", getSqlName(SQLNAME_FIND_DOCTOR_LIST_BY_DEPT)), e);
			throw new SystemException(String.format("根据科室查询医生查询出错！语句：%s", getSqlName(SQLNAME_FIND_DOCTOR_LIST_BY_DEPT)), e);
		}
	}

	@Override
	public List<DoctorsParamsVo> findFamousDoctorList() {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_FAMOUS_DOCTOR_LIST));
		} catch (Exception e) {
			logger.error(String.format("根据账号电话号码查询用户出错！语句：%s", getSqlName(SQLNAME_FIND_FAMOUS_DOCTOR_LIST)), e);
			throw new SystemException(String.format("根据账号电话号码查询用户出错！语句：%s", getSqlName(SQLNAME_FIND_FAMOUS_DOCTOR_LIST)), e);
		}
	}

	@Override
	public DoctorUser findByAccount(String account) {
		try {
			Assert.notNull(account);
			return sqlSession.selectOne(getSqlName(SQLNAME_FIND_BY_ACCOUNT), account);
		} catch (Exception e) {
			logger.error(String.format("根据账号电话号码查询用户出错！语句：%s", getSqlName(SQLNAME_FIND_BY_ACCOUNT)), e);
			throw new SystemException(String.format("根据账号电话号码查询用户出错！语句：%s", getSqlName(SQLNAME_FIND_BY_ACCOUNT)), e);
		}

	}

	@Override
	public int countByAccount(String account) {
		try {
			Assert.notNull(account);
			return sqlSession.selectOne(getSqlName(SQLNAME_COUNT_BY_ACCOUNT), account);
		} catch (Exception e) {
			logger.error(String.format("根据账号电话号码查询用户出错！语句：%s", getSqlName(SQLNAME_COUNT_BY_ACCOUNT)), e);
			throw new SystemException(String.format("根据账号电话号码查询用户出错！语句：%s", getSqlName(SQLNAME_COUNT_BY_ACCOUNT)), e);
		}
	}

}
