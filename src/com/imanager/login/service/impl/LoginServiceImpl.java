package com.imanager.login.service.impl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.framework.service.EnvService;
import com.imanager.login.dao.ILoginDao;
import com.imanager.login.service.ILoginService;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class LoginServiceImpl implements ILoginService {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServiceImpl.class);
	
	// Dao
	private ILoginDao loginDao;
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#updateLastLoginDate(java.lang.String)
	 */
	public boolean updateLastLoginDate(String loginId){
		return loginDao.updateLastLoginDate(loginId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#getCurrentLoginId()
	 */
	@SuppressWarnings("unchecked")
	public String getCurrentLoginId(String recordType){
		/*String recordType = EnvService.getValueByProperty("recordType");*/
		
		if (StringUtils.isBlank(recordType)) {
			if (log.isDebugEnabled()) {
				log.info("The property of \"recordType\" is null!");
			} else if (log.isErrorEnabled()) {
				log.error("The property of \"recordType\" is null!");
			}
			return null;
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
				return null;
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
	    	return currentLoginId;
	    } else {
	    	if (log.isDebugEnabled()) {
				log.info("Cannot get \"loginId\"!");
			} else if (log.isErrorEnabled()) {
				log.error("Cannot get \"loginId\"!");
			}
	    }
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#recordCurrentLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean recordCurrentLoginId(String recordType, String loginId){
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		if (StringUtils.isBlank(recordType)) {
			if (log.isDebugEnabled()) {
				log.info("The property of \"recordType\" is null!");
			} else if (log.isErrorEnabled()) {
				log.error("The property of \"recordType\" is null!");
			}
			return false;
		}
		
		if (EnvService.RECORD_TYPE_COOKIE.equals(recordType)) {
			Cookie loginIdCookie = new Cookie("loginId", loginId);
			//loginId.setDomain("localhost");
			loginIdCookie.setPath("/");
			loginIdCookie.setMaxAge(-1);
			response.addCookie(loginIdCookie);
		} else if (EnvService.RECORD_TYPE_SESSION.equals(recordType)) {
			Map<String, String> session = ctx.getSession();
			session.put("loginId", loginId);
		}
		
		return true;
	}

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
