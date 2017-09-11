package com.sunshine.common.formatter.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sunshine.common.formatter.FieldFormatter;

/**
 * 
 * @Project: easyHealth 
 * @Package: com.sunshine.common.formatter.support
 * @ClassName: FieldFormatterManager
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-5-23
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class FieldFormatterManager {
	private static final FieldFormatterManager instance = new FieldFormatterManager();
	
	private Map<String, FieldFormatter> formatters = new ConcurrentHashMap<String, FieldFormatter>();
	
	private FieldFormatterManager() {}

	public static FieldFormatterManager getInstance() {
		return instance;
	}
	
	public FieldFormatter getDicFormatter() {
		return getFormatter(JSONSourceDictionaryFieldFormatter.class.getName());
	}
	public FieldFormatter getFormatter(String key) {
		return formatters.get(key);
	}
	
	public FieldFormatterManager registerFormatter(String key, FieldFormatter formatter) {
		formatters.put(key, formatter);
		return this;
	} 
}
