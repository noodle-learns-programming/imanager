package com.imanager.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class DoCheckLoginCookies implements Interceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(DoCheckLoginCookies.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Cookie[] cookies = this.getRequest().getCookies();
		if(cookies != null){
			
			int len = cookies.length;
			int i = 0;
			
		    for (i = 0; i < len; i++) 
		    {
		       Cookie c = cookies[i];     
		       if(c.getName().equalsIgnoreCase("loginId"))
		       {
		    	   String name = c.getValue();
		    	   log.info("The login id is: " + name);
		    	   break;
		        }
		    }
		    
		    if(i == len){
		    	log.info("There are no cookies here !");
		    	return "nocookies";
		    }
		    
		    return invocation.invoke();
		}else{
			log.info("There are no cookies here !");
			return "nocookies";
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
