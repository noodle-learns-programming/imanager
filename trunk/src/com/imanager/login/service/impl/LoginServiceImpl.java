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
	
	public static final long SECONDS_AN_HOUR = 60 * 60;
	
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
	public String getCurrentLoginId(){
		try {
			//获取用户记录类型
			String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
			if (StringUtils.isBlank(recordType)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				}
				return null;
			}
			
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
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			String currentLoginId = null;
			
			//获得当前登录用户ID
			if (EnvService.RECORD_TYPE_COOKIE.equals(recordType)) {
				currentLoginId = getValueFromCookie(request, recordDomainLoginId);
			} else if (EnvService.RECORD_TYPE_SESSION.equals(recordType)) {
				Map<String, String> session = ctx.getSession();
				currentLoginId = session.get(recordDomainLoginId);
			}
			
			if (StringUtils.isNotBlank(currentLoginId)) {
		    	return currentLoginId;
		    } else {
		    	if (log.isInfoEnabled()) {
					log.info("Cannot get \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\"!");
				} else if (log.isErrorEnabled()) {
					log.error("Cannot get \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\"!");
				}
		    	return null;
		    }
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info(e.getMessage());
			} else if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			return null;
		}
	}

	
	/**
	 * 根据cookie名称获得cookie的值
	 * @param request
	 * @param recordDomain
	 * @return
	 */
	private String getValueFromCookie(HttpServletRequest request, String recordDomain) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			if (log.isInfoEnabled()) {
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
		   if(c.getName().equalsIgnoreCase(recordDomain)) {
			   return c.getValue();
		   }
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#recordCurrentLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean recordCurrentLoginId(String loginId){
		try {
			//获取用户记录类型
			String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
			if (StringUtils.isBlank(recordType)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				}
				return false;
			}
			
			//获取用户记录值域
			String recordDomainLoginId = EnvService.getValueByProperty(EnvService.RECORD_DOMAIN_LOGIN_ID);
			if (StringUtils.isBlank(recordDomainLoginId)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
				}
				return false;
			}
			
			//获取cookie有效时间
			int effectiveTime = 0;
			String effectiveTimeStr = EnvService.getValueByProperty(EnvService.COOKIE_EFFECTIVE_TIME);
			if (StringUtils.isNotBlank(effectiveTimeStr)) {
				effectiveTime = (int)(Integer.valueOf(effectiveTimeStr) * SECONDS_AN_HOUR);
			}
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
			
			//记录当前用户loginId
			if (EnvService.RECORD_TYPE_COOKIE.equals(recordType)) {
				Cookie loginIdCookie = new Cookie(recordDomainLoginId, loginId);
				//loginIdCookie.setDomain("localhost");
				loginIdCookie.setPath("/");
				if (effectiveTime >= 0) {
					loginIdCookie.setMaxAge(effectiveTime);
				} else {
					loginIdCookie.setMaxAge(-1);
				}
				response.addCookie(loginIdCookie);
			} else if (EnvService.RECORD_TYPE_SESSION.equals(recordType)) {
				Map<String, String> session = ctx.getSession();
				session.put(recordDomainLoginId, loginId);
			}
			return true;
			
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info(e.getMessage());
			} else if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			return false;
		}
	}
	

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
