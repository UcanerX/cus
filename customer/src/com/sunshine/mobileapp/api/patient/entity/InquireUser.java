/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年9月13日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.patient.entity;

import java.util.Date;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
* @Package：com.sunshine.mobileapp.api.patient.entity   
* @ClassName：InquireUser   
* @Description：   <p> 问诊人 </p>
* @Author： - DaoDou 
* @CreatTime：2017年9月10日 下午3:55:13   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class InquireUser extends BaseSQLEntity {
	
	private static final long serialVersionUID = -2205891198852364512L;

	private String id;

    private String patientUser;

    private String name;

    private Boolean sex;

    private Date birthday;

    private String condition;

    private String conditionImg;

    private Boolean defaultUser;

    private Date ct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientUser() {
        return patientUser;
    }

    public void setPatientUser(String patientUser) {
        this.patientUser = patientUser == null ? null : patientUser.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    public String getConditionImg() {
        return conditionImg;
    }

    public void setConditionImg(String conditionImg) {
        this.conditionImg = conditionImg == null ? null : conditionImg.trim();
    }

    public Boolean getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(Boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}