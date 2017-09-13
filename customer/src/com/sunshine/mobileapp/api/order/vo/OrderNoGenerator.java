package com.sunshine.mobileapp.api.order.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunshine.common.GlobalConstant;
import com.sunshine.cache.CacheConstant;
import com.sunshine.framework.cache.redis.RedisLock;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

/**
 * @Package com.sunshine.common.utils
 * @ClassName OrderNoGenerator
 * @Statement <p>
 *            订单编号生成器
 *            </p>
 * @JDK version used: 1.7
 * @Author: 无名子
 * @Create Date: 2016-4-27
 * @modify-Author:
 * @modify-Date:
 * @modify-Why/What:
 * @Version 1.0
 */
public class OrderNoGenerator {
	private static Logger logger = LoggerFactory.getLogger(OrderNoGenerator.class);
	private static final String COVER_CHAR = "0";
	public static DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	public static DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static DateFormat YYYYMMDDHH = new SimpleDateFormat("yyyyMMddHH");

	/**
	 * 订单日期标志位 判断流水号是否需要从1开始
	 */
	private static long orderDay = Long.valueOf(YYYYMMDD.format(new Date()));

	private static java.util.concurrent.atomic.AtomicLong serialNum = new AtomicLong(0);

	private static final String ORDERNO_GEN_NUMBER_DATE = "eh.order.number.date";
	private static final String ORDERNO_LOCKED_KEY = "eh.order.lock";
	private static final long ORDERNO_LOCKED_TIME_OUT = 30000;

	/*
	 * 根据服务器的MAC地址生成1-9的编号 private static int serverNo; static { InetAddress address; try { address = InetAddress.getLocalHost(); NetworkInterface ni
	 * = NetworkInterface.getByInetAddress(address); byte[] mac = ni.getHardwareAddress(); String sMAC = null; Formatter formatter = new Formatter();
	 * for (int i = 0; i < mac.length; i++) { sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i], ( i < mac.length - 1 ) ? "-" :
	 * "").toString(); } if (StringUtils.isBlank(sMAC)) { sMAC = address.getHostAddress(); } serverNo = ( Math.abs(sMAC.hashCode()) % 9 + 1 ); } catch
	 * (Exception e) { // TODO Auto-generated catch block serverNo = 1; } }
	 */

	/**
	 * <p>
	 * 生成规则 ：(1位)订单来源 + (2位)交易类型 + (1位)业务编号 + (10位)日期 + (7位)流水号<br>
	 * 订单来源：医程通APP:1 H5微站:2 医程通微信 :3 医程通支付宝:4 医程通网站:5 医院微信:6 医院支付宝:7 银联钱包:8 医程通钱包:9 交易类型(2位数),不足2位补0 线上支付-1,患者线上退费-2,系统退费(HIS支付确认失败退费)-3,
	 * 停诊退费-4,窗口全额退费-5,窗口部分退费-6 扫码支付-7 跨平台取号支付-8 绑卡解绑(先诊后付)-9 医保签到-10 医保余额查询-11 医保冲正-12 后台退费-13 预付卡充值-14<br>
	 * 业务编号(1位数) 挂号:1 门诊:2 住院:3 先诊后付的绑卡解绑:4 医保绑卡解绑:5 其他:6<br>
	 * 日期：yyyyMMdd 8位 流水号(7位数 ,且每天从1开始 , 例如:0000001)
	 * </p>
	 * 
	 * @param orderType
	 *            订单类型
	 * @param tradeType
	 *            交易类型
	 * @param bizType
	 *            业务编号
	 * @return
	 */
	public static String genOrderNo(int orderType, int tradeType, int bizType) {
		if (orderType != 0 && tradeType != 0 && bizType != 0) {
			StringBuffer sb = new StringBuffer();
			// 订单来源
			sb.append(orderType);

			// 交易类型
			if (tradeType < 10) {
				sb.append(COVER_CHAR + tradeType);
			} else {
				sb.append(tradeType);
			}

			// 业务编号
			sb.append(bizType);

			// 服务器编号
			// sb.append(serverNo);

			// 日期
			sb.append(YYYYMMDD.format(new Date()));

			// 流水号 是以7位数000001为起始值
			sb.append(getOrderBaseSerialNum(GlobalConstant.EH_COMMON_ORDER_SERIAL_NUMBER));
			return sb.toString();
		} else {
			return null;
		}
	}

	/**
	 * 提供pos机器自增流水号 2016年11月16日 10:40:40 党参增加
	 * 
	 * @param orderCacheKey
	 * @return modify by yuce 将java锁换为全局的redis分布式锁
	 */
	public static String getOrderSerialNum(String orderCacheKey) {
		return getOrderBaseSerialNum(orderCacheKey);
	}

	/**
	 * 订单缓存类型 GlobalConstant.YCT_COMMON_ORDER_SERIAL_NUMBER 普通订单流水号 GlobalConstant.YCT_MEDICARE_ORDER_SERIAL_NUMBER 医保订单流水号
	 * 
	 * @param orderCacheKey
	 * @return
	 */
	public static String getOrderBaseSerialNum(String orderCacheKey) {
		RedisService redisService = SpringContextHolder.getBean(RedisService.class);
		long nowDay = Long.valueOf(YYYYMMDD.format(new Date()));
		StringBuffer serialNumSb = new StringBuffer();
		if (nowDay > orderDay) {
			RedisLock redisLock = new RedisLock(redisService.getRedisPool());
			boolean isLock = false;
			String orderNoDate = null;
			try {
				do {
					isLock = redisLock.singleLock(ORDERNO_LOCKED_KEY, ORDERNO_LOCKED_TIME_OUT);
				} while (!isLock);
				if (nowDay > orderDay) {
					orderNoDate = redisService.get(ORDERNO_GEN_NUMBER_DATE);
					// 是否需要更新生产订单的日期和订单编号重新计数
					boolean isUpdateGenOrderConfig = false;
					if (StringUtils.isNotBlank(orderNoDate) && !CacheConstant.CACHE_KEY_NOT_EXIST.equalsIgnoreCase(orderNoDate)) {
						if (Long.valueOf(orderNoDate).longValue() < nowDay) {
							isUpdateGenOrderConfig = true;
						} else if (Long.valueOf(orderNoDate).longValue() == nowDay) {
							orderDay = nowDay;
						}
					} else {
						isUpdateGenOrderConfig = true;
					}

					if (isUpdateGenOrderConfig) {
						orderDay = nowDay;
						redisService.set(ORDERNO_GEN_NUMBER_DATE, nowDay);
						redisService.set(orderCacheKey, 0);
					}
				}
			} finally {
				redisLock.singleUnlock(ORDERNO_LOCKED_KEY);
			}
		}

		// redis生产 订单流水号
		String serialNum = redisService.incr(orderCacheKey).toString();
		logger.info("订单流水号生成,time:{},redis incr:{}", YYYYMMDDHHMMSS.format(new Date()), serialNum);
		int len = 7;
		if (GlobalConstant.EH_MEDICARE_ORDER_SERIAL_NUMBER.equals(orderCacheKey)) {
			len = 6;
		}
		if (serialNum.length() < len) {
			int length = serialNum.length();
			for (int i = 0; i < len - length; i++) {
				serialNumSb.append(COVER_CHAR);
			}
		}
		serialNumSb.append(serialNum);
		return serialNumSb.toString();
	}
}
