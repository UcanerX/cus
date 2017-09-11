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
package com.sunshine.platform.security.service;

import java.util.List;

import com.sunshine.framework.mvc.mysql.service.BaseSQLService;
import com.sunshine.platform.security.entity.Dept;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.sunshine.platform.security.service
 * @ClassName DeptService.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年7月9日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
public interface DeptService extends BaseSQLService<Dept, String> {
	/**
	 * @Description 验证部门名称是否唯一
	 * @param dept
	 * @return
	 * @date 2017年7月10日
	 */
	public Boolean isUniqueDeptName(Dept dept);

	/**
	 * @Description 验证部门编码是否唯一
	 * @param dept
	 * @return
	 * @date 2017年7月10日
	 */
	public Boolean isUniqueDeptCode(Dept dept);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sunshine.framework.mvc.mysql.service.BaseService#deleteByIds(java.util.List)
	 */
	@Override
	public Long deleteByIds(List<String> ids);

}
