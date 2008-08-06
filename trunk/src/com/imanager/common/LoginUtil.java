package com.imanager.common;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class LoginUtil {
	
	/**
	 * 获得当前登录的LoginId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getCurrentLogin() {
		ActionContext ctx = ActionContext.getContext();
		Map<String, String> session = ctx.getSession();
		String loginId = session.get("loginId");
		if(StringUtils.isBlank(loginId)){
			return null;
		}else{
			return loginId;
		}
	}
	
	public String getLoginIdFromCookies(){
		
		String currentLoginId = null;
		
		//获得当前登录用户ID
		Cookie[] cookies = this.getRequest().getCookies();
		if(cookies!=null){
			
			int len = cookies.length;
			int i = 0;
			
		    for (i = 0; i < len; i++) 
		    {
		       Cookie c = cookies[i];     
		       if(c.getName().equalsIgnoreCase("loginId"))
		       {
		    	   currentLoginId = c.getValue();
		    	   break;
		       }
		    }
		    
		    if(currentLoginId != null){
		    	return currentLoginId;
		    }else{
		    	return "checkcookies";
		    }
		}else{
			return "checkcookies";
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
