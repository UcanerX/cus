/**
* <html>
*  <body>
*   <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016年10月4日</p>
*   <p> Created by 姓名</p>
*  </body>
* </html>
*/
package com.sunshine.mobileapp.api.dept.dao;

import java.util.List;
import java.util.Map;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.dept.entity.Dept;

/**
 * 
 * @Project: easy_health
 * @Package: com.sunshine.platform.dept.dao
 * @ClassName: DeptDao
 * @Description:
 * 				<p>
 *               科室管理dao接口
 *               </p>
 * @JDK version used:
 * @Author: 申姜
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface DeptDao extends BaseDao<Dept, String> {

	/**
	 * 批量更新状态
	 * 
	 * @param params
	 */
	public void batchUpdateStatus(Map<String, Object> params);

	/**
	 * 根据父科室ID查找二级科室列表
	 * 
	 * @param parentId
	 * @return
	 */
	public abstract List<Dept> findByParentId(String parentId);

	
	/**
	 * 查询所有的deptList集合数据
	 * @return
	 */
	public abstract List<Dept> findAllDeptList();
	
	/**
	 * 查找一级科室
	 * 
	 * @param params
	 * @return
	 */
	public abstract List<Dept> findOneLevelDepts(Map<String, Object> params);

}