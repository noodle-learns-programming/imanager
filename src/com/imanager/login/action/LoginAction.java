package com.imanager.login.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.Md5Encode;
import com.imanager.framework.action.BaseAction;
import com.imanager.login.service.ILoginService;
import com.imanager.user.domain.User;
import com.imanager.user.service.IUserService;

/**
 * 登录
 * @author Yang Qiang
 * @since 2008-08-03
 */

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	// Service
	private ILoginService loginService;
	private IUserService userService;
	
	// Domain or Var
	private String loginId;
	private String password;


	/**
	 * 初始化登录页面
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception {
		return "init";
	}
	
	/**
	 * 验证用户登录
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String validateUser() throws Exception {
		try {
			//根据用户名和密码获取用户
			String loginIdTrim = loginId.trim();
			String passwordTrim = password.trim();
			User user = userService.getUserByLoginIdNPassword(loginIdTrim, Md5Encode.MD5(passwordTrim));
			
			if(user == null){
				addActionError("用户名或密码错误！");
				return "validateUserInput";
			}
			
			//登入当前用户，记录当前登录用户的loginId
			if (!loginService.loginCurrentUser(loginIdTrim)) {
				addActionError("系统错误：记录当前登录用户出错！");
				return ERROR;
			}
			
			//更新上次登录时间
			if (!loginService.updateLastLoginDate(user.getLoginId())) {
				addActionError("系统错误：更新上次登录时间出错！");
				return ERROR;
			}
		}catch (Exception e){
			//log.error("Error: " + LoginAction.class + ", validateUser()",e);
			log.error(e.getMessage());
			addActionError("系统错误：验证是否合法用户出错！");
			return ERROR;
		}
		
		return "validateUser";
	}
	
	public String logoutUser() throws Exception {
		try {
			if (!loginService.logoutCurrentUser()) {
				addActionError("系统错误：登出当前用户出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：登出当前用户出错！");
			return ERROR;
		}
		
		return "logoutUser";
	}
	

	
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
}
