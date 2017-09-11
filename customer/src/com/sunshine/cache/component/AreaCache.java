package com.sunshine.cache.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.common.entity.area.Area;
import com.sunshine.common.entity.area.City;
import com.sunshine.common.entity.area.Province;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

/**
 * @Project: yct 
 * @Package: com.sunshine.common.datas.cache.component
 * @ClassName: AreaCache
 * @Description: <p>省市区缓存类</p>
 * @JDK version used: 
 * @Author: liuyuanming
 * @Create Date: 2016-04-27
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class AreaCache {

	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	private static boolean had_init = false;
	private static String CITY_FIELDNAME_PREFIX = "city:";
	private static String PROVINCE_FIELDNAME_PREFIX = "province:";
	private static String AREA_FIELDNAME_PREFIX = "area:";

	private static Logger logger = LoggerFactory.getLogger(AreaCache.class);

	public void initCache() {
		
	}

	public interface GroupBy<T> {
		T groupby(Object obj);
	}

	private final <T extends Comparable<T>, D> Map<T, List<D>> group(Collection<D> colls, GroupBy<T> gb) {
		if (colls == null || colls.isEmpty()) {
			return null;
		}
		if (gb == null) {
			return null;
		}
		Iterator<D> iter = colls.iterator();
		Map<T, List<D>> map = new HashMap<T, List<D>>();
		while (iter.hasNext()) {
			D d = iter.next();
			T t = gb.groupby(d);
			if (map.containsKey(t)) {
				map.get(t).add(d);
			} else {
				List<D> list = new ArrayList<D>();
				list.add(d);
				map.put(t, list);
			}
		}
		return map;
	}

	/**
	 * 获取Area的Key
	 */
	private String getAreaCacheKey() {
		return CacheConstant.CACHE_HOSPITAL_AREA_KEY_PREFIX;
	}

	/**
	 * 获取省份缓存数据
	 */
	public List<Province> getProvinces() {
		List<Province> list = null;
		String value = redisSvc.hget(getAreaCacheKey(), PROVINCE_FIELDNAME_PREFIX);
		if (StringUtils.isNotBlank(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
			list = JSON.parseArray(value, Province.class);
		}
		return list;
	}

	/**
	 * 获取省份缓存数据Map
	 */
	public Map<String, String> getProvinceNames() {
		Map<String, String> map = new HashMap<String, String>();
		List<Province> list = getProvinces();
		for (Province province : list) {
			map.put(province.getProvinceCode(), province.getProvinceName());
		}
		return map;
	}

	/**
	 * 获取城市缓存数据
	 */
	public List<City> getCitys(String provinceCode) {
		List<City> list = null;
		if (StringUtils.isNotBlank(provinceCode)) {
			provinceCode = CITY_FIELDNAME_PREFIX.concat(provinceCode);
			String value = redisSvc.hget(getAreaCacheKey(), provinceCode);
			if (StringUtils.isNotBlank(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
				list = JSON.parseArray(value, City.class);
			}
		}
		return list;
	}

	/**
	 * 获取城市缓存数据Map
	 */
	public Map<String, String> getCityNames(String provinceCode) {
		Map<String, String> map = new HashMap<String, String>();
		List<City> list = getCitys(provinceCode);
		for (City city : list) {
			map.put(city.getCityCode(), city.getCityName());
		}
		return map;
	}

	/**
	* 获取城市缓存数据Map
	*/
	public Map<String, String> getCityNamesByCityCode(String cityCode) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(cityCode) && cityCode.length() == 6) {
			String provinceCode = cityCode.substring(0, 2).concat("0000");
			List<City> list = getCitys(provinceCode);
			for (City city : list) {
				map.put(city.getCityCode(), city.getCityName());
			}
		}
		return map;
	}

	/**
	 * 获取区县缓存数据
	 */
	public List<Area> getAreas(String cityCode) {
		List<Area> list = null;
		if (StringUtils.isNotBlank(cityCode)) {
			cityCode = AREA_FIELDNAME_PREFIX.concat(cityCode);
			String value = redisSvc.hget(getAreaCacheKey(), cityCode);
			if (StringUtils.isNotBlank(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
				list = JSON.parseArray(value, Area.class);
			}
		}
		return list;
	}

	/**
	 * 获取区县缓存数据Map
	 */
	public Map<String, String> getAreaNames(String cityCode) {
		Map<String, String> map = new HashMap<String, String>();
		List<Area> list = getAreas(cityCode);
		for (Area area : list) {
			map.put(area.getAreaCode(), area.getAreaName());
		}
		return map;
	}

	/**
	* 获取区县缓存数据Map
	*/
	public Map<String, String> getAreaNamesByAreaCode(String areaCode) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(areaCode) && areaCode.length() == 6) {
			String cityCode = areaCode.substring(0, 4).concat("00");
			List<Area> list = getAreas(cityCode);
			for (Area area : list) {
				map.put(area.getAreaCode(), area.getAreaName());
			}
			return map;
		}
		return map;
	}

}
