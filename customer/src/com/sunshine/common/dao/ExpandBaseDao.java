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
package com.sunshine.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sunshine.framework.mvc.entity.BaseEntity;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;

/**
 * @Project: easy_health_client 
 * @Package: com.sunshine.common.dao
 * @ClassName: BaseDao
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
public interface ExpandBaseDao<T extends BaseEntity, PK extends Serializable> extends BaseDao {

	public List<T> findListByParams(Map<String, Object> params);

}
