package com.imanager.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class DoCheckLoginSessions implements Interceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(DoCheckLoginSessions.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String currentLoginId = new LoginUtil().getCurrentLogin();
		
		if(currentLoginId == null){
			log.info("There no sessions here !");
			return "nosessions";
		}else{
			//log.info("The current login id is: " + currentLoginId);
			return invocation.invoke();
		}
	}
	
    /**
     * Convenience method to get the request
     * @return current request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();  
    }
    /**
     * Convenience method to get the response
     * @return current response
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }
    /**
     * Convenience method to get the session
     */
    protected HttpSession getSession() {
    	return getRequest().getSession();
    }


	
}
