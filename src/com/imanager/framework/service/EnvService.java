package com.imanager.framework.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@SuppressWarnings("unchecked")
public class EnvService{
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EnvService.class);
	
	public static final String RECORD_TYPE = "recordType";	//用户登录后记录类型
	public static final String RECORD_DOMAIN_LOGIN_ID = "recordDomainLoginId";	//记录用户的值域
	public static final String RECORD_TYPE_COOKIE = "cookie";	//用cookie记录
	public static final String RECORD_TYPE_SESSION = "session";	//用session记录
	public static final String COOKIE_EFFECTIVE_TIME = "cookieEffectiveTime";	//cookie的有效时间
	public static final String APP_URL = "appUrl";	//应用的url
	public static final String SRC_URL = "srcUrl";	//资源的url
	public static final String SRC_DIR = "srcDir";	//资源的目录
	public static final String FOLDERS = "folders";	//需要创建的文件夹
	public static final String PIC_TYPE = "picType";	//允许存放的照片类型
	public static final String PIC_SIZE = "picSize";	//允许存放的照片大小

	/** 环境变量信息 */
    static Properties properties = new Properties();

    /**
     * 初始化环境变量
     */
   static {
		try {
			FileInputStream fileIn = new FileInputStream("d:/env.properties");
			if (fileIn.available() == 0) {
				properties = PropertiesLoaderUtils.loadAllProperties("env.properties");
				FileOutputStream fileOut = new FileOutputStream("d:/env.properties");
				for (Entry entry : properties.entrySet()) {
					//System.out.println(entry.toString());
					fileOut.write(entry.toString().getBytes());
					fileOut.write("\r\n".getBytes());
				}
			} else {
				properties.load(fileIn);
			}
		} catch (FileNotFoundException e) {
			try {
				properties = PropertiesLoaderUtils.loadAllProperties("env.properties");
				FileOutputStream fileOut = new FileOutputStream("d:/env.properties");
				for (Entry entry : properties.entrySet()) {
					//System.out.println(entry.toString());
					fileOut.write(entry.toString().getBytes());
					fileOut.write("\r\n".getBytes());
				}
			} catch (FileNotFoundException e1) {
				if (log.isErrorEnabled()) {
					log.error(e1.toString());
				}
			} catch (IOException e2) {
				if (log.isErrorEnabled()) {
					log.error(e2.toString());
				}
			}
		} catch (IOException e3) {
			if (log.isErrorEnabled()) {
				log.error(e3.toString());
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
