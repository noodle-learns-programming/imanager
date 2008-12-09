package com.imanager.login.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.Md5Encode;
import com.imanager.framework.action.BaseAction;
import com.imanager.login.service.ILoginService;
import com.imanager.user.domain.User;
import com.imanager.user.service.IUserService;

/**
 * ��¼
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
	 * ��ʼ����¼ҳ��
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception {
		return "init";
	}
	
	/**
	 * ��֤�û���¼
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String validateUser() throws Exception {
		try {
			//�����û����������ȡ�û�
			String loginIdTrim = loginId.trim();
			String passwordTrim = password.trim();
			User user = userService.getUserByLoginIdNPassword(loginIdTrim, Md5Encode.MD5(passwordTrim));
			
			if(user == null){
				addActionError("�û������������");
				return "validateUserInput";
			}
			
			//���뵱ǰ�û�����¼��ǰ��¼�û���loginId
			if (!loginService.loginCurrentUser(loginIdTrim)) {
				addActionError("ϵͳ���󣺼�¼��ǰ��¼�û�����");
				return ERROR;
			}
			
			//�����ϴε�¼ʱ��
			if (!loginService.updateLastLoginDate(user.getLoginId())) {
				addActionError("ϵͳ���󣺸����ϴε�¼ʱ�����");
				return ERROR;
			}
		}catch (Exception e){
			//log.error("Error: " + LoginAction.class + ", validateUser()",e);
			log.error(e.getMessage());
			addActionError("ϵͳ������֤�Ƿ�Ϸ��û�����");
			return ERROR;
		}
		
		return "validateUser";
	}
	
	public String logoutUser() throws Exception {
		try {
			if (!loginService.logoutCurrentUser()) {
				addActionError("ϵͳ���󣺵ǳ���ǰ�û�����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺵ǳ���ǰ�û�����");
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
