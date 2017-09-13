/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月17日</p>
 *  <p> Created by MuGua</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.serviceinfo.dao;

import java.util.List;
import java.util.Map;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

public interface DoctorServiceInfoDao extends BaseDao<DoctorServiceInfo, String> {

	public List<DoctorServiceInfo> getServiceListByUserId(String userId);

	public List<DoctorServiceInfo> getServiceListByUserIds(List<String> userIdList);

	public DoctorServiceInfo getDoctorServiceInfoByServiceIdAndUserId(Map params);

	public List<DoctorServiceInfo> findServiceInfoByStatus(Integer status);

}