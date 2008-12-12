package com.imanager.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.ValidationAware;
import com.opensymphony.xwork.interceptor.Interceptor;

public class CheckEnvProperty implements Interceptor {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CheckEnvProperty.class);
	
	public void destroy() {
	}

	public void init() {
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		
		final Object action = invocation.getAction();
		ValidationAware validation = null;

		if (action instanceof ValidationAware) {
			validation = (ValidationAware) action;
		}
		
		//检查用户记录类型
		String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
		if (StringUtils.isBlank(recordType)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.RECORD_TYPE + "\"的值为空！");
			return "checkenv";
		}
		
		//检查用户记录域
		String recordDomainLoginId = EnvService.getValueByProperty(EnvService.RECORD_DOMAIN_LOGIN_ID);
		if (StringUtils.isBlank(recordDomainLoginId)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\"的值为空！");
			return "checkenv";
		}
		
		//检查cookie有效时间
		String cookieEffectiveTime = EnvService.getValueByProperty(EnvService.COOKIE_EFFECTIVE_TIME);
		if (StringUtils.isBlank(cookieEffectiveTime)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.COOKIE_EFFECTIVE_TIME + "\"的值为空！");
			return "checkenv";
		}
		
		//检查应用URL
		String appUrl = EnvService.getValueByProperty(EnvService.APP_URL);
		if (StringUtils.isBlank(appUrl)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.APP_URL + "\"的值为空！");
			return "checkenv";
		}
		
		//检查图片保存目录
		String picDir = EnvService.getValueByProperty(EnvService.PIC_DIR);
		if (StringUtils.isBlank(picDir)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.PIC_DIR + "\"的值为空！");
			return "checkenv";
		}
		
		//检查图片类型
		String picType = EnvService.getValueByProperty(EnvService.PIC_TYPE);
		if (StringUtils.isBlank(picType)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.PIC_TYPE + "\"的值为空！");
			return "checkenv";
		}
		
		//检查图片大小
		String picSize = EnvService.getValueByProperty(EnvService.PIC_SIZE);
		if (StringUtils.isBlank(picSize)) {
			validation.addActionError("系统错误：环境变量\"" + EnvService.PIC_SIZE + "\"的值为空！");
			return "checkenv";
		}
		
		return invocation.invoke();
	}
	
}
