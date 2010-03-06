package com.imanager.framework.action;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import com.opensymphony.xwork.ActionSupport;

/**
 * �ж�����Ԥ���Ƿ��ܷ��ʣ������ܷ�������ʾ
 * @author yangqiang
 * @since 2010.3.6 21:48
 */
public class HeaderAction extends ActionSupport {
	private static final long serialVersionUID = -591373720030159808L;
	
	private String canWeatherShow;
	
	// �������Ԥ���Ƿ�����ʾ
	public String canWeatherShow() {
		//����HttpClient��ʵ��
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//����GET������ʵ��
		// ���ݣ�http://m.weather.com.cn/m/p2/weather1.htm?id=101210101T
		// ���գ�http://m.weather.com.cn/m/p2/weather1.htm?id=101121501T
		GetMethod getMethod = new GetMethod("http://m.weather.com.cn/m/p2/weather1.htm?id=101210101T");
		//ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			//ִ��getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				canWeatherShow = "false";
			} else {
				canWeatherShow = "true";
			}
			//��ȡ���� 
			//byte[] responseBody = getMethod.getResponseBody();
			//��������
			//System.out.println(new String(responseBody));
		} catch (HttpException e) {
			//�����������쳣��������Э�鲻�Ի��߷��ص�����������
			//System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			//���������쳣
			e.printStackTrace();
		} finally {
			//�ͷ�����
			getMethod.releaseConnection();
		}
		
		return "canWeatherShow";
	}

	public String getCanWeatherShow() {
		return canWeatherShow;
	}

	public void setCanWeatherShow(String canWeatherShow) {
		this.canWeatherShow = canWeatherShow;
	}

}
