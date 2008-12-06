package com.imanager.framework.service;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class EnvService{
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EnvService.class);
	
	public static final String RECORD_TYPE = "recordType";
	public static final String RECORD_TYPE_COOKIE = "cookie";
	public static final String RECORD_TYPE_SESSION = "session";

	/** 环境变量信息 */
    static Properties properties=new Properties();
    /** 从配置文件中获得配置文件信息，静态初始化，每次只读一次配置文件 */
	static {
		try {
			properties = PropertiesLoaderUtils.loadAllProperties("env.properties");
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				log.info(e.toString());
			} else if (log.isErrorEnabled()) {
				log.error(e.toString());
			}
		}
	}
	

	/**
	 * 获得环境变量的具体值
	 * @param property
	 * @return
	 */
	public static String getValueByProperty(String property) {
		return properties.getProperty(property); 
	}
}
