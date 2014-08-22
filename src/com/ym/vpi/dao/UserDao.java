package com.ym.vpi.dao;

import com.ym.vpi.model.User;

public interface UserDao {
	// 使用id获取用户
	public User getUserById(Long id);

	// 使用用户名获取用户
	public User getUserByUsername(String username);

	// 使用用户名和密码获取用户
	public User getUser(String username, String password);
	
	//新建用户
	public boolean save(User user);
}
