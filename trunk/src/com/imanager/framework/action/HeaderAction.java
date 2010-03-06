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
 * 判断天气预报是否能访问，若不能访问则不显示
 * @author yangqiang
 * @since 2010.3.6 21:48
 */
public class HeaderAction extends ActionSupport {
	private static final long serialVersionUID = -591373720030159808L;
	
	private String canWeatherShow;
	
	// 检测天气预报是否能显示
	public String canWeatherShow() {
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//创建GET方法的实例
		// 杭州：http://m.weather.com.cn/m/p2/weather1.htm?id=101210101T
		// 日照：http://m.weather.com.cn/m/p2/weather1.htm?id=101121501T
		GetMethod getMethod = new GetMethod("http://m.weather.com.cn/m/p2/weather1.htm?id=101210101T");
		//使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				canWeatherShow = "false";
			} else {
				canWeatherShow = "true";
			}
			//读取内容 
			//byte[] responseBody = getMethod.getResponseBody();
			//处理内容
			//System.out.println(new String(responseBody));
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			//System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		} finally {
			//释放连接
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
