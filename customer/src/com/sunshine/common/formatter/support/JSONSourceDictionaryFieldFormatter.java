package com.sunshine.common.formatter.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.beanutils.BeanUtils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunshine.common.formatter.FieldFormatter;

/**
 * 
 * @Project: easyHealth 
 * @Package: com.sunshine.common.formatter.support
 * @ClassName: JSONSourceDictionaryFieldFormatter
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-5-23
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class JSONSourceDictionaryFieldFormatter  implements FieldFormatter {
	private static final String DEFAULT_KEY     = "$_default";
	private static final String DEFAULT_STUFFIX = "Label";
	private JSONObject dicSource;

	public JSONSourceDictionaryFieldFormatter() {}
	public JSONSourceDictionaryFieldFormatter(File jsonFile) {
		try {
			this.load(new FileInputStream(jsonFile));
		} catch (Exception e) {
			throw new IllegalArgumentException("读取json文件失败", e);
		}
	}
	public JSONSourceDictionaryFieldFormatter(URL url) {
		
		try {
			this.load(url.openStream());
		} catch (Exception e) {
			throw new IllegalArgumentException("读取json文件失败", e);
		}
	}
	public void load(InputStream ins) {
		
		try(InputStream in = ins) {
			byte[] data = new byte[in.available()];
			in.read(data);
			dicSource = JSON.parseObject(new String(data, "utf-8"));
		} catch (Exception e) {
			throw new IllegalArgumentException("解析json文件失败", e);
		}
	}

	@Override
	public String format(Object entity, String dicName, Object value) {
		JSONObject dicJson = dicSource.getJSONObject(dicName);
		String result = null;
		try {
			result = translate(dicJson, value);
			BeanUtils.setProperty(entity, dicName + DEFAULT_STUFFIX, result);
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return result;
	}
	
	private String translate(JSONObject dicJson, Object rawValue) {
		String result = null;
		String key = null;
		if (rawValue != null) {
			key = "$" + rawValue.toString();
		}
		if (key == null) {
			key = DEFAULT_KEY;
		}
		result = dicJson.getString(key);
		if (result == null) {
			result = dicJson.getString(DEFAULT_KEY) + key;
		}
		return result;
	}
}
