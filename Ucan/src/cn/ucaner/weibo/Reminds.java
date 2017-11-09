/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.weibo;

import java.util.Map;

import cn.ucaner.weibo.model.PostParameter;
import cn.ucaner.weibo.model.WeiboException;
import cn.ucaner.weibo.org.json.JSONObject;
import cn.ucaner.weibo.util.ArrayUtils;
import cn.ucaner.weibo.util.WeiboConfig;


public class Reminds extends Weibo {

	private static final long serialVersionUID = 5042162449339969435L;

	public Reminds(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * 获取某个用户的各种消息未读数
	 * 
	 * 
	 * @return
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.2
	 * @see http://open.weibo.com/wiki/2/remind/unread_count
	 * @since JDK 1.5
	 */
	public JSONObject getUnreadCountOfRemind() throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "remind/unread_count.json",
				access_token).asJSONObject();
	}

	public JSONObject getUnreadCountOfRemind(String callback)
			throws WeiboException {
		return client
				.get(WeiboConfig.getValue("baseURL")
						+ "remind/unread_count.json",
						new PostParameter[] { new PostParameter("callback",
								callback) }, access_token).asJSONObject();
	}

	public JSONObject getUnreadCountOfRemind(Map<String, String> map)
			throws WeiboException {
		PostParameter[] parList = ArrayUtils.mapToArray(map);
		return client.get(
				WeiboConfig.getValue("baseURL") + "remind/unread_count.json",
				parList, access_token).asJSONObject();
	}

}
