package com.imanager.common;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.framework.service.EnvService;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class DoCheckLogin implements Interceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(DoCheckLogin.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String recordType = EnvService.getValueByProperty("recordType");
		
		if (StringUtils.isBlank(recordType)) {
			if (log.isDebugEnabled()) {
				log.info("The property of \"recordType\" is null!");
			} else if (log.isErrorEnabled()) {
				log.error("The property of \"recordType\" is null!");
			}
			return "checklogin";
		}
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		String currentLoginId = null;
		
		if (EnvService.RECORD_TYPE_COOKIE.equals(recordType)) {
			//获得当前登录用户ID
			Cookie[] cookies = request.getCookies();
			if (cookies == null) {
				if (log.isDebugEnabled()) {
					log.info("Cannot get cookies!");
				} else if (log.isErrorEnabled()) {
					log.error("Cannot get cookies!");
				}
				return "checklogin";
			}
			
			int len = cookies.length;
			int i = 0;
		    for (i = 0; i < len; i++) {
		       Cookie c = cookies[i];     
		       if(c.getName().equalsIgnoreCase("loginId")) {
		    	   currentLoginId = c.getValue();
		    	   break;
		       }
		    }
		    
		} else if (EnvService.RECORD_TYPE_SESSION.equals(recordType)) {
			Map<String, String> session = ctx.getSession();
			currentLoginId = session.get("loginId");
		}
		
		if (StringUtils.isNotBlank(currentLoginId)) {
			return invocation.invoke();
	    } else {
	    	if (log.isDebugEnabled()) {
				log.info("Cannot get \"loginId\"!");
			} else if (log.isErrorEnabled()) {
				log.error("Cannot get \"loginId\"!");
			}
	    }
		
		return "checklogin";
	}
	
}
