/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JasonInternational Ucanx</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.common;

/**
* @Package：cn.ucaner.common   
* @ClassName：HashSplitTableGenerator   
* @Description：   <p> hash取模算法分表表名生成器</p>
* @Author： - DaoDou 
* @CreatTime：2017年8月30日 下午2:33:31   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class HashSplitTableGenerator {
	/**
	 * 得到hsah子表名</p>
	 * @param tableName      原表名<br>
	 * @param splitKeyVal    hash取模的关键值(根据splitKeyVal值来取模)<br>
	 * @param subTableCount  要拆分子表总数<br>
	 * @param isNeedUniform  是否需要均匀散列<br>
	 * @return
	 */
	public static String getSplitTableName(String tableName, Object splitKeyVal, Integer subTableCount, Boolean isNeedUniform) {
		long hashVal = 0;
		if (splitKeyVal instanceof Number) {
			hashVal = Integer.parseInt(splitKeyVal.toString());
		} else {
			hashVal = splitKeyVal.toString().hashCode();
		}

		//斐波那契（Fibonacci）散列
		if (isNeedUniform) {
			hashVal = ( hashVal * 2654435769L ) >> 28;
		}

		//避免hashVal超出 MAX_VALUE = 0x7fffffff时变为负数,取绝对值
		hashVal = Math.abs(hashVal) % subTableCount;
		String splitableName = tableName + "_" + ( hashVal + 1 );
		return splitableName;
	}
}
