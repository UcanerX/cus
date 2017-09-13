/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月25日</p>
 *  <p> Created by Allen</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.serviceinfo.service;

import java.util.List;
import java.util.Map;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.doctor.service.service
 * @ClassName: DoctorServiceInfoService
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: MuGua
 * @Create Date: 2016年10月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface DoctorServiceInfoRestService {
	
	/**
	 * 获取医生开启了的服务列表
	 * @param userId 医生的id
	 * @return
	 */
	public RestResponse getServiceListByUserId(String userId);
	
	public List<DoctorServiceInfo> getServiceListByUserIds(List<String> userIdList);

	public List<DoctorServiceInfo> getServiceListCache(String userId);

	public DoctorServiceInfo getDoctorServiceInfoByServiceIdAndUserId(Map params);
	
	public List<DoctorServiceInfo> findServiceInfoByStatus(Integer status);

}
