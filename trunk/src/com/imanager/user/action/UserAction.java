package com.imanager.user.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.Md5Encode;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.imanager.user.domain.User;
import com.imanager.user.exception.UserServiceException;
import com.imanager.user.service.IUserService;

public class UserAction extends BaseAction{

	private static final long serialVersionUID = 13453454L;
	private static final Log log = LogFactory.getLog(UserAction.class);
	
	// Service
	private IUserService userService;
	private ILoginService loginService;
	
	// Domain or Var
	private User user = new User();
	private String currentLoginId;
	private String oldPassword;
	private String newPassword;
	
	
	/**
	 * 初始化
	 * @return
	 * @throws Exception
	 */
	public String initRegisterUser() throws Exception {
		return "initRegisterUser";
	}
	
	/**
	 * 注册一个用户
	 * @return
	 * @throws Exception
	 */
	public String registerUser() throws Exception {
		String loginId = user.getLoginId();
		user.setCreator(loginId);
		user.setModifier(loginId);
		String password = Md5Encode.MD5(user.getPassword().trim());
		user.setPassword(password);
		try {
			userService.registerUser(user);
			loginService.recordCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString(), user.getLoginId());
		} catch (UserServiceException e) {
			addActionError(e.toString());
			return "registerUserInput";
		} catch (Exception e) {
			log.error(e.toString());
			addActionError(e.toString());
			return ERROR;
		}
		
		return "registerUser";
	}
	
	/**
	 * 获得当前登录用户的信息
	 * @return
	 * @throws Exception
	 */
	public String initUpdateUserInfo() throws Exception {
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try {
			user = userService.getUserByLoginId(currentLoginId);
		} catch (Exception e) {
			log.error(e.toString());
			addActionError(e.toString());
			return ERROR;
		}
		
		return "initUpdateUserInfo";
	}
	
	/**
	 * 更新用户信息
	 * @return
	 * @throws Exception
	 */
	public String updateUserInfo() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		String userNameTrim = user.getUserName().trim();
		user.setUserName(userNameTrim);
		user.setModifier(currentLoginId);
		user.setLoginId(currentLoginId);
		
		try {
			if (userService.updateUserInfo(user)) {
				return "updateUserInfo";
			} else {
				addActionError("系统错误：更新用户信息出错!");
				return ERROR;
			}
		} catch (Exception e) {
			log.error(e.toString());
			addActionError(e.toString());
			return ERROR;
		}
	}
	
	/**
	 * 初始化修改用户密码
	 * @return
	 * @throws Exception
	 */
	public String initUpdateUserPassword() throws Exception {
		
		return "initUpdateUserPassword";
	}
	
	/**
	 * 更新用户密码
	 * @return
	 * @throws Exception
	 */
	public String updateUserPassword() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try {
			String pwd = userService.getUserByLoginId(currentLoginId).getPassword();
			if (StringUtils.isBlank(pwd)) {
				addActionError("原密码不存在！");
				return ERROR;
			}
			
			if (!pwd.equals(Md5Encode.MD5(oldPassword.trim()))) {
				addActionError("原密码错误！");
				return ERROR;
			}
			
			String newPasswrodTrim = Md5Encode.MD5(newPassword.trim());
			user.setPassword(newPasswrodTrim);
			user.setModifier(currentLoginId);
			user.setLoginId(currentLoginId);
			
			if (userService.updateUserPassword(user)) {
				return "updateUserPassword";
			} else {
				addActionError("系统错误：更新密码出错！");
				return ERROR;
			}
		} catch (Exception e) {
			log.error(e.toString());
			addActionError(e.toString());
			return ERROR;
		}
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
