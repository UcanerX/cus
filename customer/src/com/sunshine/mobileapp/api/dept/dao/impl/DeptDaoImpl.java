/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月4日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.dept.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.dept.dao.DeptDao;
import com.sunshine.mobileapp.api.dept.entity.Dept;

/**
 * @Project: easy_health
 * @Package: com.sunshine.platform.dept.dao.impl
 * @ClassName: DeptDaoImpl
 * @Description:
 * 				<p>
 *               </p>
 * @JDK version used:
 * @Author: 申姜
 * @Create Date: 2016年10月4日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Repository(value = "deptDao")
public class DeptDaoImpl  extends BaseDaoImpl<Dept, String> implements DeptDao {
	private static Logger logger = LoggerFactory.getLogger(DeptDaoImpl.class);
	private final static String SQLNAME_FIND_BY_PARENT_ID = "findByParentId";
	private final static String SQLNAME_FIND_ONE_LEVEL_DEPTS = "findOneLevelDepts";
	private final static String SQLNAME_BATCH_UPDATE_STATUS = "batchUpdateStatus";
	private final static String SQLNAME_FIND_ALL_DEPT_LIST = "findAllDeptList";
	
	@Override
	public List<Dept> findAllDeptList() {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_ALL_DEPT_LIST));
		} catch (Exception e) {
			logger.error(String.format("查询所有的科室！语句：%s", getSqlName(SQLNAME_FIND_ALL_DEPT_LIST)), e);
			throw new SystemException(String.format("查询所有的科室出错！语句：%s", getSqlName(SQLNAME_FIND_ALL_DEPT_LIST)), e);
		}
	}
	
	
	@Override
	public void batchUpdateStatus(Map<String, Object> params) {
		
	}
	
	
	
	@Override
	public List<Dept> findByParentId(String parentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public List<Dept> findOneLevelDepts(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
