/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月6日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.index.doctorscollect.service;

import java.util.Map;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.PageParamsVo;
/**
* @Package：com.sunshine.mobileapp.api.index.doctorscollect.service   
* @ClassName：DoctorsRestService   
* @Description：   <p> 名医汇服务接口</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月6日 上午11:07:14   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public interface DoctorsRestService {
 
	public RestResponse findFamousDoctorList(String pageNum,String pageSize);
	
	/**
	 * 根据 pageNum pageSize 分页
	 * @param pageParamsVo
	 * @return
	 */
	public RestResponse findFamousDoctorListPost(PageParamsVo pageParamsVo);
	
	
	public RestResponse findDoctorListByDept(String deptId);
  
	/**
	 * 医生首页详情
	 * @return 
	 */
	public RestResponse doctorHomePageDetail(String doctorId);
	
	/**
	 * 根据部门和城市查询医生列表
	 * @param params
	 * @return
	 */
	public RestResponse findDoctorListByDeptAndCity(String deptId, String cityCode);
}
