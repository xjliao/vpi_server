package com.ym.vpi.service.impl;

import com.ym.vpi.dao.UserDao;
import com.ym.vpi.model.User;
import com.ym.vpi.service.UserManager;

public class UserManagerImpl implements UserManager {
	private UserDao userDao;
	private User user;

	@Override
	// 注册
	public boolean register(User u) {
		// 检查是否有相同用户名和新增用户
		user = userDao.getUserByUsername(u.getUsername());

		if (null != user) {
			return false;
		}

		// 新增用户
		boolean saveUser = userDao.save(u);

		if (saveUser) {
			return true;
		}

		return false;
	}

	@Override
	// 登陆
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		user = userDao.getUser(username, password);
		// 使用用户名和密码登陆
		// 登陆成功
		if (null != user) {
			return true;
		}

		return false;
	}

	@Override
	// 通过用户名查找
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByUsername(username);
	}

	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
