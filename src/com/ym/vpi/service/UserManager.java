package com.ym.vpi.service;

import com.ym.vpi.model.User;

public interface UserManager {
	// 注册
	public boolean register(User u);

	// 登陆
	public boolean login(String username, String password);

	// 通过用户名获取用户
	public User getUserByUsername(String username);

	// 通过用户id获取用户
	public User getUserById(Long id);
}
