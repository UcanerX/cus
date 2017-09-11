package com.sunshine.platform.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sunshine.common.GlobalConstant;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.platform.security.dao.UserDao;
import com.sunshine.platform.security.dao.UserRoleDao;
import com.sunshine.platform.security.entity.User;
import com.sunshine.platform.security.entity.UserRole;
import com.sunshine.platform.security.service.UserService;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {
	private UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	private UserRoleDao userRoleDao = SpringContextHolder.getBean(UserRoleDao.class);

	@Override
	protected BaseDao<User, String> getDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		String userId = userDao.insert(user);

		// 保存user-role关系表
		if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
			List<UserRole> userRols = new ArrayList<>();
			for (String roleId : user.getRoleIdList()) {
				userRols.add(new UserRole(userId, roleId, GlobalConstant.STATUS_VALID));
			}
			userRoleDao.batchInsert(userRols);
		}
		return userId;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		// 更新user-role关系表

		// 删除旧的用户与角色关系
		userRoleDao.deleteByUserId(user.getId());

		// 保存新的用户与角色关系
		if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
			List<UserRole> userRoles = new ArrayList<>();
			for (String roleId : user.getRoleIdList()) {
				userRoles.add(new UserRole(user.getId(), roleId, GlobalConstant.STATUS_VALID));
			}
			userRoleDao.batchInsert(userRoles);
		}
	}

	@Override
	public Long deleteByIds(List<String> userIds) {
		List<UserRole> list = userRoleDao.findUserRoleByUserList(userIds);
		List<String> userRoleIdList = new ArrayList<>();
		for (UserRole sur : list) {
			userRoleIdList.add(sur.getId());
		}
		if (userRoleIdList.size() > 0) {
			userRoleDao.deleteByIds(userRoleIdList); // 删除用户角色表数据
		}

		return userDao.deleteByIds(userIds);
	}

	@Override
	public User findUserByAccount(String account) {
		return userDao.findUserByAccount(account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sunshine.platform.security.service.UserService#isUniqueName(com.sunshine.platform.security.entity.SysUser)
	 */
	@Override
	public boolean isUniqueAccount(User user) {
		// TODO Auto-generated method stub
		String id = user.getId();
		User entity = userDao.findUserByAccount(user.getAccount());
		if (entity == null) {
			return true;
		} else {
			if (entity.getId().equals(id)) {
				return true;
			} else {
				return false;
			}
		}
	}

}
