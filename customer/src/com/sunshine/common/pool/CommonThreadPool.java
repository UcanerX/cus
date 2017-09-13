package com.sunshine.common.pool;

import com.sunshine.framework.common.threadpool.FixedThreadPoolExecutor;

public class CommonThreadPool {
	/**
	 * 消息推送线程池
	 */
	public static FixedThreadPoolExecutor msgPushThreadPool = new FixedThreadPoolExecutor(10, "msgPushThreadPool");

}
