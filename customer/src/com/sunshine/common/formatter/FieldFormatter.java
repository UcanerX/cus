package com.sunshine.common.formatter;
/**
 * 
 * @Project: easyHealth 
 * @Package: com.sunshine.common.formatter
 * @ClassName: FieldFormatter
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-5-23
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface FieldFormatter {
	public String format(Object entity, String dicName, Object value);
}
