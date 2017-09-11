package com.sunshine.common;

/**
 * @Package com.sunshine.common
 * @ClassName OrderConstant
 * @Statement <p>订单状态常量定义</p>
 * @JDK version used: 1.7
 * @Author: 无名子
 * @Create Date: 2016-4-27
 * @modify-Author: 
 * @modify-Date:   
 * @modify-Why/What: 
 * @Version 1.0
 */
public class OrderConstant {
	/**
	 * 订单状态  未支付   1<br>
	 */
	public static final int STATE_NO_PAYMENT = 1;

	/**
	 * 订单状态  已支付   2<br>
	 */
	public static final int STATE_PAYMENT = 2;

	/**
	 * 订单状态  已退费   3<br>
	 */
	public static final int STATE_REFUND = 3;

	/**
	 * 订单支付中   4<br>
	 */
	public static final int STATE_PAYMENTING = 4;

	/**
	 * 订单退费中   5<br>
	 */
	public static final int STATE_REFUNDING = 5;

	/**
	 * 订单已经关闭   6<br>
	 */
	public static final int STATE_CLOSE = 6;

	/**
	 * 订单状态 未退费   7<br>
	 */
	public static final int STATE_NO_REFUND = 7;

	/**
	 * 订单状态 冲正  8<br>
	 */
	public static final int STATE_REVERSAL = 8;
	
	
	/**
	 * 发送订单提醒信息的提前时间（分钟）<br>
	 */
	public static final int MSG_PROMPT_OFFSETTIME_IN_MINUTE = 30;
	
	/**
	 * 电话咨询订单可呼叫的提前时间（分钟）<br>
	 */
	public static final int PHONE_PROMPT_OFFSETTIME_IN_MINUTE = 5;
	
	/**
	 * 发送订单即将开始提醒消息提前时间（分钟）<br>
	 */
	public static final int BEFORE_REACHING_TIME_PROMPT_IN_MINUTE = 5;
	
	/**
	 * 每天剩余预约时间缓存时间（秒）<br>
	 */
	public static final int APPOINTMENT_TIME_ALIVE_IN_SECONDS = 26 * 60 * 60;// 26小时，只需24小时，延长一段时间

	/**
	 * 预约提前时间（毫秒），当前15天<br>
	 */
	public static final int APPOINTMENT_DAYS_IN_FUTURE_IN_MILLIS = 15 * 24 * 60 * 60 * 1000;
	
	/**
	 * 预约提前时间（天），当前15天<br>
	 */
	public static final int APPOINTMENT_DAYS_IN_FUTURE_IN_DAYS = 15;
	
	/**
	 * 预约最迟提前时间（毫秒），30分钟<br>
	 */
	public static final int APPOINTMENT_LEAST_ACCEPT_TIME_IN_MILLIS = 30 * 60 * 1000;
	
	
	/**
	 * 已提醒的订单缓存（秒），（30分钟前发送提示信息）<br>
	 */
	public static final int PROMPTED_ORDER_TIME_IN_SECONDS = 1 * 60  * 60;
	
	
}
