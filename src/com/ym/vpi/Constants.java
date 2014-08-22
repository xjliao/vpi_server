package com.ym.vpi;

import java.util.Properties;

public class Constants {
	public static Properties appConf = null;
	
	//读取app.properties里的目录配置
	public static String getAppConf(String key){if(appConf == null)return null;return appConf.getProperty(key);}
}
