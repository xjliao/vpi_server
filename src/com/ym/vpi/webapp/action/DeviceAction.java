package com.ym.vpi.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.ym.vpi.model.Device;
import com.ym.vpi.service.DeviceManager;

@ParentPackage(value = "default")
@Namespace(value = "")
public class DeviceAction extends AppBaseAction {
	private DeviceManager deviceManager;
	private Device device;
	private String deviceToken;	
	
	public void registerDevice () {
		ServletActionContext.getResponse().setContentType("text/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		System.out.println("deviceToken:" + deviceToken);
		String msg = "设备注册失败";
		
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			
			if (null != deviceToken && !"".equals(deviceToken)) {
				device = new Device();
				device.setDeviceToken(deviceToken);
				boolean register = deviceManager.registerDevice(device);
				
				if (register) {
					msg = "设备注册完成";
				}
			}
			
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			
			out.write(obj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DeviceManager getDeviceManager() {
		return deviceManager;
	}
	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}	
}
