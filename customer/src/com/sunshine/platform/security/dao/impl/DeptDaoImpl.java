/**
 * <html>
 * <body>
 *  <P> Copyright 2017 阳光康众</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年7月9日</p>
 *  <p> Created by 于策/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.platform.security.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.common.GlobalConstant;
import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.platform.security.dao.DeptDao;
import com.sunshine.platform.security.entity.Dept;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.sunshine.platform.security.dao.impl
 * @ClassName DeptDaoImpl.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年7月9日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Repository
public class DeptDaoImpl extends BaseDaoImpl<Dept, String> implements DeptDao {
	private static Logger logger = LoggerFactory.getLogger(DeptDaoImpl.class);

	private final static String SQLNAME_FIND_BY_PROPERTIES = "findByProperties";
	private final static String SQLNAME_COUNT_BY_PARENTID = "countByParentId";
	private final static String SQLNAME_FIND_ALL_SUB_DEPTIDS = "findAllSubDeptIds";

	@Override
	public List<Dept> findByProperties(Map<String, Object> paramMap) {
		try {
			Assert.notNull(paramMap);
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_BY_PROPERTIES), paramMap);
		} catch (Exception e) {
			logger.error(String.format("根据部门属性查询角色出错！语句：%s", getSqlName(SQLNAME_FIND_BY_PROPERTIES)), e);
			throw new SystemException(String.format("根据部门属性查询角色出错！语句：%s", getSqlName(SQLNAME_FIND_BY_PROPERTIES)), e);
		}
	}

	@Override
	public Integer countByParentId(String parentId) {
		// TODO Auto-generated method stub
		try {
			Assert.notNull(parentId);
			return sqlSession.selectOne(getSqlName(SQLNAME_COUNT_BY_PARENTID), parentId);
		} catch (Exception e) {
			logger.error(String.format("统计父部门下子部门的个数值出错！语句：%s", getSqlName(SQLNAME_COUNT_BY_PARENTID)), e);
			throw new SystemException(String.format("统计父部门下子部门的个数出错！语句：%s", getSqlName(SQLNAME_COUNT_BY_PARENTID)), e);
		}
	}

	@Override
	public List<String> findAllSubDeptIds(List<String> idList) {
		// TODO Auto-generated method stub
		try {
			List<String> subIdList = new ArrayList<>();
			Assert.notEmpty(idList);
			StringBuffer deptIds = new StringBuffer();
			for (String deptId : idList) {
				if (deptIds.length() > 0) {
					deptIds.append(GlobalConstant.STRING_SPLIT_CHAR);
				}
				deptIds.append(deptId);
			}
			String result = sqlSession.selectOne(getSqlName(SQLNAME_FIND_ALL_SUB_DEPTIDS), deptIds.toString());

			if (StringUtils.isNotBlank(result)) {
				// 去重复id
				Set<String> subIdSet = new HashSet<>();
				subIdSet.addAll(Arrays.asList(result.split(GlobalConstant.STRING_SPLIT_CHAR)));
				subIdList.clear();
				subIdList.addAll(subIdSet);
			}
			return subIdList;
		} catch (Exception e) {
			logger.error(String.format("查询父部门下所有子部门的ID出错！语句：%s", getSqlName(SQLNAME_FIND_ALL_SUB_DEPTIDS)), e);
			throw new SystemException(String.format("查询父部门下所有子部门的ID出错！语句：%s", getSqlName(SQLNAME_FIND_ALL_SUB_DEPTIDS)), e);
		}
	}

}
