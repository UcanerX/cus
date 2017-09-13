/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月13日</p>
 *  <p> Created by x-lan</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunshine.common.dao.ExpandBaseDao;
import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.entity.BaseEntity;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;

/**
 * @Project: easy_health_client 
 * @Package: com.sunshine.common.dao.impl
 * @ClassName: Base1DaoImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: x-lan
 * @Create Date: 2017年9月13日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@SuppressWarnings("rawtypes")
public class ExpandBaseDaoImpl<T extends BaseEntity, PK extends Serializable> extends BaseDaoImpl implements ExpandBaseDao<T, PK> {

	private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	private final static String SQLNAME_FIND_LIST_BY_PARAMS = "findListByParams";

	@Override
	public List<T> findListByParams(Map<String, Object> params) {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST_BY_PARAMS), params);
		} catch (Exception e) {
			logger.error(String.format("查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_LIST_BY_PARAMS)), e);
			throw new SystemException(String.format("查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_LIST_BY_PARAMS)), e);
		}
	}

}
