package com.imanager.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
		
		if (StringUtils.isNotBlank(loginService.getCurrentLoginId())) {
			return invocation.invoke();
	    } else {
	    	if (log.isInfoEnabled()) {
				log.info("Cannot get cookie!");
			} else if (log.isErrorEnabled()) {
				log.error("Cannot get cookie!");
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
