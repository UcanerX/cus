package com.sunshine.platform.security.service;

import com.sunshine.framework.mvc.mysql.service.BaseSQLService;
import com.sunshine.platform.security.entity.User;

public interface UserService extends BaseSQLService<User, String> {

	/**
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);

	/**
	 * @param user
	 * @return
	 */
	public boolean isUniqueAccount(User user);

	/**
	 * @Description 保存新用户
	 * @param user
	 * @return 用户Id
	 * @date 2017年7月3日
	 */
	public String saveUser(User user);

	/**
	 * @Description 更新用户信息
	 * @param user
	 * @date 2017年7月3日
	 */
	public void updateUser(User user);
}
