package com.shiliu.game.utils;



import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {
	private static final Logger logger = Logger.getLogger(PropertyUtil.class);
	private static Properties properties;
	
	static {
		// 加载属性文件
		try {
			InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
			try {
				properties = new Properties();
				properties.load(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 加载整型配置
	 * @param key 配置名称
	 * @return 配置值
	 */
	public static int getIntProperty(String key) {
		return getIntProperty(key, 0);
	}

	/**
	 * 加载整型配置
	 * @param key 配置名称
	 * @param def 默认值
	 * @return 配置值
	 */
	public static int getIntProperty(String key, int def) {
		String value = properties.getProperty(key);
		if(value == null || "".equals(value))	return def;
		return Integer.valueOf(value);
	}
	
	public static String getProperty(String key, String def) {
		String value = getProperty(key);
		if(value == null || "".equals(value))	return def;
		return value;
	}
}
