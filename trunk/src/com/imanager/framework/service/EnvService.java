package com.imanager.framework.service;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class EnvService{
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EnvService.class);
	
	public static final String RECORD_TYPE = "recordType";	//用户登录后记录类型
	public static final String RECORD_DOMAIN_LOGIN_ID = "recordDomainLoginId";	//记录用户的值域
	public static final String RECORD_TYPE_COOKIE = "cookie";	//用cookie记录
	public static final String RECORD_TYPE_SESSION = "session";	//用session记录
	public static final String COOKIE_EFFECTIVE_TIME = "cookieEffectiveTime";	//cookie的有效时间
	public static final String APP_URL = "appUrl";	//应用的url
	public static final String PIC_DIR = "picDir";	//联系人照片存放目录
	public static final String PIC_TYPE = "picType";	//允许存放的照片类型
	public static final String PIC_SIZE = "picSize";	//允许存放的照片大小
	
	
	
	

	/** 环境变量信息 */
    static Properties properties=new Properties();
    /** 从配置文件中获得配置文件信息，静态初始化，每次只读一次配置文件 */
	static {
		try {
			properties = PropertiesLoaderUtils.loadAllProperties("env.properties");
		} catch (IOException e) {
			if (log.isInfoEnabled()) {
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
