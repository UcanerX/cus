package com.sunshine.common.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.sunshine.framework.hash.SimpleHashTableNameGenerator;

public class ExecutorExecMultiHashTable<T> {
	private static Logger logger = LoggerFactory.getLogger(ExecutorExecMultiHashTable.class);

	/**
	 * 此方法执行10张分表   
	 * @param tableNamePirex   分表名前缀
	 * @param tableNameKey     子表名在sql中对应的预编译名,默认为hashTableName
	 * @param invokeClass      执行的类名
	 * @param invokeMethod     执行的方法名
	 * @param paramMap         方法的参数,此处规定invokeMethod的参数必须是Map类型
	 * @return
	 */
	public List<T> exec(String tableNamePirex, String tableNameKey, Class<?> invokeClass, String invokeMethod, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		// 设置线程池的数量
		ExecutorService collectExec = Executors.newFixedThreadPool(SimpleHashTableNameGenerator.subTableCount);
		List<FutureTask<Object>> taskList = new ArrayList<FutureTask<Object>>();

		for (int i = 1; i < SimpleHashTableNameGenerator.subTableCount + 1; i++) {
			String hashTableName = new String(tableNamePirex + "_" + i);
			Map<String, Object> methodParamMap = new HashMap<String, Object>();
			if (!paramMap.isEmpty()) {
				methodParamMap.putAll(paramMap);
			}
			methodParamMap.put(tableNameKey, hashTableName);
			Object[] invokeParams = new Object[] { methodParamMap };
			Class<?>[] invokeParamTypes = new Class[] { Map.class };
			SubHashTableCallable collectCall = new SubHashTableCallable(invokeClass, invokeMethod, invokeParams, invokeParamTypes);
			// 创建每条指令的采集任务对象
			FutureTask<Object> collectTask = new FutureTask<Object>(collectCall);
			// 添加到list,方便后面取得结果
			taskList.add(collectTask);
			// 提交给线程池 exec.submit(task);
			collectExec.submit(collectTask);
		}

		List<T> results = new ArrayList<T>();
		try {
			for (FutureTask<Object> taskF : taskList) {
				// 防止某个子线程查询时间过长 超过默认时间没有拿到抛出异常
				Object obj = taskF.get(Long.MAX_VALUE, TimeUnit.DAYS);
				if (obj != null) {
					if (obj instanceof List) {
						results.addAll((List<T>) obj);
					} else {
						results.add((T) obj);
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 处理完毕,关闭线程池,这个不能在获取子线程结果之前关闭,因为如果线程多的话,执行中的可能被打断
		collectExec.shutdown();

		if (logger.isDebugEnabled()) {
			if (!CollectionUtils.isEmpty(results)) {
				logger.debug("findByIdFromHashTable cast time :{}Millis . res:",
						new Object[] { System.currentTimeMillis() - start, JSON.toJSONString(results) });
			} else {
				logger.debug("findByIdFromHashTable cast time :{}Millis . res:", new Object[] { System.currentTimeMillis() - start, 0 });
			}
		}

		return results;
	}

	/**
	 * 此方法执行指定的分表  hashTableNames
	 * @param hashTableAndParamMaps   需要执行的分表和方法执行参数Map
	 * @param tableNameKey     子表名在sql中对应的预编译名,默认为hashTableName
	 * @param invokeClass      执行的类名
	 * @param invokeMethod     执行的方法名
	 * 
	 * Map<String ,Map<String, Object>> hashTableAndParamMaps  Key为子表名称   value为方法执行的参数
	 * 其中Map<String, Object> 为执行方法的参数 此处规定invokeMethod的参数必须是Map类型 且 map中的key为sql中的预编译名
	 * @return
	 */
	public List<T> execSubTables(Map<String, Map<String, Object>> hashTableAndParamMaps, String tableNameKey, Class<?> invokeClass,
			String invokeMethod) {
		long start = System.currentTimeMillis();
		// 设置线程池的数量
		ExecutorService collectExec = Executors.newFixedThreadPool(SimpleHashTableNameGenerator.subTableCount);
		List<FutureTask<Object>> taskList = new ArrayList<FutureTask<Object>>();

		for (String hashTableName : hashTableAndParamMaps.keySet()) {
			Map<String, Object> methodParamMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = hashTableAndParamMaps.get(hashTableName);
			if (!paramMap.isEmpty()) {
				methodParamMap.putAll(paramMap);
			}
			methodParamMap.put(tableNameKey, hashTableName);
			Object[] invokeParams = new Object[] { methodParamMap };
			Class<?>[] invokeParamTypes = new Class[] { Map.class };
			SubHashTableCallable collectCall = new SubHashTableCallable(invokeClass, invokeMethod, invokeParams, invokeParamTypes);
			// 创建每条指令的采集任务对象
			FutureTask<Object> collectTask = new FutureTask<Object>(collectCall);
			// 添加到list,方便后面取得结果
			taskList.add(collectTask);
			// 提交给线程池 exec.submit(task);
			collectExec.submit(collectTask);
		}

		List<T> results = new ArrayList<T>();
		try {
			for (FutureTask<Object> taskF : taskList) {
				// 防止某个子线程查询时间过长 超过默认时间没有拿到抛出异常
				Object obj = taskF.get(Long.MAX_VALUE, TimeUnit.DAYS);
				if (obj != null) {
					if (obj instanceof List) {
						results.addAll((List<T>) obj);
					} else {
						results.add((T) obj);
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 处理完毕,关闭线程池,这个不能在获取子线程结果之前关闭,因为如果线程多的话,执行中的可能被打断
		collectExec.shutdown();

		if (logger.isDebugEnabled()) {
			if (!CollectionUtils.isEmpty(results)) {
				logger.debug("findByIdFromHashTable cast time :{}Millis . res:",
						new Object[] { System.currentTimeMillis() - start, JSON.toJSONString(results) });
			} else {
				logger.debug("findByIdFromHashTable cast time :{}Millis . res:", new Object[] { System.currentTimeMillis() - start, 0 });
			}
		}

		return results;
	}
}
