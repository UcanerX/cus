package com.sunshine.common.service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunshine.common.utils.WeekDay;
import com.sunshine.framework.utils.DateUtils;
import com.sunshine.mobileapp.api.order.planning.AvailableTime;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.service.config
 * @ClassName: DoctorAvailableTimeConfig
 * @Description: <p>医生排班时间对象</p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017年7月12日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DoctorAvailableTimeConfig {
	
	private String doctorId;
	private final Map<WeekDay, List<AvailableTime>> timeMap = new HashMap<WeekDay, List<AvailableTime>>();
	
	public DoctorAvailableTimeConfig() {
	}
	
	public String getDoctorId() {
		return this.doctorId;
	}
	
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	public List<AvailableTime> getAvailableTimeList(WeekDay weekDay) {
		return timeMap.get(weekDay);
	}
	
	public String toJSONString() {
		JSONObject map = new JSONObject();
		
		map.put("doctorId", doctorId);
		if (timeMap.get(WeekDay.Monday) != null) {
			map.put(WeekDay.Monday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Monday)));
		}
		if (timeMap.get(WeekDay.Tuesday) != null) {
			map.put(WeekDay.Tuesday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Tuesday)));
		}
		if (timeMap.get(WeekDay.Wednesday) != null) {
			map.put(WeekDay.Wednesday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Wednesday)));
		}
		if (timeMap.get(WeekDay.Thursday) != null) {
			map.put(WeekDay.Thursday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Thursday)));
		}
		if (timeMap.get(WeekDay.Friday) != null) {
			map.put(WeekDay.Friday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Friday)));
		}
		if (timeMap.get(WeekDay.Saturday) != null) {
			map.put(WeekDay.Saturday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Saturday)));
		}
		if (timeMap.get(WeekDay.Sunday) != null) {
			map.put(WeekDay.Sunday.getCode(), JSON.toJSONString(timeMap.get(WeekDay.Sunday)));
		}
		
		return map.toJSONString();
	}
	
	public static String toJSONString(List<AvailableTime> list) {
		if (list != null) {
			return JSON.toJSONString(list);
		}
		return null;
	}
	
	public static List<AvailableTime> parse(WeekDay weekDay, String listJson) {
		List<AvailableTime> list = new CopyOnWriteArrayList<AvailableTime>();
		JSONArray arr = JSON.parseArray(listJson);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				AvailableTime at = new AvailableTime(weekDay, list, json.getLongValue("startTime"), json.getLongValue("endTime"));
				list.add(at);
			}
		}
		
		return list;
	}
	public static DoctorAvailableTimeConfig parse(String jsonString) {
		JSONObject map = JSON.parseObject(jsonString);
		DoctorAvailableTimeConfig config = new DoctorAvailableTimeConfig();
		
		config.setDoctorId(map.getString("doctorId"));
		
		addAvailableTime(config, WeekDay.Monday, map);
		addAvailableTime(config, WeekDay.Tuesday, map);
		addAvailableTime(config, WeekDay.Wednesday, map);
		addAvailableTime(config, WeekDay.Thursday, map);
		addAvailableTime(config, WeekDay.Friday, map);
		addAvailableTime(config, WeekDay.Saturday, map);
		addAvailableTime(config, WeekDay.Sunday, map);
		
		return config;
	}
	
	private static void addAvailableTime(DoctorAvailableTimeConfig config, WeekDay weekDay, JSONObject map) {
		if (map.get(weekDay.getCode()) != null) {
			JSONArray arr = map.getJSONArray(weekDay.getCode());
			List<AvailableTime> list = new CopyOnWriteArrayList<AvailableTime>();
			if (arr.size() > 0) {
				for (int i = 0; i < arr.size(); i++) {
					JSONObject json = arr.getJSONObject(i);
					AvailableTime at = new AvailableTime(weekDay, list, json.getLongValue("startTime"), json.getLongValue("endTime"));
					list.add(at);
				}
				config.timeMap.put(weekDay, list);
			}
		}
	}
	
	private static void addAvailableTime(DoctorAvailableTimeConfig config, WeekDay weekDay, String timeStr) {
		long lastEndTime = 0;
		AvailableTime lastAvailableTime = null;
		if (!StringUtils.isEmpty(timeStr)) {
			
			List<AvailableTime> list = new CopyOnWriteArrayList<AvailableTime>();
			String[] availableTimeArr = timeStr.split("\\|");
			for (String availableTimeStr : availableTimeArr) {
				if (StringUtils.isEmpty(availableTimeStr)) {
					continue;
				}
				Date[] dates = DateUtils.parseDates(availableTimeStr);
				
				if (lastAvailableTime != null && dates[0].getTime() == lastEndTime) {// 相邻的时间段合并
					lastAvailableTime.setEndTime(dates[1].getTime());
				} else {
					AvailableTime at = new AvailableTime(weekDay, list, dates[0].getTime(), dates[1].getTime());
					lastAvailableTime = at;
					list.add(at);
				}
				lastEndTime = dates[1].getTime();
			}
			config.timeMap.put(weekDay, list);
		}
	}
	
	public static DoctorAvailableTimeConfig buildAvailableTimeConfig(DoctorServiceInfo setting) {
		Assert.notNull(setting, "排班配置时间不能为空");
		Assert.notNull(setting, "排班配置时间记录医生Id不能为空");
		DoctorAvailableTimeConfig config = new DoctorAvailableTimeConfig();
		config.setDoctorId(setting.getUserId());
		
		addAvailableTime(config, WeekDay.Monday, setting.getStMon());
		addAvailableTime(config, WeekDay.Tuesday, setting.getStTue());
		addAvailableTime(config, WeekDay.Wednesday, setting.getStWed());
		addAvailableTime(config, WeekDay.Thursday, setting.getStThu());
		addAvailableTime(config, WeekDay.Friday, setting.getStFri());
		addAvailableTime(config, WeekDay.Saturday, setting.getStSat());
		addAvailableTime(config, WeekDay.Sunday, setting.getStSun());
		
		return config;
	}
	public static void main(String[] args) {
		DoctorAvailableTimeConfig c = new DoctorAvailableTimeConfig();
		
		// 构建配置可用时间对象测试
		addAvailableTime(c, WeekDay.Monday, "7:00-7:30|9:00-9:30|9:30-10:00|15:30-16:00");
		System.out.println(c.toJSONString());
		
	}
}
