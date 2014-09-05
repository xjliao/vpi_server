package com.ym.vpi.service.impl;

import com.ym.vpi.dao.DeviceDao;
import com.ym.vpi.model.Device;
import com.ym.vpi.service.DeviceManager;

public class DeviceManagerImpl implements DeviceManager {
	private DeviceDao deviceDao;
	private Device device;
	@Override
	public boolean registerDevice(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.saveDevice(device);
	}
	public DeviceDao getDeviceDao() {
		return deviceDao;
	}
	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

}
