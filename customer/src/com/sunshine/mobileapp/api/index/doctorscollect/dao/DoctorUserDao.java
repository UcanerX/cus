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
package com.sunshine.mobileapp.api.index.doctorscollect.dao;

import java.util.List;
import java.util.Map;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.index.doctorscollect.entity.DoctorUser;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.DoctorsParamsVo;

/**
* @Package：com.sunshine.mobileapp.api.index.doctorscollect.dao   
* @ClassName：DoctorUserDao   
* @Description：   <p> 医生用户dao</p>
* @Author： -MuGua 
* @CreatTime：2016年9月6日 
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public interface DoctorUserDao extends BaseDao<DoctorUser, String> {
	
	
	/**
	 * @return 查询名医列表
	 */
	public List<DoctorsParamsVo>  findFamousDoctorList();
	
	/**
	 * 根据科室id查询医生列表数据
	 * @param deptId 科室id
	 * @return  对应科室的医生
	 */
	public List<DoctorsParamsVo> findDoctorListByDept(String deptId);
	
	
	/**
	 * 根据城市Code筛选医生列表
	 * @param deptId 科室id
	 * @param cityCode 城市Code
	 * @return List<DoctorsParamsVo> 符合城市条件的 对应科室的 医生列表
	 */
	public List<DoctorsParamsVo> findDoctorListByDeptAndCity(Map<String, String> params);
	
	
	
	public DoctorUser findByAccount(String account);

	
	public int countByAccount(String account);

	
	public DoctorsParamsVo findDoctorDetailById(String doctorId);

}
