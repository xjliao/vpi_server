package com.ym.vpi.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ym.vpi.dao.DeviceDao;
import com.ym.vpi.model.Article;
import com.ym.vpi.model.Device;

public class DeviceDaoImpl implements DeviceDao {
	private JdbcTemplate jdbcTemplate;
	private HibernateTemplate hibernateTemplate;
	private Device device;
	
	
	@Override
	public boolean saveDevice(Device device) {
		// TODO Auto-generated method stub
		if (hibernateTemplate.save(device) != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean deleteDevice(Device device) {
		// TODO Auto-generated method stub
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

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
}
