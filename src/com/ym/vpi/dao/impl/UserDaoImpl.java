package com.ym.vpi.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ym.vpi.dao.UserDao;
import com.ym.vpi.model.User;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;
	private HibernateTemplate hibernateTemplate;
	private List<User> users;
	private User user;

	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(Long id) {
		String sqlStr = "from User u where u.id = " + id;
		users = (List<User>) hibernateTemplate.find(sqlStr);

		if (null != users && users.size() > 0) {
			for (int i = 0, n = users.size(); i < n; i++) {
				user = (User) users.get(i);

				return user;
			}
		}

		return null;
	}

	//某些时候通过用户名获取用户
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) {
		String sqlStr = "from User u where u.username = '" + username + "'";
		users = (List<User>) hibernateTemplate.find(sqlStr);

		if (null != users && users.size() > 0) {
			for (int i = 0, n = users.size(); i < n; i++) {
				user = (User) users.get(i);

				return user;
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String username, String password) {
		String sqlStr = "from User u where u.username = '" + username
				+ "' and u.password = '" + password + "'";
		users = (List<User>) hibernateTemplate.find(sqlStr);

		if (null != users && users.size() > 0) {
			for (int i = 0, n = users.size(); i < n; i++) {
				user = users.get(i);

				return user;
			}
		}

		return null;
	}

	@Override
	// 新建用户
	public boolean save(User user) {
		if (hibernateTemplate.save(user) != null) {
			return true;
		}

		return false;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
