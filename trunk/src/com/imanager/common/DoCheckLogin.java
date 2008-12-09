package com.imanager.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class DoCheckLogin implements Interceptor {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DoCheckLogin.class);
	
	// Service
	private ILoginService loginService;
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//获取用户记录值域
		String recordDomainLoginId = EnvService.getValueByProperty(EnvService.RECORD_DOMAIN_LOGIN_ID);
		if (StringUtils.isBlank(recordDomainLoginId)) {
			if (log.isInfoEnabled()) {
				log.info("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
			} else if (log.isErrorEnabled()) {
				log.error("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
			}
			return null;
		}
		
		if (StringUtils.isNotBlank(loginService.getCurrentLoginId())) {
			return invocation.invoke();
	    } else {
	    	if (log.isInfoEnabled()) {
				log.info("Cannot get \"" + recordDomainLoginId + "\"!");
			} else if (log.isErrorEnabled()) {
				log.error("Cannot get \"" + recordDomainLoginId + "\"!");
			}
	    }
		
		return "checklogin";
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
}
