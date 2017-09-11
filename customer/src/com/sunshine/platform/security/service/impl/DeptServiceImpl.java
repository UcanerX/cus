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
package com.sunshine.platform.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.platform.security.dao.DeptDao;
import com.sunshine.platform.security.dao.UserDao;
import com.sunshine.platform.security.entity.Dept;
import com.sunshine.platform.security.service.DeptService;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.sunshine.platform.security.service.impl
 * @ClassName DeptServiceImpl.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年7月9日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept, String> implements DeptService {
	private DeptDao deptDao = SpringContextHolder.getBean(DeptDao.class);

	@Override
	protected BaseDao<Dept, String> getDao() {
		// TODO Auto-generated method stub
		return deptDao;
	}

	@Override
	public String insert(Dept entity) {
		return super.insert(entity);
	}

	@Override
	public void update(Dept entity) {
		// TODO Auto-generated method stub
		if (!entity.getName().equalsIgnoreCase(entity.getOldName())) {
			UserDao userDao = SpringContextHolder.getBean(UserDao.class);
			userDao.updateDeptNameByDeptId(entity.getId(), entity.getName());
		}
		super.update(entity);
	}

	@Override
	public Long deleteByIds(List<String> idList) {
		// TODO Auto-generated method stub
		// 查询所有的子部门Id
		Long delCount = 0L;
		if (!CollectionUtils.isEmpty(idList)) {
			List<String> subDeptIds = deptDao.findAllSubDeptIds(idList);
			// 去重
			Set<String> idSet = new HashSet<>();
			idSet.addAll(subDeptIds);
			idSet.addAll(idList);
			idList = new ArrayList<>();
			idList.addAll(idSet);
			super.deleteByIds(idList);

			// 去除部门下所属人员的部门信息
			UserDao userDao = SpringContextHolder.getBean(UserDao.class);
			delCount = userDao.removeDeptInfo(idList);
		}
		return delCount;
	}

	@Override
	public Boolean isUniqueDeptName(Dept dept) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", dept.getName());
		List<Dept> depts = deptDao.findByProperties(paramMap);
		if (CollectionUtils.isEmpty(depts)) {
			return true;
		} else {
			if (depts.size() == 1 && StringUtils.isNotBlank(dept.getId())) {
				if (dept.getId().equalsIgnoreCase(depts.get(0).getId())) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public Boolean isUniqueDeptCode(Dept dept) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("code", dept.getCode());
		List<Dept> depts = deptDao.findByProperties(paramMap);
		if (CollectionUtils.isEmpty(depts)) {
			return true;
		} else {
			if (depts.size() == 1 && StringUtils.isNotBlank(dept.getId())) {
				if (dept.getId().equalsIgnoreCase(depts.get(0).getId())) {
					return true;
				}
			}
			return false;
		}
	}

}
