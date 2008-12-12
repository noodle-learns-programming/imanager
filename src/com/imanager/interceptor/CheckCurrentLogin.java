package com.imanager.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.login.service.ILoginService;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class CheckCurrentLogin implements Interceptor {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CheckCurrentLogin.class);
	
	// Service
	private ILoginService loginService;
	
	public void destroy() {
	}

	public void init() {
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		
		if (StringUtils.isNotBlank(loginService.getCurrentLoginId())) {
			return invocation.invoke();
	    } else {
	    	return "checklogin";
	    }
		
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
}
