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
	
	public static final String RECORD_TYPE = "recordType";	//�û���¼���¼����
	public static final String RECORD_DOMAIN_LOGIN_ID = "recordDomainLoginId";	//��¼�û���ֵ��
	public static final String RECORD_TYPE_COOKIE = "cookie";	//��cookie��¼
	public static final String RECORD_TYPE_SESSION = "session";	//��session��¼
	public static final String COOKIE_EFFECTIVE_TIME = "cookieEffectiveTime";	//cookie����Чʱ��
	public static final String APP_URL = "appUrl";	//Ӧ�õ�url
	public static final String SRC_URL = "srcUrl";	//��Դ��url
	public static final String SRC_DIR = "srcDir";	//��Դ��Ŀ¼
	public static final String FOLDERS = "folders";	//��Ҫ�������ļ���
	public static final String PIC_TYPE = "picType";	//�����ŵ���Ƭ����
	public static final String PIC_SIZE = "picSize";	//�����ŵ���Ƭ��С

	/** ����������Ϣ */
    static Properties properties = new Properties();

    /**
     * ��ʼ����������
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
	 * ��û��������ľ���ֵ
	 * @param property
	 * @return
	 */
	public static String getValueByProperty(String property) {
		return properties.getProperty(property); 
	}
}
