package com.imanager.user.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.Md5Encode;
import com.imanager.framework.action.BaseAction;
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
	 * ��ʼ���û�ע��
	 * @return
	 * @throws Exception
	 */
	public String initRegisterUser() throws Exception {
		try {
			return "initRegisterUser";
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺳�ʼ���û�ע�����");
			return ERROR;
		}
	}
	
	/**
	 * ע��һ���û�
	 * @return
	 * @throws Exception
	 */
	public String registerUser() throws Exception {
		try {
			String loginIdTrim = user.getLoginId().trim();
			String passwordTrim = user.getPassword().trim();
			user.setCreator(loginIdTrim);
			user.setModifier(loginIdTrim);
			user.setPassword(Md5Encode.MD5(passwordTrim));
			//ע���û�
			userService.registerUser(user);
			//��¼��ǰ�û�
			loginService.recordCurrentLoginId(loginIdTrim);
		} catch (UserServiceException e) {
			addActionError(e.getMessage());
			return "registerUserInput";
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("ϵͳ����ע���û�����");
			return ERROR;
		}
		
		return "registerUser";
	}
	
	/**
	 * ��õ�ǰ��¼�û�����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String initUpdateUserInfo() throws Exception {
		try {
			currentLoginId = loginService.getCurrentLoginId();
			user = userService.getUserByLoginId(currentLoginId);
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺲鿴��ǰ��¼�û�����Ϣ����");
			return ERROR;
		}
		
		return "initUpdateUserInfo";
	}
	
	/**
	 * �����û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String updateUserInfo() throws Exception {
		try {
			currentLoginId = loginService.getCurrentLoginId();
			String userNameTrim = user.getUserName().trim();
			user.setUserName(userNameTrim);
			user.setModifier(currentLoginId);
			user.setLoginId(currentLoginId);
		
			if (userService.updateUserInfo(user)) {
				return "updateUserInfo";
			} else {
				addActionError("ϵͳ���󣺸����û���Ϣ����");
				return ERROR;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺸����û���Ϣ����");
			return ERROR;
		}
	}
	
	/**
	 * ��ʼ���޸��û�����
	 * @return
	 * @throws Exception
	 */
	public String initUpdateUserPassword() throws Exception {
		try {
			return "initUpdateUserPassword";
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺳�ʼ���޸��û��������");
			return ERROR;
		}
	}
	
	/**
	 * �����û�����
	 * @return
	 * @throws Exception
	 */
	public String updateUserPassword() throws Exception {
		try {
			currentLoginId = loginService.getCurrentLoginId();
		
			String pwd = userService.getUserByLoginId(currentLoginId).getPassword();
			if (StringUtils.isBlank(pwd)) {
				addActionError("ԭʼ���벻���ڣ�");
				return "updateUserPasswordInput";
			}
			
			if (!pwd.equals(Md5Encode.MD5(oldPassword.trim()))) {
				addActionError("ԭʼ�������");
				return "updateUserPasswordInput";
			}
			
			String newPasswrodTrim = Md5Encode.MD5(newPassword.trim());
			user.setPassword(newPasswrodTrim);
			user.setModifier(currentLoginId);
			user.setLoginId(currentLoginId);
			
			if (userService.updateUserPassword(user)) {
				return "updateUserPassword";
			} else {
				addActionError("ϵͳ���󣺸����������");
				return ERROR;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺸����������");
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
