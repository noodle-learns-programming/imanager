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
			//��ȡ�û���¼����
			String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
			if (StringUtils.isBlank(recordType)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				}
				return null;
			}
			
			//��ȡ�û���¼ֵ��
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
			
			//��õ�ǰ��¼�û�ID
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
					log.info("Cannot get \"" + recordDomainLoginId + "\"!");
				} else if (log.isErrorEnabled()) {
					log.error("Cannot get \"" + recordDomainLoginId + "\"!");
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
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#recordCurrentLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean loginCurrentUser(String loginId){
		try {
			//��ȡ�û���¼����
			String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
			if (StringUtils.isBlank(recordType)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				}
				return false;
			}
			
			//��ȡ�û���¼ֵ��
			String recordDomainLoginId = EnvService.getValueByProperty(EnvService.RECORD_DOMAIN_LOGIN_ID);
			if (StringUtils.isBlank(recordDomainLoginId)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
				}
				return false;
			}
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
			
			//��¼��ǰ�û�loginId
			if (EnvService.RECORD_TYPE_COOKIE.equals(recordType)) {
				//��ȡcookie��Чʱ��
				int effectiveTime = 0;
				String effectiveTimeStr = EnvService.getValueByProperty(EnvService.COOKIE_EFFECTIVE_TIME);
				if (StringUtils.isNotBlank(effectiveTimeStr)) {
					effectiveTime = (int)(Integer.valueOf(effectiveTimeStr) * SECONDS_AN_HOUR);
				}
				
				//����cookie
				Cookie loginIdCookie = new Cookie(recordDomainLoginId, loginId);
				//loginIdCookie.setDomain("localhost");
				loginIdCookie.setPath("/");
				if (effectiveTime >= 0) {
					loginIdCookie.setMaxAge(effectiveTime);
				} else {
					loginIdCookie.setMaxAge(-1);
				}
				
				//���cookie
				response.addCookie(loginIdCookie);
			} else if (EnvService.RECORD_TYPE_SESSION.equals(recordType)) {
				//��¼session
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
	
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#logoutCurrentUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean logoutCurrentUser(){
		try {
			//��ȡ�û���¼����
			String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
			if (StringUtils.isBlank(recordType)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_TYPE + "\" is null!");
				}
				return false;
			}
			
			//��ȡ�û���¼ֵ��
			String recordDomainLoginId = EnvService.getValueByProperty(EnvService.RECORD_DOMAIN_LOGIN_ID);
			if (StringUtils.isBlank(recordDomainLoginId)) {
				if (log.isInfoEnabled()) {
					log.info("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
				} else if (log.isErrorEnabled()) {
					log.error("The property of \"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\" is null!");
				}
				return false;
			}
			
			ActionContext ctx = ActionContext.getContext();
			//HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
			
			//ʹ��ǰ�û���¼ʧЧ
			if (EnvService.RECORD_TYPE_COOKIE.equals(recordType)) {
				//����cookie
				Cookie loginIdCookie = new Cookie(recordDomainLoginId, null);
				loginIdCookie.setPath("/");
				loginIdCookie.setMaxAge(0);
				response.addCookie(loginIdCookie);
			} else if (EnvService.RECORD_TYPE_SESSION.equals(recordType)) {
				//ʹsessionʧЧ
				Map<String, String> session = ctx.getSession();
				session.remove(recordDomainLoginId);
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
	
	/**
	 * ����cookie���ƻ��cookie��ֵ
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
		
		for (int i = 0; i < cookies.length; i++) {
		   Cookie c = cookies[i];     
		   if(c.getName().equalsIgnoreCase(recordDomain)) {
			   return c.getValue();
		   }
		}
		return null;
	}
	
	/**
	 * ����cookie���ƻ��cookie
	 * @param request
	 * @param recordDomain
	 * @return
	 */
	/*private Cookie getCookieByRecordDomain(HttpServletRequest request, String recordDomain) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			if (log.isInfoEnabled()) {
				log.info("Cannot get cookies!");
			} else if (log.isErrorEnabled()) {
				log.error("Cannot get cookies!");
			}
			return null;
		}
		
		for (int i = 0; i < cookies.length; i++) {
		   Cookie c = cookies[i];     
		   if(c.getName().equalsIgnoreCase(recordDomain)) {
			   return c;
		   }
		}
		return null;
	}*/
	

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
