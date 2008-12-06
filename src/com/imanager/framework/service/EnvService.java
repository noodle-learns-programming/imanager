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

	/** ����������Ϣ */
    static Properties properties=new Properties();
    /** �������ļ��л�������ļ���Ϣ����̬��ʼ����ÿ��ֻ��һ�������ļ� */
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
	 * ��û��������ľ���ֵ
	 * @param property
	 * @return
	 */
	public static String getValueByProperty(String property) {
		return properties.getProperty(property); 
	}
}
