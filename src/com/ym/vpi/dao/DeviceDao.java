package com.ym.vpi.dao;

import com.ym.vpi.model.Device;

public interface DeviceDao {
	//添加device
	public boolean saveDevice(Device device);
	
	//删除device
	public boolean deleteDevice(Device device);
}
