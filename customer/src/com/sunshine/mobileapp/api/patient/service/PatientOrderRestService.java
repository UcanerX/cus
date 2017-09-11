package com.sunshine.mobileapp.api.patient.service;

import com.sunshine.mobileapp.api.RestResponse;
/**
* @Package：com.sunshine.mobileapp.api.patient.service   
* @ClassName：PatientOrderRestService   
* @Description：   <p> 下单接口  </p>
* @Author： - DaoDou 
* @CreatTime：2017年9月10日 下午5:46:05   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public interface PatientOrderRestService {
	
	/**
	 * @param account 账号
	 * @param doctor 医生
	 * @param serviceId 服务id
	 * @param serviceName 服务名称
	 * @param appointmentDate  
	 * @param appointmentTime
	 * @param buyTime 购买时长
	 * @param imageKeys 
	 * @return
	 */
	RestResponse generateOrder(String account, String doctor,String serviceId, String serviceName,
	String appointmentDate, String appointmentTime, Integer buyTime, String imageKeys);
	
	
	
} 